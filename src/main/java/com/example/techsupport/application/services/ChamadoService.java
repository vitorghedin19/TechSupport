package com.example.techsupport.application.services;

import com.example.techsupport.application.DTOs.ChamadoRequest;
import com.example.techsupport.application.DTOs.ChamadoResponse;
import com.example.techsupport.application.DTOs.EnumStatusChamado;
import com.example.techsupport.application.DTOs.EnumStatusEquipamento;
import com.example.techsupport.application.DTOs.SolicitanteLogado;
import com.example.techsupport.domain.entities.Chamado;
import com.example.techsupport.domain.entities.Equipamento;
import com.example.techsupport.domain.repository.ChamadoRepository;
import com.example.techsupport.domain.repository.EquipamentoRepository;
import com.example.techsupport.domain.repository.SolicitanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private SolicitanteRepository solicitanteRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private ContextoSegurancaService contexto;

    // Solicitante so acessa os chamados que ele mesmo abriu.
    // Tecnico acessa os da empresa dele. Admin acessa todos.
    private boolean podeAcessar(Chamado chamado){
        if (contexto.getLogado() instanceof SolicitanteLogado solicitanteLogado){
            return chamado.getSolicitante() != null
                    && chamado.getSolicitante().getId().equals(solicitanteLogado.id());
        }
        return contexto.podeAcessar(chamado.getEmpresa());
    }

    // O equipamento escolhido no chamado precisa existir, nao estar excluido e ser da empresa de quem esta logado.
    // Sem id (null) = chamado sem equipamento, que e permitido.
    private Equipamento buscarEquipamentoValido(Long equipamentoId){

        if (equipamentoId == null){
            return null;
        }

        Equipamento equipamento = equipamentoRepository.findById(equipamentoId).orElse(null);

        if (equipamento == null
                || equipamento.getStatus() == EnumStatusEquipamento.EXCLUIDO
                || !contexto.podeAcessar(equipamento.getEmpresa())){
            throw new IllegalArgumentException("Equipamento inválido!");
        }

        return equipamento;
    }

    public List<ChamadoResponse> listarTodosChamadosTable(){

        List<Chamado> chamados;

        if (contexto.getLogado() instanceof SolicitanteLogado solicitanteLogado){
            chamados = chamadoRepository.findBySolicitante_Id(solicitanteLogado.id());
        } else if (contexto.isAdmin()) {
            chamados = chamadoRepository.findAll();
        } else {
            Long empresaId = contexto.getEmpresaId();
            chamados = empresaId == null ? List.of() : chamadoRepository.findByEmpresa_Id(empresaId);
        }

        return chamados
                .stream()
                .map(ChamadoResponse::new)
                .toList();

    }

    public ChamadoResponse buscarPorId(Long id) {
        var chamado = chamadoRepository.findById(id).orElse(null);
        if (chamado == null || !podeAcessar(chamado)){
            return null;
        }
        return new ChamadoResponse(chamado);
    }

    public ChamadoResponse salvarChamado(ChamadoRequest chamado, Long id) {

        //Novo Chamado
        if (id == 0L){
            Chamado chamadoBanco = new Chamado(chamado);
            chamadoBanco.setEmpresa(contexto.getEmpresaDoLogado());
            chamadoBanco.setEquipamento(buscarEquipamentoValido(chamado.equipamentoId()));

            if (contexto.getLogado() instanceof SolicitanteLogado solicitanteLogado){
                chamadoBanco.setSolicitante(solicitanteRepository.getReferenceById(solicitanteLogado.id()));
            }

            chamadoRepository.save(chamadoBanco);
            return new ChamadoResponse(chamadoBanco);
        }
        else {
            //Chamado existente: so edita titulo, descricao, prioridade e equipamento (status e solucao ficam de fora)
            Chamado chamadoBanco = chamadoRepository.findById(id).orElse(null);
            if ( chamadoBanco != null && podeAcessar(chamadoBanco) ){

                Long equipamentoAtualId = chamadoBanco.getEquipamento() != null ? chamadoBanco.getEquipamento().getId() : null;

                if (!Objects.equals(equipamentoAtualId, chamado.equipamentoId())){
                    chamadoBanco.setEquipamento(buscarEquipamentoValido(chamado.equipamentoId()));
                }

                chamadoBanco.setTitulo(chamado.titulo());
                chamadoBanco.setDescricao(chamado.descricao());
                chamadoBanco.setPrioridade(chamado.prioridade());
                chamadoRepository.save(chamadoBanco);
                return new ChamadoResponse(chamadoBanco);
            }
        }
        return null;
    }

    public boolean atualizarStatus(EnumStatusChamado status, Long id) {

        if (status == EnumStatusChamado.RESOLVIDO) {
            throw new IllegalArgumentException("Para resolver um chamado, informe a solução pelo endpoint de resolver!");
        }

        Chamado chamadoBanco = chamadoRepository.findById(id).orElse(null);
        if (chamadoBanco != null && podeAcessar(chamadoBanco)){
            chamadoBanco.setStatus(status);
            chamadoRepository.save(chamadoBanco);
            return true;
        }
        return false;
    }

    // Quem decide se pode resolver ou nao e o metodo resolver() la na entidade -
    // aqui so orquestra a busca e o save.
    public ChamadoResponse resolver(Long id, String solucaoAdotada) {

        Chamado chamadoBanco = chamadoRepository.findById(id).orElse(null);
        if (chamadoBanco == null || !podeAcessar(chamadoBanco)) {
            return null;
        }

        chamadoBanco.resolver(solucaoAdotada); // pode lancar IllegalArgumentException
        chamadoRepository.save(chamadoBanco);
        return new ChamadoResponse(chamadoBanco);
    }
}