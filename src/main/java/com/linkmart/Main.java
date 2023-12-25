package com.linkmart;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Linkmart API",
                version = "1.0",
                description = "Documentation Linkmart API v1.0")
                )
public class Main {
    public static void main(String[] args) {
            SpringApplication.run(Main.class,args);
        System.out.println(org.hibernate.Version.getVersionString());
    }
}