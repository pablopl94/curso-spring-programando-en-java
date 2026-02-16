package com.programandoenjava.bootcamp_1_2026;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class Bootcamp12026Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Bootcamp12026Application.class, args);
		System.out.println("CodeJa levantado con éxito en el perfil: " + Arrays.toString(context.getEnvironment().getActiveProfiles()));
		System.out.println("La pasarela de pago activa es: " + context.getEnvironment().getProperty("app.payment-provider", "mock"));
	}
}