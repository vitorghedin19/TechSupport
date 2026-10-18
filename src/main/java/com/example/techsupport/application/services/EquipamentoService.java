package com.example.techsupport.application.services;

import com.example.techsupport.application.DTOs.EnumStatusEquipamento;
import com.example.techsupport.application.DTOs.EquipamentoRequest;
import com.example.techsupport.application.DTOs.EquipamentoResponse;
import com.example.techsupport.domain.entities.Equipamento;
import com.example.techsupport.domain.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;


    public List<EquipamentoResponse> listarTodosEquipamentosTable(){

        return equipamentoRepository.findAll()
                .stream()
                .map(EquipamentoResponse::new)
                .toList();
    }

    public EquipamentoResponse buscarPorId(Long id) {
        var equipamento = equipamentoRepository.findById(id).orElse(null);
        if (equipamento == null){
            return null;
        }
        return new EquipamentoResponse(equipamento);
    }

    public EquipamentoResponse salvarEquipamento(EquipamentoRequest equipamento, Long id) {

        //Novo Equipamento
        if (id == 0L){
            Equipamento equipamentoBanco = new Equipamento(equipamento);
            equipamentoRepository.save(equipamentoBanco);
            return new EquipamentoResponse(equipamentoBanco);
        }
        else {
            //Equipamento existente
            Equipamento equipamentoBanco = equipamentoRepository.findById(id).orElse(null);
            if ( equipamentoBanco != null ){
                equipamentoBanco.setEquipamento(equipamento.equipamento());
                equipamentoBanco.setTipo(equipamento.tipo());
                equipamentoRepository.save(equipamentoBanco);
                return new EquipamentoResponse(equipamentoBanco);
            }
        }
        return null;
    }

    public boolean atualizarStatus(EnumStatusEquipamento status, Long id) {

        Equipamento equipamentoBanco = equipamentoRepository.findById(id).orElse(null);
        if (equipamentoBanco != null){
            equipamentoBanco.setStatus(status);
            equipamentoRepository.save(equipamentoBanco);
            return true;
        }
        return false;
    }
}