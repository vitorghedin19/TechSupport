package com.example.techsupport.application.services;

import com.example.techsupport.application.DTOs.*;
import com.example.techsupport.domain.entities.Empresa;
import com.example.techsupport.domain.entities.Usuario;
import com.example.techsupport.domain.repository.EmpresaRepository;
import com.example.techsupport.domain.repository.UsuarioRepository;
import com.example.techsupport.domain.valueobject.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Regras de Usuario (tecnico/admin): login, CRUD filtrado pela empresa do token e criacao via desktop (secretKey).
// Admin so consulta (a rota de escrita e bloqueada no SecurityConfiguration); tecnico nao enxerga admins.
@Service
public class UsuarioService {

    @Value("${spring.secret}")
    private String secret;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ContextoSegurancaService contexto;

    public LoginResponse validarUsuarioAutenticadoRetornaToken (LoginRequest request) {

        // So loga usuario ATIVO; bloqueado/excluido cai no login invalido (null).
        if (usuarioRepository.existsUsuarioByEmailAndSenhaAndStatus(request.email(), request.senha(), EnumStatusUsuario.ATIVO)){
            var token = tokenService.gerarTokenUsuario(request);
            return new LoginResponse(token);
        }
        return null;
    }

    // Admin acessa qualquer um; tecnico so usuarios (nao admin) da propria empresa.
    private boolean podeAcessar(Usuario usuario){
        if (contexto.isAdmin()){
            return true;
        }
        return !"ROLE_ADMIN".equals(usuario.getRole())
                && contexto.podeAcessar(usuario.getEmpresa());
    }

    public List<UsuarioResponse> listarTodosUsuarioTable(){

        List<Usuario> usuarios;

        if (contexto.isAdmin()){
            usuarios = usuarioRepository.findAll();
        } else {
            Long empresaId = contexto.getEmpresaId();
            usuarios = empresaId == null
                    ? List.of()
                    : usuarioRepository.getUsuariosByEmpresa_Id(empresaId)
                    .stream()
                    .filter(u -> !"ROLE_ADMIN".equals(u.getRole()))
                    .toList();
        }

        return usuarios
                .stream()
                .map(UsuarioResponse::new)
                .toList();

    }

    // Usado pelo desktop: so cria se a secretKey for igual ao spring.secret.
    public CriarAdminResponse criarAdmin(CriarAdminRequest criarAdminRequest) {

        if (!criarAdminRequest.secretKey().equals(secret)){
            return new CriarAdminResponse(0L, "Usuario Salvo com sucesso!");
        }

        Usuario usuarioAdminSalvar = new Usuario(criarAdminRequest);

        usuarioRepository.save(usuarioAdminSalvar);

        return new CriarAdminResponse(usuarioAdminSalvar.getId(), "Usuário salvo com sucesso!");

    }

    // Usado pelo desktop: cria a empresa e o tecnico dela juntos (@Transactional: se um falhar, nenhum e salvo).
    @Transactional
    public CriarTecnicoEmpresaResponse criarTecnicoComEmpresa(CriarTecnicoEmpresaRequest request) {

        if (!secret.equals(request.secretKey())) {
            throw new RuntimeException("Chave secreta inválida!");
        }

        Empresa empresa = new Empresa();
        empresa.setRazaoSocial(request.razaoSocial());
        empresa.setNomeFantasia(request.nomeFantasia());
        empresa.setCnpj(request.cnpj());
        empresaRepository.save(empresa);

        Usuario tecnico = new Usuario(request, empresa);
        usuarioRepository.save(tecnico);

        return new CriarTecnicoEmpresaResponse(tecnico.getId(), empresa.getId(), "Técnico e empresa salvos com sucesso!");

    }

    public UsuarioLogado getUsuarioLogado(){
        return (UsuarioLogado) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public UsuarioResponse salvarUsuario(UsuarioRequest usuario, Long id) {

        // id 0 = novo usuario, ja na empresa de quem esta logado; senao edita o existente.
        if (id == 0L){
            Usuario usuarioBanco = new Usuario(usuario);
            usuarioBanco.setEmpresa(contexto.getEmpresaDoLogado());
            usuarioRepository.save(usuarioBanco);

            return new UsuarioResponse(usuarioBanco);
        } else {
            Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);
            if ( usuarioBanco != null && podeAcessar(usuarioBanco) ){
                usuarioBanco.setCpf(new CPF(usuario.cpf()));
                usuarioBanco.setEmail(usuario.email());
                usuarioBanco.setNome(usuario.nome());
                usuarioBanco.setSenha(usuario.senha());
                usuarioRepository.save(usuarioBanco);
                return new UsuarioResponse(usuarioBanco);
            }
            return null;
        }
    }

    public UsuarioResponse buscarPorId(Long id) {

        var usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null || !podeAcessar(usuario)){
            return null;
        }
        return new UsuarioResponse(usuario);
    }

    public boolean atualizarStatus(EnumStatusUsuario statusUsuario, Long id){

        Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);
        if (usuarioBanco!=null && podeAcessar(usuarioBanco)){
            usuarioBanco.setStatus(statusUsuario);
            usuarioRepository.save(usuarioBanco);
            return true;
        }
        return false;
    }

    public UsuarioResponse BuscarUsuarioLogado() {

        try {

            var usuarioLogado = getUsuarioLogado();
            return usuarioRepository.findById(usuarioLogado.id()).stream().map(UsuarioResponse::new).findFirst().orElse(null);

        }catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário logado");
        }
    }
}