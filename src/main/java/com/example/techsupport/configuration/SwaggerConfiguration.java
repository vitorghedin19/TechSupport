// ==========================================================================
// ARQUIVO: SwaggerConfiguration.java
// PARA QUE SERVE: personalizar a documentação automática da API (Swagger UI / OpenAPI).
// O Swagger gera uma página (normalmente em /swagger-ui/index.html) listando todos os endpoints
// dos controllers para testar pelo navegador. Aqui configuramos o título e o botão "Authorize",
// onde se cola o token JWT para testar rotas protegidas.
// ==========================================================================
package com.example.techsupport.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    // @Bean: o objeto retornado por este método é registrado no Spring e usado pelo Swagger
    // como a "descrição geral" da API.
    @Bean
    public OpenAPI customOpenAPI(){

        return new OpenAPI()
                // Diz que a API usa o esquema de segurança chamado "bearerAuth" (definido logo abaixo).
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                // Define o esquema "bearerAuth": autenticação HTTP do tipo Bearer, com token no formato JWT.
                // Isso faz aparecer o cadeado/botão "Authorize" no Swagger UI.
                .components(new Components().addSecuritySchemes("bearerAuth" ,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                ))
                // Informações que aparecem no topo da página do Swagger.
                .info(new Info()
                .title("TechSupport")
                .version("1.0.0")
                .description("Api para aula da 4 fase"));

    }

}
