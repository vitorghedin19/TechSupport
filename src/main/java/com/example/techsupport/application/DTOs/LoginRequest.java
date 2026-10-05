// Pasta DTOs: moldes de JSON que entram ou saem da API, sem ser uma entidade do banco.
// Este e o JSON que o front ENVIA no login: { "email": "...", "senha": "..." }
package com.example.techsupport.application.DTOs;

public record LoginRequest(String email, String senha) {
}
