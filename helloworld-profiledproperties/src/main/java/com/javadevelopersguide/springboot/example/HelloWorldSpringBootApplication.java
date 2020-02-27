package com.javadevelopersguide.springboot.example;

import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@SpringBootApplication
public class HelloWorldSpringBootApplication {
	@Autowired
	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(HelloWorldSpringBootApplication.class, args);
	}

	/*public static void reloadProfile(String profileName){
		Thread thread = new Thread(() -> {
			context.close();
			context = SpringApplication.run(HelloWorldSpringBootApplication.class, "--spring.profiles.active="+profileName);
		});

		thread.setDaemon(false);
		thread.start();
	}*/
}
