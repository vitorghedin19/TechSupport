// Pasta configuration: configurações gerais do projeto (CORS, segurança, Swagger).
// Este arquivo libera o front-end (rodando em localhost:3000) a fazer requisições pra esta API
// (localhost:8080). Sem isso, o navegador bloqueia as chamadas do axios por segurança.
package com.example.techsupport.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // vale pra todas as rotas da API; só esse endereço (o front) pode chamar
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS","PATCH","HEAD");
    }
}
