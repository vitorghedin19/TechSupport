// ==========================================================================
// ARQUIVO: Usuario.java (ENTIDADE JPA) -> tabela "usuario".
// É o TÉCNICO do sistema: quem faz login (email + senha) e atende os chamados.
// ==========================================================================
package com.example.techsupport.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity = vira tabela; @Data / @NoArgsConstructor / @AllArgsConstructor = Lombok gera getters, setters e construtores.
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    // Chave primária gerada pelo banco (auto incremento).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    // Senha salva como texto puro no banco (sem criptografia/hash) e comparada direto no login.
    // Em um sistema real usaríamos hash (ex.: BCrypt).
    private String senha;
    // O email é o "login" do usuário (usado no /auth/login e virou o "subject" do token).
    private String email;
    // Status (enum salvo como número); todo usuário novo nasce ATIVO.
    private EnumStatusUsuario status = EnumStatusUsuario.ATIVO;
}
