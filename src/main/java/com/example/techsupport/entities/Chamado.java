package com.example.techsupport.entities;

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
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String titulo;
    public String descricao;
    public String prioridade;
    public Status status;

    public enum Status{
        ABERTO,
        EM_ANDAMENTO,
        RESOLVIDO,
        FECHADO,
        ATRASADO
    }
}
