// ==========================================================================
// ARQUIVO: TokenService.java -> GERA e VALIDA tokens JWT (biblioteca com.auth0.java-jwt).
//
// O QUE É UM JWT (JSON Web Token): texto com 3 partes separadas por ponto:  HEADER.PAYLOAD.ASSINATURA
//  - PAYLOAD: dados (claims) como emissor (iss), assunto/usuário (sub) e expiração (exp). É só Base64, NÃO é
//    criptografado - qualquer um consegue ler, então nunca se coloca senha dentro.
//  - ASSINATURA: calculada com a chave secreta. Se alguém alterar o payload, a assinatura deixa de bater e o token é recusado.
//
// QUEM USA: AuthController (gerarToken, no login) e JwtFilter (verificarToken, nas requisições).
// ==========================================================================
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

// @Service: classe de regra/serviço; o Spring cria uma instância única (bean) e a injeta onde tiver @Autowired.
@Service
public class TokenService {

    // @Value("${spring.secret}"): o Spring busca no application.properties a propriedade "spring.secret"
    // e coloca o valor neste campo. Assim a chave não fica escrita dentro do código.
    @Value("${spring.secret}")
    private String secret;

    // Minutos de validade do token (no properties está 5).
    @Value("${spring.expiracao}")
    private Long expiracao;

    // Nome de quem emitiu o token (vai no campo "iss").
    @Value("${spring.emissor}")
    private String emissor;

    // Cria o token para o usuário informado. "subject" recebe o e-mail (chamado no AuthController.login).
    public String gerarToken(String subject){

        try {

            // Algoritmo de assinatura HMAC com SHA-256 (chave simétrica: a MESMA chave assina e verifica).
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Monta o token encadeando os dados:
            //  withIssuer    -> quem emitiu (iss);
            //  withSubject   -> a quem pertence (sub) = e-mail;
            //  withExpiresAt -> quando deixa de valer (exp);
            //  sign          -> assina com o algoritmo e devolve o texto final "xxxxx.yyyyy.zzzzz".
            String token = com.auth0.jwt.JWT.create().withIssuer(emissor).withSubject(subject).withExpiresAt(getDataExpiracao()).sign(algorithm);

            return token;

        }catch (RuntimeException e){
            // Se algo falhar ao criar o token, repassa como erro de execução.
            throw new RuntimeException(e);
        }
    }

    // Valida um token recebido. Se estiver OK devolve o token decodificado (dá para ler getSubject());
    // se estiver inválido lança JWTVerificationException (o JwtFilter captura e responde 401).
    public DecodedJWT verificarToken(String token) throws JWTVerificationException {

        // Precisa do MESMO algoritmo e chave usados na criação.
        Algorithm algorithm = Algorithm.HMAC256(secret);

        // Monta o verificador exigindo que o emissor seja o nosso. O verify() confere:
        // assinatura (não foi adulterado), emissor correto e se ainda não expirou.
        JWTVerifier verificador = JWT.require(algorithm).withIssuer(emissor).build();

        return verificador.verify(token);

    }

    // Calcula o momento em que o token expira: agora + "expiracao" minutos.
    private Instant getDataExpiracao(){

        //pegar data atual
        var dataAtual = LocalDateTime.now();
        //adicionar ou diminuir tempo da data atual
        var dataFutura = dataAtual.plusMinutes(expiracao);

        // O JWT exige um Instant (momento absoluto em UTC). Como LocalDateTime não tem fuso, informamos
        // o offset -03:00 (horário de Brasília) para converter corretamente.
        return dataFutura.toInstant(ZoneOffset.of("-03:00"));

    }

}
