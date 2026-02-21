package com.programandoenjava.bootcamp_1_2026.interceptor;

import com.programandoenjava.bootcamp_1_2026.filter.TransactionFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class PerformanceInterceptor implements HandlerInterceptor {

    long requestStart = 0L;
    private static final Logger log = LoggerFactory.getLogger(TransactionFilter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        requestStart = System.currentTimeMillis();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        log.info("Tiempo total de respuesta : {} ms", System.currentTimeMillis() - requestStart);
    }
}
