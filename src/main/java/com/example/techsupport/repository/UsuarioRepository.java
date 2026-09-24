// ==========================================================================
// ARQUIVO: UsuarioRepository.java -> acesso ao banco para Usuario.
// Além dos métodos prontos do JpaRepository, tem 2 "consultas derivadas do nome do método":
// o Spring Data LÊ o nome do método e monta o SQL automaticamente (por isso o nome precisa seguir o padrão).
// ==========================================================================
package com.example.techsupport.repository;

import com.example.techsupport.entities.EnumStatusUsuario;
import com.example.techsupport.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

        // Nome quebrado em partes: exists + Usuario + By + Email + And + Senha.
        // Gera algo como: SELECT ... FROM usuario WHERE email = ? AND senha = ?  e devolve true/false.
        // Usado no AuthController para validar o login.
        boolean existsUsuarioByEmailAndSenha(String email, String senha);

        // findBy + Status + Not => WHERE status <> ?  (todos os usuários cujo status é DIFERENTE do informado).
        // Útil para listar sem os EXCLUIDO. Optional evita retornar null. Nas classes enviadas, ninguém chama este método ainda.
        Optional<List<Usuario>> findByStatusNot(EnumStatusUsuario statusUsuario);

}
