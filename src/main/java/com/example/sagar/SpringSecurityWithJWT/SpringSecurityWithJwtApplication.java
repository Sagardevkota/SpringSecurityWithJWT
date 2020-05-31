package com.example.sagar.SpringSecurityWithJWT;

import com.example.sagar.SpringSecurityWithJWT.repository.ProductRepository;
import com.example.sagar.SpringSecurityWithJWT.repository.UserRepository;
import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.sagar.SpringSecurityWithJWT.repository")
public class SpringSecurityWithJwtApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringSecurityWithJwtApplication.class, args);
    }

}
