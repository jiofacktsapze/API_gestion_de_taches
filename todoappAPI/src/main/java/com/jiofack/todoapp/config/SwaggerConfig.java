package com.jiofack.todoapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("TodoApp API")
                        .version("1.0.0")
                        .description("API de gestion des tâches pour l'application TodoApp. Elle permet de créer, modifier, supprimer et consulter les tâches")
                );
    }
}
