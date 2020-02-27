package com.javadevelopersguide.springboot.example;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.core.env.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@Controller
@EnableAutoConfiguration
public class HelloWorldController {
    @Autowired
    StandardEnvironment environment;
    @Value("${hello.message}")
    private String helloMessage;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(@RequestHeader(name = "profile-name", required = false) String profileName) throws IOException {
        if(profileName != null) {
            reloadPropValue(environment.getActiveProfiles()[0],profileName);
            environment.setActiveProfiles(profileName.equals("") ? "default": profileName);
            helloMessage = environment.getProperty("hello.message");
        }
        return helloMessage;
    }

    private void reloadPropValue(String currentProfile,String profileName) throws IOException {
        MutablePropertySources propertySources = environment.getPropertySources();
        Properties properties = new Properties();
        profileName = profileName == null || profileName.equals("") ? "" : "-"+profileName;
        currentProfile = currentProfile == null || currentProfile.equals("default") ? "" : "-"+currentProfile;
        var configPaths = Paths.get("src/main/resources/application"+profileName+".properties");
        InputStream inputStream = Files.newInputStream(configPaths);
                /*getClass().getResourceAsStream("/application"+profileName+".properties");*/
        properties.load(inputStream);
        inputStream.close();
        propertySources.replace("applicationConfig: [classpath:/application"+currentProfile+".properties]",new OriginTrackedMapPropertySource("applicationConfig: [classpath:/application"+profileName+".properties]", properties));
    }
}