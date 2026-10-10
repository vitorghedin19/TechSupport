package com.example.techsupport.domain.entities;

import com.example.techsupport.application.DTOs.ComunicacaoRequest;
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
public class Comunicacao {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nome;

        public Comunicacao(ComunicacaoRequest comunicacaoRequest) {

            this.setNome(comunicacaoRequest.nome());

        }
    }

}
