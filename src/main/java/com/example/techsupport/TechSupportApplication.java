// Classe principal do back-end: é ela que "liga" o projeto. Rodar essa classe = subir a API inteira
// (servidor na porta 8080, conexão com o banco, todas as rotas dos controllers).
package com.example.techsupport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TechSupportApplication {

    public static void main(String[] args) {
        System.out.println("HelloWorld");
        // Sobe o servidor e deixa a API no ar, esperando requisições.
        SpringApplication.run(TechSupportApplication.class, args);
    }
}
