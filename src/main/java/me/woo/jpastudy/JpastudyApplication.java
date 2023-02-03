package me.woo.jpastudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaRepositories
@SpringBootApplication
@EnableJpaAuditing
public class JpastudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpastudyApplication.class, args);
	}

}

