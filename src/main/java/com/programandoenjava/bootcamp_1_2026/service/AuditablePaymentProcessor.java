package com.programandoenjava.bootcamp_1_2026.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;

/*
 * Clase abstracta para evitar repetir código en todos los procesadores de pago.
 * Como todos necesitan BeanNameAware, @PostConstruct y @PreDestroy para los logs,
 * los pongo aquí y las clases que extiendan esto ya lo heredan automáticamente.
 * También implementa PaymentProcessor para que Spring sepa que estos beans
 * son procesadores de pago y pueda inyectarlos donde haga falta.
 */
public abstract class AuditablePaymentProcessor implements PaymentProcessor, BeanNameAware {

    private String beanName;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @PostConstruct
    private void init(){
        System.out.println("[LOG] Configurando procesador " + beanName + "...");
    }

    @PreDestroy
    private void destroy(){
        System.out.println("[LOG] Cerrando conexiones de " + beanName + " antes del apagado...");
    }

}