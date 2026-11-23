package com.example.techsupport.domain.repository;

import com.example.techsupport.domain.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Acesso ao banco para Token. findByToken e o que o TokenService usa para validar o token recebido.
@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {

    Optional<Token> findByToken(String token);
}
