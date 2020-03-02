package com.javadevelopersguide.springboot.example.customactuator;

import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "customlov")
public class CustomLOVActuator {
    private Map customLov = new ConcurrentHashMap<>();

    public CustomLOVActuator() {
        customLov.put("lastDeploymentAuthor", "SYSTEM");
        customLov.put("lastDeploymentIssue", "RELEASE");
    }

    @ReadOperation
    public Map customLOVs() {
        return customLov;
    }

    @ReadOperation
    public String customLOV(@Selector String name) {
        return (String) customLov.get(name);
    }

    @WriteOperation
    public void writeLOV(@Selector String name, String value) {
        customLov.put(name, value);
    }
    @DeleteOperation
    public void deleteLOV(@Selector String name){
        customLov.remove(name);
    }
}
