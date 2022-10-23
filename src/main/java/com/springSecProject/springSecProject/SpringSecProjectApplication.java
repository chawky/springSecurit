package com.springSecProject.springSecProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EntityScan("com.springSecProject.modals") 
@ComponentScan (basePackages = {"controllers","services","security","jwt","requestPayload","responsePayload"})
@EnableJpaRepositories(basePackages = {"reporsitories"})
@EnableAutoConfiguration
@SpringBootApplication
public class SpringSecProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecProjectApplication.class, args);
	}

}
