package com.programandoenjava.bootcamp_1_2026.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TransactionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path.equals("/api/order/checkout")) {
            String header = request.getHeader("X-Transaction-Id");
            if (header == null) {
                // response.sendError(400, "No se ha encontrado X-Transaction-Id en el Header" ) no lo captura, nose si es porque Spring lo intercepta antes
                return;
            }
            System.out.println("Capturado X-Transaction-Id-: " + header);
        }

        filterChain.doFilter(request, response);
    }
}
