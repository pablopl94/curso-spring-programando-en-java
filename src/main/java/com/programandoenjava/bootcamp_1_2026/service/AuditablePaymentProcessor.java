package com.programandoenjava.bootcamp_1_2026.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;


/*
 * Clase abstracta para evitar repetir código en todos los process.
 * Como todos necesitan BeanNameAware, @PostConstruct y @PreDestroy para los logs,
 * los pongo aquí y las clases que extiendan esto ya lo heredan automáticamente.
 * También implementa PaymentProcessor para que Spring sepa que estos beans
 * son procesadores de pago y pueda inyectarlos donde haga falta.
 */
public abstract class AuditablePaymentProcessor implements PaymentProcessor, BeanNameAware, ApplicationEventPublisherAware {

    private String beanName;
    protected Logger log;
    protected ApplicationEventPublisher publisher;

    public AuditablePaymentProcessor() {
        log = LoggerFactory.getLogger(this.getClass().getName());
        log.info("INICIALIZANDO EL BEAN: {}", this.getClass().getSimpleName());
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @PostConstruct
    private void init(){
        log.info("[LOG] Configurando procesador {}...", beanName);
    }

    @PreDestroy
    private void destroy(){
        log.info("[LOG] Cerrando conexiones de {} antes del apagado...", beanName);
    }

}