package com.example.techsupport.domain.repository;

import com.example.techsupport.domain.entities.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Acesso ao banco para Chamado. Os nomes dos metodos viram SQL (Spring Data).
@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

    // SELECT ... FROM chamado WHERE solicitante_id = ?  (o Spring monta o SQL pelo nome do metodo)
    List<Chamado> findBySolicitante_Id(Long solicitanteId);

    // WHERE empresa_id = ? (chamados da empresa do tecnico)
    List<Chamado> findByEmpresa_Id(Long empresaId);
}