package com.programandoenjava.bootcamp_1_2026;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Arrays;

@SpringBootApplication
@EnableAsync()
public class Bootcamp12026Application {

	private static final Logger log = LoggerFactory.getLogger(Bootcamp12026Application.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Bootcamp12026Application.class, args);
        log.info("CodeJa levantado con éxito en el perfil: {}", Arrays.toString(context.getEnvironment().getActiveProfiles()));
        log.info("La pasarela de pago activa es: {}", context.getEnvironment().getProperty("app.payment-provider", "mock"));
	}
}