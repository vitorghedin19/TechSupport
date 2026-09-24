// ==========================================================================
// ARQUIVO: SolicitanteRepository.java -> acesso ao banco para Solicitante (mesma ideia do ChamadoRepository).
// ==========================================================================
package com.example.techsupport.repository;

import com.example.techsupport.entities.Solicitante;
import org.springframework.data.jpa.repository.JpaRepository;

// Sem @Repository aqui: funciona igual, pois o Spring Data já detecta interfaces que estendem JpaRepository.
public interface SolicitanteRepository extends JpaRepository<Solicitante, Long> {
}
