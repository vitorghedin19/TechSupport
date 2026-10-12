package com.example.techsupport.application.services;

import com.example.techsupport.application.DTOs.ComunicacaoResponse;
import com.example.techsupport.application.DTOs.CriarComunicacaoRequest;
import com.example.techsupport.application.DTOs.CriarComunicacaoResponse;
import com.example.techsupport.domain.entities.Comunicacao;
import com.example.techsupport.domain.repository.ComunicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComunicacaoService {

    @Value("${spring.secret}")
    private String secret;

    @Autowired
    private ComunicacaoRepository comunicacaoRepository;

    public List<ComunicacaoResponse> listarTodasComunicacoesTable(){

        return comunicacaoRepository.findAll()
                .stream()
                .map(ComunicacaoResponse::new)
                .toList();

    }

    public CriarComunicacaoResponse criarComunicacao(CriarComunicacaoRequest comunicacaoRequest) {

        if (!comunicacaoRequest.secretKey().equals(secret)){
            return new CriarComunicacaoResponse(0L, "Comunicação Salva com sucesso!");
        }

        Comunicacao comunicacaoSalvar = new Comunicacao (comunicacaoRequest);

        comunicacaoRepository.save(comunicacaoSalvar);

        return new CriarComunicacaoResponse(comunicacaoSalvar.getId(), "Comunicação salvo com sucesso!");

    }

}
