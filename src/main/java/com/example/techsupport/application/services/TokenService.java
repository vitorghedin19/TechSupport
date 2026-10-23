// Pasta services: onde fica a REGRA (nao a rota nem o acesso ao banco). Esta classe gera e confere
// o token JWT usado no login. Quem usa: AuthController (gera o token) e JwtFilter (confere o token).
package com.example.techsupport.application.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.techsupport.application.DTOs.LoginRequest;
import com.example.techsupport.application.DTOs.UsuarioLogado;
import com.example.techsupport.domain.entities.Token;
import com.example.techsupport.domain.repository.TokenRepository;
import com.example.techsupport.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenRepository tokenRepository;

    // Gera o token JWT para o usuário que acabou de fazer login com sucesso.
    // "subject" = identificador do usuário dentro do token (aqui, o email — é o que o
    // AuthController deve estar passando ao chamar este metodo após validar a senha).
    public String gerarToken(LoginRequest loginRequestDto){

        Algorithm algorithm = Algorithm.HMAC256(secret);

        var usuario = usuarioRepository.findAll().stream().filter(u-> u.getEmail().equals(loginRequestDto.email())).findFirst().orElse(null);

            String token = com.auth0.jwt.JWT.create()
                    .withIssuer(emissor)
                    .withSubject(loginRequestDto.email())
                    .withExpiresAt(getDataExpiracao())
                    .sign(algorithm);

            tokenRepository.save(new Token(token, usuario));

            return token;
    }

    // Confere se um token e valido. Se estiver tudo certo devolve o token decodificado; se algo
    // estiver errado (assinatura, emissor ou prazo), lanca excecao.
    public UsuarioLogado verificarToken(String token) throws JWTVerificationException {

        Algorithm algorithm = Algorithm.HMAC256(secret);

        JWTVerifier verificador = JWT.require(algorithm).withIssuer(emissor).build();


        verificador.verify(token);

        var tokenBanco = tokenRepository.findByToken(token).orElse(null);
        if (tokenBanco == null){
            throw new IllegalArgumentException("Token Inválido!");
        }
        return new UsuarioLogado(tokenBanco.getUsuario());
    }

    // Calcula quando o token vai vencer: agora + os minutos configurados no properties.
    private Instant getDataExpiracao(){

        var dataAtual = LocalDateTime.now();
        var dataFutura = dataAtual.plusMinutes(expiracao);

        return dataFutura.toInstant(ZoneOffset.of("-03:00"));

    }

}
