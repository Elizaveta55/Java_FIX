package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = "com.company")
@EntityScan(basePackages = "com.company.models", basePackageClasses = Jsr310JpaConverters.class)
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

}
