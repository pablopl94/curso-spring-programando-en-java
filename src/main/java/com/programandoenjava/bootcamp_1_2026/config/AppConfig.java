package com.programandoenjava.bootcamp_1_2026.config;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViews;
import com.blazebit.persistence.view.spi.EntityViewConfiguration;
import com.programandoenjava.bootcamp_1_2026.orders.model.projection.OrderDashboardView;
import com.programandoenjava.bootcamp_1_2026.payments.service.PaymentProcessor;
import com.programandoenjava.bootcamp_1_2026.payments.service.impl.MockProcessor;
import jakarta.persistence.EntityManagerFactory;
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

    @Bean
    public CriteriaBuilderFactory criteriaBuilderFactory(EntityManagerFactory emf){
        return Criteria.getDefault().createCriteriaBuilderFactory(emf);
    }

    @Bean
    public EntityViewManager entityViewManager(CriteriaBuilderFactory cbf) {
        EntityViewConfiguration config = EntityViews.createDefaultConfiguration();
        config.addEntityView(OrderDashboardView.class);
        return config.createEntityViewManager(cbf);
    }

}
