// ==========================================================================
// ARQUIVO: CorsConfiguration.java
// PARA QUE SERVE: liberar o FRONT-END (React em localhost:3000) a chamar esta API (localhost:8080).
// CORS = Cross-Origin Resource Sharing. O navegador bloqueia por segurança quando o JavaScript
// de uma origem (site:porta) faz requisição para outra origem. Aqui a API avisa: "aceito requisições
// vindas de http://localhost:3000". Sem isso o front recebe erro de CORS no console do navegador.
// ==========================================================================
package com.example.techsupport.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration: classe de configuração, o Spring a carrega automaticamente na inicialização.
// WebMvcConfigurer: interface do Spring MVC que permite personalizar o comportamento web
// (aqui, só o CORS) sobrescrevendo métodos.
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {


    // @Override: estamos sobrescrevendo o método da interface WebMvcConfigurer.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // "/**" = a regra vale para TODAS as rotas da API (/chamado, /usuarios, /auth/login...).
        registry.addMapping("/**")
                // Só esta origem (o front rodando em localhost:3000) é aceita. Se o front subir em
                // outra porta (ex.: Vite usa 5173), precisa mudar aqui.
                .allowedOrigins("http://localhost:3000")
                // Métodos HTTP permitidos. O OPTIONS é o "preflight": antes de um POST/PUT/PATCH/DELETE
                // o navegador manda um OPTIONS perguntando se pode; sem ele o CORS falha.
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS","PATCH","HEAD");
    }
}
