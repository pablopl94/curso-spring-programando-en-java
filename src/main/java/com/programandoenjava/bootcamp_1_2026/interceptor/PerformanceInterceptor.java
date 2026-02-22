package com.programandoenjava.bootcamp_1_2026.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class PerformanceInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(PerformanceInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Con HttpServletRequest creamos un objeto por cada petición, así no se sobreescribe
        // y las peticiones no comparten una variable que podría sobreescribirla si se lanzan a la vez de dos peticiones
        request.setAttribute("startTime",System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        Long start = (Long) request.getAttribute("startTime");
        if(start == null) {
            log.warn("Error: No se ha ejecutado correctamente preHandle");
            return;
        }
        Long totalTime = System.currentTimeMillis() - start;
        log.debug("Tiempo total de respuesta : {} ms", totalTime);
    }
}
