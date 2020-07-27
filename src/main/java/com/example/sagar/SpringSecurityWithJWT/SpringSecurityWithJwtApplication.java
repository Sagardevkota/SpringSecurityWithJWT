package com.example.sagar.SpringSecurityWithJWT;

import com.example.sagar.SpringSecurityWithJWT.repository.ProductRepository;
import com.example.sagar.SpringSecurityWithJWT.repository.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.context.SecurityContext;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EnableSwagger2
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.sagar.SpringSecurityWithJWT.repository")
public class SpringSecurityWithJwtApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringSecurityWithJwtApplication.class, args);
    }

    @Bean
    public Docket swaggerConfiguration()
    {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.sagar.SpringSecurityWithJWT"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(apiKey()))
                .securityContexts(Arrays.asList(securityContext()))
                .useDefaultResponseMessages(false)


                ;
    }

    private ApiInfo apiInfo()
    {
        return new ApiInfo(
                "SMART API",
                "API for applications owned by S-Mart company",
                "1.0",
                "Contact sagar devkota to use",
                new springfox.documentation.service.Contact("Sagar Devkota","sagardevkota.com","sagardevkota55@gmail.com"),
                "API License",
                "http://sagar.com",
                Collections.emptyList()

        );
    }


    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private springfox.documentation.spi.service.contexts.SecurityContext securityContext() {
        return  springfox.documentation.spi.service.contexts.SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("JWT", authorizationScopes));
    }


}
