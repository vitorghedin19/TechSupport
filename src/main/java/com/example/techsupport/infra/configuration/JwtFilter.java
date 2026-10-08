// Filtro de segurança: roda ANTES de todo controller, em toda requisição. A ideia é: só deixar passar
// quem mandar um token válido no header "Authorization: Bearer <token>" (o token vem do login).
//
// AVISO: no "if" de baixo tem uma condição "uri.startsWith("/")", que é verdadeira pra QUALQUER rota
// (toda URL começa com "/"). Isso faz o filtro sempre liberar direto, sem checar o token. Ou seja,
// hoje a API está sem essa proteção ativa, mesmo o código existindo.
package com.example.techsupport.infra.configuration;

import com.example.techsupport.application.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();

        // Rotas que não precisam de token (Swagger e o próprio login).
        if (uri.startsWith("/swagger-ui")
        || uri.startsWith("/v2/api-docs")
        || uri.startsWith("/v3/api-docs")
        || uri.startsWith("/swagger-resources")
        || uri.startsWith("webjars")
        || uri.startsWith("/auth/login")
        || uri.startsWith("/")
        ){
            filterChain.doFilter(request,response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.replace("Bearer ", "");

            try {
                // Confere se o token é válido (assinatura, emissor e se não venceu).
                var jwtValidador = tokenService.verificarToken(token);
                System.out.println(jwtValidador.getSubject());

            }catch (Exception e){
                // Token inválido/vencido: barra a requisição com 401.
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().println("Token inválido");
                return;
            }

        }else
        {
        // Sem token: barra a requisição com 401.
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().println("Token inválido");
        return;
        }

        // Token ok: deixa a requisição seguir para o controller.
        filterChain.doFilter(request,response);

    }
}
