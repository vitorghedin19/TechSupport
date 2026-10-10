package com.example.techsupport.domain.repository;

import com.example.techsupport.domain.entities.Comunicacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunicacaoRepository extends JpaRepository<Comunicacao, Long> {
}
