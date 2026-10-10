package com.example.techsupport.application.services;

import com.example.techsupport.application.DTOs.ComunicacaoRequest;
import com.example.techsupport.application.DTOs.ComunicacaoResponse;
import com.example.techsupport.domain.entities.Comunicacao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ComunicacaoService {

    @Value("${spring.secret}")
    private String secret;

    public ComunicacaoResponse criarComunicacao(ComunicacaoRequest comunicacaoRequest) {

        if (!ComunicacaoRequest.secretKey().equals(secret)){
            return new ComunicacaoResponse(0L, "Comunicação Salva com sucesso!");
        }

        Comunicacao comunicacaoSalvar = new Comunicacao (comunicacaoRequest);

        comunicacaoRepository.save(comunicacaoSalvar);

        return new ComunicacaoResponse(comunicacaoSalvar.getId(), "Comunicação salvo com sucesso!");

    }

}
