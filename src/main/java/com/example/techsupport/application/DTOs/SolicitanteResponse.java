package com.example.techsupport.application.DTOs;

import com.example.techsupport.domain.entities.EnumStatusSolicitante;
import com.example.techsupport.domain.entities.Solicitante;

public record SolicitanteResponse (Long id, String nome, String email, String setor, EnumStatusSolicitante statusSolicitante) {

    public SolicitanteResponse (Solicitante solicitanteEntidade) {
        this(
                solicitanteEntidade.getId(),
                solicitanteEntidade.getNome(),
                solicitanteEntidade.getEmail(),
                solicitanteEntidade.getSetor(),
                solicitanteEntidade.getStatus()
        );
    }

}
