package com.menthfy.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * EN: Defines the metadata exposed by the generated OpenAPI document.
 * PT-BR: Define os metadados expostos pelo documento OpenAPI gerado.
 */
@Configuration
public class OpenApiConfig {

    /**
    * EN: Creates the OpenAPI description for the mentorship service.
    * PT-BR: Cria a descrição OpenAPI do serviço de mentorias.
     *
     * @return configured OpenAPI metadata
     */
    @Bean
    public OpenAPI mentorshipServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Menthfy Mentorship Service API")
                        .description("EN: API responsible for the mentorship request lifecycle. "
                            + "PT-BR: API responsável pelo ciclo de vida das solicitações de mentoria.")
                        .version("v1")
                        .contact(new Contact().name("Menthfy")));
    }
}