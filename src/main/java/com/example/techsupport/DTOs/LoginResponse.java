// ==========================================================================
// ARQUIVO: LoginResponse.java (DTO)
// Objeto que representa o JSON que a API DEVOLVE quando o login dá certo:  { "token": "eyJhbGci..." }
// O front guarda esse token e o envia nas próximas requisições no header Authorization: Bearer <token>.
// ==========================================================================
package com.example.techsupport.DTOs;

// record com um único campo; o Jackson transforma em JSON {"token": "..."}.
public record LoginResponse(String token) {
}
