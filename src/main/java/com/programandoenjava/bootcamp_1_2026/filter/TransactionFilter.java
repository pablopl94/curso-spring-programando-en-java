package com.programandoenjava.bootcamp_1_2026.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TransactionFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(TransactionFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path.equals("/api/order/checkout")) {
            String header = request.getHeader("X-Transaction-Id");
            if (header == null) {
                response.sendError(400);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
