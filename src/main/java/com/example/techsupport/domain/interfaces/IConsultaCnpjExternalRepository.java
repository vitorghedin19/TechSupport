package com.example.techsupport.domain.interfaces;

import com.example.techsupport.infra.dto.BrasilApiCnpjResponse;

public interface IConsultaCnpjExternalRepository {

    BrasilApiCnpjResponse buscarEmpresaPorCnpj(String cnpj);

}
