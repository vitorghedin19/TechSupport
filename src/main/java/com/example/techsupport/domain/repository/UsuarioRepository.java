// Acesso ao banco para Usuario. Alem dos metodos prontos, tem 2 consultas "derivadas do nome":
// o Spring le o nome do metodo e monta o SQL sozinho.
package com.example.techsupport.domain.repository;

import com.example.techsupport.domain.entities.EnumStatusUsuario;
import com.example.techsupport.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

        // Usado no login: existe usuario com esse email E essa senha? SELECT ... WHERE email = ? AND senha = ?
        boolean existsUsuarioByEmailAndSenha(String email, String senha);

        // Busca todos os usuarios com status DIFERENTE do informado. Ninguem chama esse metodo ainda.
        Optional<List<Usuario>> findByStatusNot(EnumStatusUsuario statusUsuario);

}
