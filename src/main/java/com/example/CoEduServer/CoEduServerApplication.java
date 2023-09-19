package com.example.CoEduServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CoEduServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoEduServerApplication.class, args);
	}

}
