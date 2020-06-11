package com.fatec.javaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjetoJavaWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoJavaWebApplication.class, args);
	}

}
