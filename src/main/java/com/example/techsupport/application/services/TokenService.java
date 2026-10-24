package com.example.techsupport.application.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.techsupport.application.DTOs.LoginRequest;
import com.example.techsupport.application.DTOs.SolicitanteLogado;
import com.example.techsupport.application.DTOs.UsuarioLogado;
import com.example.techsupport.domain.entities.Token;
import com.example.techsupport.domain.repository.SolicitanteRepository;
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

    @Value("${spring.secret}")
    private String secret;

    @Value("${spring.expiracao}")
    private Long expiracao;

    @Value("${spring.emissor}")
    private String emissor;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SolicitanteRepository solicitanteRepository; // NOVO

    @Autowired
    private TokenRepository tokenRepository;

    // Gera token pro Usuario (Tecnico/Admin) - mesma logica de antes, so renomeado.
    public String gerarTokenUsuario(LoginRequest loginRequestDto){

        Algorithm algorithm = Algorithm.HMAC256(secret);
        var usuario = usuarioRepository.findAll().stream().filter(u-> u.getEmail().equals(loginRequestDto.email())).findFirst().orElse(null);

        String token = JWT.create()
                .withIssuer(emissor)
                .withSubject(loginRequestDto.email())
                .withExpiresAt(getDataExpiracao())
                .sign(algorithm);

        tokenRepository.save(new Token(token, usuario));
        return token;
    }

    // NOVO: mesma logica, so que busca e salva o token vinculado a um Solicitante.
    public String gerarTokenSolicitante(LoginRequest loginRequestDto){

        Algorithm algorithm = Algorithm.HMAC256(secret);
        var solicitante = solicitanteRepository.findAll().stream().filter(s-> s.getEmail().equals(loginRequestDto.email())).findFirst().orElse(null);

        String token = JWT.create()
                .withIssuer(emissor)
                .withSubject(loginRequestDto.email())
                .withExpiresAt(getDataExpiracao())
                .sign(algorithm);

        tokenRepository.save(new Token(token, solicitante));
        return token;
    }

    // AJUSTE: retorna Object porque agora pode vir um UsuarioLogado OU um SolicitanteLogado -
    // quem decide qual e o JwtFilter, checando com instanceof.
    public Object verificarToken(String token) throws JWTVerificationException {

        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verificador = JWT.require(algorithm).withIssuer(emissor).build();
        verificador.verify(token);

        var tokenBanco = tokenRepository.findByToken(token).orElse(null);
        if (tokenBanco == null){
            throw new IllegalArgumentException("Token Inválido!");
        }

        if (tokenBanco.getUsuario() != null) {
            return new UsuarioLogado(tokenBanco.getUsuario());
        }
        return new SolicitanteLogado(tokenBanco.getSolicitante());
    }

    private Instant getDataExpiracao(){
        var dataAtual = LocalDateTime.now();
        var dataFutura = dataAtual.plusMinutes(expiracao);
        return dataFutura.toInstant(ZoneOffset.of("-03:00"));
    }
}