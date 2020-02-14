package com.backendDevelopment.crud.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan({"com.delivery.request"})
@EntityScan("com.delivery.request")
public class RestServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CRUDController.class, args);
	}
}
