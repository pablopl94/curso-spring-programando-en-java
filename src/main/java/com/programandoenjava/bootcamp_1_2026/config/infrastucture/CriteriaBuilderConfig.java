package com.programandoenjava.bootcamp_1_2026.config.infrastucture;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViews;
import com.blazebit.persistence.view.spi.EntityViewConfiguration;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.database.model.view.OrderDashboardView;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CriteriaBuilderConfig {

    @Bean
    public CriteriaBuilderFactory criteriaBuilderFactory(EntityManagerFactory emf) {
        return Criteria.getDefault().createCriteriaBuilderFactory(emf);
    }

    @Bean
    public EntityViewManager entityViewManager(CriteriaBuilderFactory cbf) {
        EntityViewConfiguration config = EntityViews.createDefaultConfiguration();
        config.addEntityView(OrderDashboardView.class);
        return config.createEntityViewManager(cbf);
    }

}
