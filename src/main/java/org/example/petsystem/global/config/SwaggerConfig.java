package org.example.petsystem.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){

        Info info = new Info()
                .title("PET CARE SYSTEM API Document")
                .description("반려동물 케이 서비스 프로젝트의 API 명세서");

        return new OpenAPI()
                .info(info);
    }
}
