package com.example.techsupport.application.DTOs;

import com.example.techsupport.domain.entities.Solicitante;

public record SolicitanteLogado(Long id, String email,
                                java.util.Collection<? extends org.springframework.security.core.GrantedAuthority> authorities) {

    public SolicitanteLogado(Solicitante solicitante){
        this(
                solicitante.getId(),
                solicitante.getEmail(),
                solicitante.getAuthorities()
        );
    }
}