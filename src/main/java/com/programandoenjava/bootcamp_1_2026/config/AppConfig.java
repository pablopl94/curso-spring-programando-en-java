package com.programandoenjava.bootcamp_1_2026.config;

import com.programandoenjava.bootcamp_1_2026.service.PaymentProcessor;
import com.programandoenjava.bootcamp_1_2026.service.impl.MockProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // Este method define un bean de tipo PaymentProcessor que Spring puede inyectar
    // @ConditionalOnMissingBean indica que este bean solo se crea si NO hay otro PaymentProcessor registrado
    // Esto asegura que si no se activa un perfil específico, siempre haya una implementación disponible
    @Bean
    @ConditionalOnMissingBean(PaymentProcessor.class)
    public PaymentProcessor getPaymentProcessorDefault() {
        return new MockProcessor();
    }
}
