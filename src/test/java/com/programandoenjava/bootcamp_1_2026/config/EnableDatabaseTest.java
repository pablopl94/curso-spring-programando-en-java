package com.programandoenjava.bootcamp_1_2026.config;

import com.programandoenjava.bootcamp_1_2026.config.infrastucture.CriteriaBuilderConfig;
import org.springframework.boot.data.jpa.test.autoconfigure.AutoConfigureDataJpa;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.AutoConfigureTestEntityManager;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableJpaRepositories(basePackages = "com.programandoenjava.bootcamp_1_2026")
@EntityScan(basePackages = "com.programandoenjava.bootcamp_1_2026")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@AutoConfigureDataJpa
@Import(CriteriaBuilderConfig.class)
public @interface EnableDatabaseTest {
}
