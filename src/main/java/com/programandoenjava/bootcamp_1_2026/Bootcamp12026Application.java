package com.programandoenjava.bootcamp_1_2026;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication
//Implemento ApplicationRunner que ya lleva el method run() para acceder justo después del arranque
public class Bootcamp12026Application implements ApplicationRunner {

	/*
	* Environment es un objeto de Spring que representa el entorno de ejecución
	* Permite acceso a los perfiles activos y las propiedades del YAML
	*/
	private final Environment env;


	/*
	 * TODO: ApplicationContext - > De momento no lo usamos pero lo dejo explicado
	 * ApplicationContext es el contenedor principal de spring, el que arranca la app básicamente y la gestiona
	 * Contiene dentro Environment, gestiona el ciclo de vida, inyecta las dependencias, gestiona los Beans..
	 */

	public Bootcamp12026Application(Environment env) {
		this.env = env;
	}

	// Este es el run de SpringApplication, arranca Spring
	public static void main(String[] args) {
		SpringApplication.run(Bootcamp12026Application.class, args);
	}

	// Este es el run de ApplicationRunner, se ejecuta JUSTO DESPUÉS de que Spring arranque
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("CodeJa levantado con éxito en el perfil: " + Arrays.toString(env.getActiveProfiles()));
		System.out.println("La pasarela de pago activa es: " + (env.getProperty("app.payment-provider", "mock")));
	}
}
