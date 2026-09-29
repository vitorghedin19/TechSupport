// Pasta services: onde fica a REGRA (nao a rota nem o acesso ao banco). Esta classe gera e confere
// o token JWT usado no login. Quem usa: AuthController (gera o token) e JwtFilter (confere o token).
package com.example.techsupport.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    // Valores lidos do application.properties (chave secreta, minutos de validade e emissor do token).
    @Value("${spring.secret}")
    private String secret;

    @Value("${spring.expiracao}")
    private Long expiracao;

    @Value("${spring.emissor}")
    private String emissor;

    // Gera o token JWT para o usuário que acabou de fazer login com sucesso.
    // "subject" = identificador do usuário dentro do token (aqui, o email — é o que o
    // AuthController deve estar passando ao chamar este metodo após validar a senha).
    public String gerarToken(String subject){

        try {

            // Algorithm.HMAC256(secret): define o algoritmo de assinatura (HMAC-SHA256),
            // usando a chave secreta (a mesma que verificarToken() usa depois pra conferir
            // — por isso precisa ser IDÊNTICA nos dois métodos, senão o token gerado aqui
            // nunca vai passar na validação).
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Monta o token usando "builder pattern" (métodos encadeados, cada um define uma
            // parte do JWT):
            //   .withIssuer(emissor)      -> quem emitiu o token (claim "iss")
            //   .withSubject(subject)     -> a quem o token pertence (claim "sub") — aqui, o email
            //   .withExpiresAt(...)       -> quando o token vence (claim "exp"), calculado no
            //                                metodo getDataExpiracao() logo abaixo
            //   .sign(algorithm)          -> assina o token com a chave e devolve a string final,
            //                                no formato padrão JWT (três partes separadas por ".")
            String token = com.auth0.jwt.JWT.create().withIssuer(emissor).withSubject(subject).withExpiresAt(getDataExpiracao()).sign(algorithm);

            return token;

        }catch (RuntimeException e){
            // Se algo der errado ao montar/assinar o token (ex: erro na configuração do
            // algoritmo), relança como RuntimeException — nesse caso específico é meio
            // redundante (captura RuntimeException só pra devolver outra RuntimeException
            // embrulhando a mesma), mas serve pra "centralizar" o ponto de erro caso algum
            // dia queiram adicionar log ou tratamento aqui.
            throw new RuntimeException(e);
        }
    }

    // Confere se um token e valido. Se estiver tudo certo devolve o token decodificado; se algo
    // estiver errado (assinatura, emissor ou prazo), lanca excecao.
    public DecodedJWT verificarToken(String token) throws JWTVerificationException {

        Algorithm algorithm = Algorithm.HMAC256(secret);

        JWTVerifier verificador = JWT.require(algorithm).withIssuer(emissor).build();

        return verificador.verify(token);

    }

    // Calcula quando o token vai vencer: agora + os minutos configurados no properties.
    private Instant getDataExpiracao(){

        var dataAtual = LocalDateTime.now();
        var dataFutura = dataAtual.plusMinutes(expiracao);

        return dataFutura.toInstant(ZoneOffset.of("-03:00"));

    }

}
