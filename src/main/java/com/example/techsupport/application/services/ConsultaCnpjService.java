package com.example.techsupport.application.services;

import com.example.techsupport.application.DTOs.ConsultaCnpjResponse;
import com.example.techsupport.domain.interfaces.IConsultaCnpjExternalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaCnpjService {

    @Autowired
    private IConsultaCnpjExternalRepository consultaCnpjExternalRepository;

    public ConsultaCnpjResponse buscarEmpresa(String cnpj) {

        var numeros = cnpj.replaceAll("[^0-9]", "");

        if (numeros.length() != 14) {
            throw new IllegalArgumentException("CNPJ inválido!");
        }

        var empresa = consultaCnpjExternalRepository.buscarEmpresaPorCnpj(numeros);

        var nomeFantasia = empresa.getNomeFantasia() == null || empresa.getNomeFantasia().isBlank()
                ? empresa.getRazaoSocial()
                : empresa.getNomeFantasia();

        return new ConsultaCnpjResponse(
                empresa.getCnpj(),
                empresa.getRazaoSocial(),
                nomeFantasia,
                empresa.getSituacaoCadastral());
    }
}
