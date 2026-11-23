package com.example.techsupport.domain.interfaces;

import com.example.techsupport.infra.dto.BrasilApiCnpjResponse;

// Porta (DDD) da integracao externa de CNPJ: o dominio so conhece esta interface; a implementacao fica em infra/external.
public interface IConsultaCnpjExternalRepository {

    BrasilApiCnpjResponse buscarEmpresaPorCnpj(String cnpj);

}
