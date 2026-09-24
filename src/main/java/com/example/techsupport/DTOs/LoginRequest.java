// ==========================================================================
// ARQUIVO: LoginRequest.java (DTO = Data Transfer Object)
// Objeto que representa o JSON que o front ENVIA no login:  { "email": "...", "senha": "..." }
// O AuthController recebe isso com @RequestBody e o Spring converte o JSON para este objeto (Jackson).
// Por que um DTO e não a entidade Usuario? Porque o login só precisa de 2 campos.
// ==========================================================================
package com.example.techsupport.DTOs;

// "record" (Java 16+): classe imutável enxuta. O Java gera sozinho construtor, getters, equals, hashCode e toString.
// Os getters de um record NÃO têm prefixo "get": usa-se loginRequest.email() e loginRequest.senha().
public record LoginRequest(String email, String senha) {
}
