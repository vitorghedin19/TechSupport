// Acesso ao banco para Solicitante (mesma ideia do ChamadoRepository).
package com.example.techsupport.domain.repository;

import com.example.techsupport.domain.entities.Solicitante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitanteRepository extends JpaRepository<Solicitante, Long> {
}
