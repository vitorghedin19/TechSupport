// ==========================================================================
// ARQUIVO: JwtFilter.java  -> FILTRO DE SEGURANÇA (guarda da API).
// Um filtro roda ANTES do controller em toda requisição. A ideia: rotas protegidas só passam se vierem
// com o header  "Authorization: Bearer <token>"  contendo um JWT válido (gerado no /auth/login).
//
// FLUXO PRETENDIDO:
//   requisição -> JwtFilter -> (token ok?) -> sim: Controller | não: responde 401 e para aqui.
//
// !!! ATENÇÃO (ponto que o professor pode perguntar) !!!
// Na condição do primeiro "if" existe  uri.startsWith("/")  . TODA uri começa com "/", então essa
// condição é SEMPRE verdadeira: toda requisição cai no "return" e o token NUNCA é validado.
// Na prática, hoje a API está aberta (sem autenticação). Só comentei, não alterei o código.
// Para o filtro funcionar de verdade, essa linha teria de ser removida (ou trocada por uma regra específica).
// ==========================================================================
package com.example.techsupport.configuration;

import com.example.techsupport.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// @Component: o Spring cria este objeto (bean) sozinho e, por ser um Filter, o registra na cadeia de filtros.
@Component

// OncePerRequestFilter: classe base do Spring que garante que o filtro executa UMA vez por requisição.
public class JwtFilter extends OncePerRequestFilter {

    // @Autowired = injeção de dependência: o Spring entrega aqui o objeto TokenService já pronto (não usamos "new").
    @Autowired
    private TokenService tokenService;

    // Método que o Spring chama a cada requisição. Recebe a requisição, a resposta e a "cadeia" (filterChain)
    // que representa o próximo passo (outros filtros e, no fim, o controller).
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Caminho que o cliente chamou, ex.: "/chamado/3" ou "/auth/login".
        String uri = request.getRequestURI();

        // Lista de rotas PÚBLICAS (não exigem token): documentação Swagger e o login (que é quem gera o token).
        // Se a uri começar com algum desses prefixos, o filtro deixa passar direto.
        // (A última linha, startsWith("/"), é a que torna tudo público - veja o aviso no topo do arquivo.
        //  Também: "webjars" está sem a barra inicial, então essa linha nunca casaria.)
        if (uri.startsWith("/swagger-ui")
        || uri.startsWith("/v2/api-docs")
        || uri.startsWith("/v3/api-docs")
        || uri.startsWith("/swagger-resources")
        || uri.startsWith("webjars")
        || uri.startsWith("/auth/login")
        || uri.startsWith("/")
        ){
            // Segue para o próximo passo da cadeia (controller) e encerra este método com return.
            filterChain.doFilter(request,response);
            return;
        }

        // Lê o header Authorization. O padrão é:  Authorization: Bearer eyJhbGciOi...
        String authHeader = request.getHeader("Authorization");

        // Só aceita se o header existe E começa com "Bearer " (esquema padrão para tokens).
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // Tira o texto "Bearer " e fica só com o token puro.
            String token = authHeader.replace("Bearer ", "");

            try {

                // Valida o token: confere assinatura (chave secreta), emissor e se não expirou.
                // Se algo estiver errado, o verificarToken LANÇA exceção e cai no catch.
                var jwtValidador = tokenService.verificarToken(token);

                // Imprime no console o "subject" do token = o e-mail do usuário logado (só para acompanhar).
                System.out.println(jwtValidador.getSubject());

            }catch (Exception e){
                // Token inválido/expirado/adulterado: responde 401 (Unauthorized) e PARA (return),
                // ou seja, a requisição não chega no controller.
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().println("Token inválido");
                return;
            }

        }else
        {
        // Não veio header Authorization (ou não é Bearer): também responde 401 e para.
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().println("Token inválido");
        return;
        }

        // Chegou aqui = token válido. Libera a requisição para o próximo elemento da cadeia (o controller).
        filterChain.doFilter(request,response);

    }
}
