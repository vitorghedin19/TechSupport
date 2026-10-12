package com.example.techsupport.presentation;

import com.example.techsupport.application.DTOs.*;
import com.example.techsupport.application.services.ComunicacaoService;
import com.example.techsupport.domain.repository.ComunicacaoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comunicacao")
@Tag(name = "Comunicação", description = "Endpoints responsáveis pelo gerenciamento de tipos de comunicações do sistema TechSupport, permitindo consultar e cadastrar.")
public class ComunicacaoController {


        @Autowired //injeção de dependencia
        private ComunicacaoRepository comunicacaoRepository;

        @Autowired
        private ComunicacaoService comunicacaoService;


        @GetMapping
        @Operation(summary = "Método de consulta de listas de comunicações!", description = "Método reponsável em efetuar a consulta de todos as comunicações!")
        public ResponseEntity<List<ComunicacaoResponse>> listarTodos(){

            return ResponseEntity.ok(comunicacaoService.listarTodasComunicacoesTable());
        }

        @PostMapping("/criar")
        @Operation(summary = "Método de criação de tipos de comunicação!", description = "Método reponsável em efetuar a criação de novos tipos de comunicação!")
        public ResponseEntity<CriarComunicacaoResponse> criarComunicacao(@RequestBody CriarComunicacaoRequest criarComunicacaoRequest){

            try {

                CriarComunicacaoResponse respostaSalvar = comunicacaoService.criarComunicacao(criarComunicacaoRequest);

                return ResponseEntity.ok(respostaSalvar);

            } catch (RuntimeException e){
                return ResponseEntity.badRequest().build();
            }
        }
    }