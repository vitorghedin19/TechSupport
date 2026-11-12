package com.example.techsupport.infra.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{

        return http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-resources/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // rotas usadas pelo desktop (bootstrapping), sem token
                        .requestMatchers(HttpMethod.POST,
                                "/usuarios/admin",
                                "/usuarios/tecnico-empresa",
                                "/comunicacao/criar"
                        ).permitAll()

                        // consulta externa de CNPJ usada pelo desktop no cadastro de empresa, sem token
                        .requestMatchers(HttpMethod.GET, "/empresa/consulta-cnpj/*")
                        .permitAll()

                        .requestMatchers("/usuarios/buscarusuariologado")
                        .hasAnyRole("USER", "ADMIN")

                        .requestMatchers("/solicitante/buscarsolicitantelogado")
                        .hasRole("SOLICITANTE")

                        // solicitante pode LISTAR equipamentos (so da empresa dele, o service filtra) para escolher no chamado
                        // e LISTAR/VER chamados (so os dele, o service filtra); admin tambem pode consultar
                        .requestMatchers(HttpMethod.GET, "/equipamento", "/chamado", "/chamado/*")
                        .hasAnyRole("USER", "ADMIN", "SOLICITANTE")

                        // solicitante pode CRIAR e EDITAR chamado (o service confere se e dele); admin NAO escreve
                        .requestMatchers(HttpMethod.POST, "/chamado")
                        .hasAnyRole("USER", "SOLICITANTE")

                        .requestMatchers(HttpMethod.PUT, "/chamado/*")
                        .hasAnyRole("USER", "SOLICITANTE")

                        // admin so CONSULTA: qualquer outro GET e liberado para tecnico e admin
                        .requestMatchers(HttpMethod.GET, "/**")
                        .hasAnyRole("USER", "ADMIN")

                        // todo o resto (cadastrar, editar, excluir, resolver, mudar status) e so do tecnico
                        .anyRequest().hasRole("USER")
                )
                .addFilterBefore( jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}