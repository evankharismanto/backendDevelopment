package com.javadevelopersguide.springboot.example.customactuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "helloendpoint")
public class HelloActuator {

    @ReadOperation
    public String sayHello() {
        return "Hello!";
    }
}