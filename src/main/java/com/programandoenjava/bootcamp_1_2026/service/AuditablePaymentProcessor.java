package com.programandoenjava.bootcamp_1_2026.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;

import java.util.logging.Logger;

/*
 * Clase abstracta para evitar repetir código en todos los process.
 * Como todos necesitan BeanNameAware, @PostConstruct y @PreDestroy para los logs,
 * los pongo aquí y las clases que extiendan esto ya lo heredan automáticamente.
 * También implementa PaymentProcessor para que Spring sepa que estos beans
 * son procesadores de pago y pueda inyectarlos donde haga falta.
 */
public abstract class AuditablePaymentProcessor implements PaymentProcessor, BeanNameAware {

    private String beanName;
    protected Logger log;

    public AuditablePaymentProcessor() {
        log = Logger.getLogger(this.getClass().getName());
        System.out.println("PRIMER PASO => INICIALIZANDO EL BEAN: " + this.getClass().getSimpleName());
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @PostConstruct
    private void init(){
        System.out.println("SEGUNDO PASO => INICIANDO EL BEAN: " + beanName);
        log.info("[LOG] Configurando procesador " + beanName + "...");
    }

    @PreDestroy
    private void destroy(){
        System.out.println("TERCER PASO => DESTRUYENDO EL BEAN: " + beanName);
        log.info("[LOG] Cerrando conexiones de " + beanName + " antes del apagado...");

    }

}