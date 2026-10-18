// Entidade Solicitante -> tabela "solicitante". E quem abre o chamado (cliente/funcionario).
package com.example.techsupport.domain.entities;

import com.example.techsupport.application.DTOs.EnumSetor;
import com.example.techsupport.application.DTOs.EnumStatusSolicitante;
import com.example.techsupport.application.DTOs.SolicitanteRequest;
import com.example.techsupport.domain.valueobject.CPF;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solicitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private EnumSetor setor;
    private EnumStatusSolicitante status = EnumStatusSolicitante.ATIVO;

    public Solicitante (SolicitanteRequest solicitante){
        this.setNome(solicitante.nome());
        this.setEmail(solicitante.email());
        this.setSetor(solicitante.setor());
    }

    public void setEmail(String email) {
        if(!email.isEmpty() && !email.contains("@")){
            throw new IllegalArgumentException("E-mail Inválido!");
        }
        this.email = email;
    }

}
