package com.javadevelopersguide.springboot.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HelloWorldSpringBootApplication {
	@Autowired
	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(HelloWorldSpringBootApplication.class, args);
	}
}
