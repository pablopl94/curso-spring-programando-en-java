package com.programandoenjava.bootcamp_1_2026.config.application;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.programandoenjava.bootcamp_1_2026.user.domain.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
public class JwtService {

    private final String secretKey;
    private final Long expiration;
    private final String issuerUrl;

    public JwtService(
            @Value("${spring.security.oauth2.resourceserver.jwt.secret-key}") String secret,
            @Value("${spring.security.oauth2.resourceserver.jwt.expiration}") long expiration,
            @Value("${app.jwt.issuer-url}") String issuerUrl
    ) {
        this.secretKey = secret;
        this.expiration = expiration;
        this.issuerUrl = issuerUrl;
    }

    /**
     * Crea los datos que van dentro del token JWT.
     *
     * @return JWTClaimsSet información del token
     */
    public JWTClaimsSet createClaims(User user) {
        return new JWTClaimsSet.Builder()
                .subject(user.id().toString()) //Id del usuario
                .claim("email", user.email()) // Le añadimos el email en los claims
                .claim("name", user.name()) // Le añadimos en los claims el rol
                .claim("role", user.role().name()) // Le añadimos en los claims el rol
                .issuer(issuerUrl) //Quien creo el token o donde se creó
                .issueTime(new Date(System.currentTimeMillis()))// Fecha en la que se creó
                .expirationTime(new Date(System.currentTimeMillis() + expiration)) //Cuando caduca el token
                .build();
    }

    /**
     * Crea el token y lo firma
     */
    public String createToken(User user) throws JOSEException {
        // Primero crea los claims con su method creado anteriormente
        final JWTClaimsSet claims = createClaims(user);
        // Crea el objeto del firmante que se encarga de firmar, usando nuestro secretKey
        JWSSigner signer = new MACSigner(secretKey);
        // Crea el objeto del token (objeto de la cabecera + claims)
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claims);
        // Firma el token (aseguramos que si alguien lo modifica podemos detectar)
        // NOTA: firma = HMAC(header + payload, secretKey)
        signedJWT.sign(signer);
        // Devuelve el token como String (header, payload , firma)
        return signedJWT.serialize();
    }

    /**
     * Validar la firma del token
     *
     * @param token Token JWT como cadena de texto (header, payload , firma)
     * @return true si la firma es válida y false si no lo es
     * @throws ParseException Error al parsear el token
     * @throws JOSEException  Error al verificar la clave secreta
     */
    public boolean validateToken(String token) throws ParseException, JOSEException {
        // Transforma el token en texto a un objeto, que separa las 3 partes (header, payload, firma)
        SignedJWT signedJWT = SignedJWT.parse(token);
        // Creamos el verificador usando la misma secretKey con la que se firmó
        JWSVerifier verifier = new MACVerifier(secretKey);
        // Recalcula la firma con la clave secretKey y la compara con la firma que trae el token
        // Es decir, con verifier recalcula cuál sería la firma y la compara con la del token
        return signedJWT.verify(verifier);
    }

    /**
     * Obtiene el subject de los claims (el id del usuario)
     *
     * @param token Token JWT como cadena de texto (header, payload , firma)
     * @return El subject del token (id del usuario)
     * @throws ParseException Error al parsear el token
     */
    public String getSubjectClaim(String token) throws ParseException {
        SignedJWT signed = SignedJWT.parse(token);
        return signed.getJWTClaimsSet().getSubject();
    }

    /**
     * Obtiene los claims a partir del claimName
     * El claimName es simplemente el nombre del dato que quieres sacar del payload del token.
     *
     * @param claimName El claimName es simplemente el nombre del dato que quieres sacar del payload del token,
     *                  cada uno tiene un nombre interno. ("sub","iss", ...)
     * @param token     Token JWT como cadena de texto (header, payload , firma)
     * @return El valor del claim como String
     * @throws ParseException Error al parsear el token
     */
    public String getClaim(String claimName, String token) throws ParseException {
        SignedJWT signed = SignedJWT.parse(token);
        return (String) signed.getJWTClaimsSet().getClaim(claimName);
    }


    public String getClaimEmail(Authentication authentication) {
        // Obtener el JWT del Authentication
        Jwt jwt = (Jwt) authentication.getPrincipal();
        // Extraer el email de los claims

        if (jwt == null) return null;

        return jwt.getClaimAsString("email");
    }

    public String getClaimName(Authentication authentication) {
        // Obtener el JWT del Authentication
        Jwt jwt = (Jwt) authentication.getPrincipal();

        // Extraer el name de los claims
        if (jwt == null) return null;

        return jwt.getClaimAsString("name");
    }
}
