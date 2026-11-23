// JSON que a API DEVOLVE quando o login da certo: { "token": "..." }. O front guarda esse token.
package com.example.techsupport.application.DTOs;

// Resposta do login: o token JWT.
public record LoginResponse(String token) {
}
