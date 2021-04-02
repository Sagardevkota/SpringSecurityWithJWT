package com.example.sagar.SpringSecurityWithJWT.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("azure.myblob")
public class AzureBlobProperties {
    private String connectionstring;
    private String container;

}
