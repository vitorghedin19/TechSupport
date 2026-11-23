package com.example.techsupport.application.DTOs;

// Corpo do POST /auth/login.
public record LoginRequest(String email, String senha) {
}
