package com.programandoenjava.bootcamp_1_2026.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class TransactionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        if(path.equals("/checkout")){
            String header = request.getHeader("X-Transaction-Id");
            if(header != null){
                System.out.println("TRANSACCIÓN CAPTURADA : " + header);
                filterChain.doFilter(request,response);
            }else{
                response.sendError(400, "No se ha encontrado X-Transaction-Id en el Header");
            }
        }
    }
}
