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

    // Cria o token pro usuario que fez login (subject = email dele).
    public String gerarToken(String subject){

        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = com.auth0.jwt.JWT.create().withIssuer(emissor).withSubject(subject).withExpiresAt(getDataExpiracao()).sign(algorithm);

            return token;

        }catch (RuntimeException e){
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
