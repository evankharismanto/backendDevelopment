package com.javadevelopersguide.springboot.example;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
@RefreshScope
public class HelloWorldController {
    @Autowired
    StandardEnvironment environment;
    @Value("${hello.message}")
    private String helloMessage;
    @Value("${spring.application.name}")
    private String appName;
    @Value("${spring.cloud.config.uri}")
    private String configBaseURI;
    List<PropertySource<?>> tempPropertySources = new ArrayList<PropertySource<?>>();

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(@RequestHeader(name = "profile-name", required = false) String profileName) throws IOException {
        if(profileName != null) {
            var envProfiles = environment.getActiveProfiles();
            if(environment.getActiveProfiles().length > 1) {
                if (!envProfiles[1].equals(profileName))
                    reloadPropValue(envProfiles[1], profileName);
            }
            else if(!envProfiles[0].equals(profileName))
                reloadPropValue(envProfiles[0], profileName);
            environment.setActiveProfiles(profileName.equals("") ? "default": profileName);
            helloMessage = environment.getProperty("hello.message");
        }
        return helloMessage;
    }

    private void reloadPropValue(String currentProfile,String newProfile) throws IOException {
        MutablePropertySources propertySources = environment.getPropertySources();
        Properties properties = new Properties();
        newProfile = newProfile == null ? "" : newProfile;
        currentProfile = currentProfile == null ? "default" : currentProfile;
        String propertyBaseName = propertySources.stream().filter(r->r.getName().contains(appName+".properties")).findFirst().get().getName();
        String stringFormatPropertyName = propertyBaseName.replace(".properties","%s.properties");
        String currentProfileProperty = String.format(stringFormatPropertyName,currentProfile.equals("default") ? "" : "-"+currentProfile);
        String newProfileProperty = String.format(stringFormatPropertyName,newProfile.equals("") ? newProfile : "-"+newProfile);
        if(!newProfile.equals("")) {
            if (!propertySources.stream().anyMatch(r -> r.getName().equals(newProfileProperty)))
                propertySources.addBefore(propertyBaseName, tempPropertySources.stream().filter(t -> t.getName().equals(newProfileProperty)).findFirst().get());
        }
        if(!currentProfile.equals("default")) {
            if(newProfile.equals("")){
                List<PropertySource<?>> sources = propertySources.stream().filter(r -> r.getName().contains(propertyBaseName.replace(".properties","-"))).collect(Collectors.toList());
                for(var propertySourceObj:sources){
                    if(!tempPropertySources.stream().anyMatch(r -> r.getName().equals(propertySourceObj.getName())))
                        tempPropertySources.add(propertySourceObj);
                }
                for(String resourceName : tempPropertySources.stream().map(r->r.getName()).collect(Collectors.toList())) {
                    if (propertySources.stream().anyMatch(r -> r.getName().equals(resourceName)))
                        propertySources.remove(resourceName);
                }
            }
            else if (propertySources.stream().anyMatch(r -> r.getName().equals(currentProfileProperty))) {
                if(!tempPropertySources.stream().anyMatch(r -> r.getName().equals(currentProfileProperty)))
                    tempPropertySources.add(propertySources.stream().filter(r -> r.getName().equals(currentProfileProperty)).findFirst().get());
                propertySources.remove(currentProfileProperty);
            }
        }
    }
}