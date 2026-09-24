// ==========================================================================
// ARQUIVO: TechSupportApplication.java  -> PONTO DE ENTRADA do back-end.
// É a classe com o main(): rodar o projeto = rodar esta classe.
// ==========================================================================
package com.example.techsupport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication junta 3 anotações:
//  - @Configuration: esta classe pode declarar beans;
//  - @EnableAutoConfiguration: o Spring configura sozinho o que encontra no projeto
//    (Web/Tomcat, JPA/Hibernate, driver do Postgres...);
//  - @ComponentScan: procura @RestController, @Service, @Component, @Configuration etc.
//    neste pacote e em TODOS os subpacotes. Por isso as pastas (controllers, services...)
//    ficam dentro de com.example.techsupport.
@SpringBootApplication
public class TechSupportApplication {

    public static void main(String[] args) {
        // Print de teste no console, não influencia a aplicação.
        System.out.println("HelloWorld");
        // Sobe o servidor embutido (Tomcat, porta 8080 por padrão), cria o "contexto" do Spring
        // (o container que guarda e injeta todos os objetos/beans) e deixa a API no ar.
        SpringApplication.run(TechSupportApplication.class, args);
    }
}
