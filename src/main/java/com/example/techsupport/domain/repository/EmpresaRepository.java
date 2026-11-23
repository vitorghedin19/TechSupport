package com.example.techsupport.domain.repository;

import com.example.techsupport.domain.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Acesso ao banco para Empresa (so os metodos prontos do JpaRepository).
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}