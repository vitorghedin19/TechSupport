package com.example.techsupport.domain.entities;

import com.example.techsupport.application.DTOs.ChamadoRequest;
import com.example.techsupport.application.DTOs.EnumStatusChamado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String prioridade;
    private EnumStatusChamado status = EnumStatusChamado.ABERTO;
    private String solucao; // NOVO: so e preenchido quando o chamado e resolvido

    public Chamado (ChamadoRequest chamado){
        this.setTitulo(chamado.titulo());
        this.setDescricao(chamado.descricao());
        this.setPrioridade(chamado.prioridade());
    }

    // NOVO: regra de negocio central do sistema.
    // Nao e um "set" qualquer - essa e a UNICA forma permitida de marcar um chamado
    // como RESOLVIDO. Se a solucao nao vier preenchida, lanca excecao e nada muda.
    public void resolver(String solucaoAdotada) {
        if (solucaoAdotada == null || solucaoAdotada.isBlank()) {
            throw new IllegalArgumentException("Não é possível resolver o chamado sem registrar a solução adotada.");
        }
        this.solucao = solucaoAdotada;
        this.status = EnumStatusChamado.RESOLVIDO;
    }
}