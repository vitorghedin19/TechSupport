package com.example.techsupport.application.DTOs;

import com.example.techsupport.domain.entities.Comunicacao;

public record ComunicacaoResponse(Long id, String nome) {

    public ComunicacaoResponse (Comunicacao comunicacaoEntidade) {
        this(
                comunicacaoEntidade.getId(),
                comunicacaoEntidade.getNome()
        );
    }
}