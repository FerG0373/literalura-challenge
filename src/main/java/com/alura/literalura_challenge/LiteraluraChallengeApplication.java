package com.alura.literalura_challenge;

import com.alura.literalura_challenge.principal.Principal;
import com.alura.literalura_challenge.service.ConsumoAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraChallengeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Instancia de clase Principal que contiene el menú y la lógica de la aplicación.
		Principal principal = new Principal();
		principal.mostrarMenu();
	}
}
