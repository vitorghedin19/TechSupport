package com.example.techsupport.application.services;

import com.example.techsupport.application.DTOs.ChamadoRequest;
import com.example.techsupport.application.DTOs.ChamadoResponse;
import com.example.techsupport.application.DTOs.EnumStatusChamado;
import com.example.techsupport.domain.entities.Chamado;
import com.example.techsupport.domain.repository.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;


    public List<ChamadoResponse> listarTodosChamadosTable(){

        return chamadoRepository.findAll()
                .stream()
                .map(ChamadoResponse::new)
                .toList();

    }

    public ChamadoResponse buscarPorId(Long id) {
        var chamado = chamadoRepository.findById(id).orElse(null);
        if (chamado == null){
            return null;
        }
        return new ChamadoResponse(chamado);
    }

    public ChamadoResponse salvarChamado(ChamadoRequest chamado, Long id) {

        //Novo Chamado
        if (id == 0L){
            Chamado chamadoBanco = new Chamado(chamado);
            chamadoRepository.save(chamadoBanco);
            return new ChamadoResponse(chamadoBanco);
        }
        else {
            //Chamado existente
            Chamado chamadoBanco = chamadoRepository.findById(id).orElse(null);
            if ( chamadoBanco != null ){
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

        Chamado chamadoBanco = chamadoRepository.findById(id).orElse(null);
        if (chamadoBanco != null){
            chamadoBanco.setStatus(status);
            chamadoRepository.save(chamadoBanco);
            return true;
        }
        return false;
    }
}