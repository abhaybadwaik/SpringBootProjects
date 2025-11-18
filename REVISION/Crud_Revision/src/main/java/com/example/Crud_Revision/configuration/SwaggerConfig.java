package com.example.Crud_Revision.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI configSwagger(){
        return new OpenAPI()
                .info(new Info()
                        .title("Abhay's CRUD Operations API")
                        .summary("All the Apis will be manages by Abhay")
                        .version("3.0"));
    }
}
