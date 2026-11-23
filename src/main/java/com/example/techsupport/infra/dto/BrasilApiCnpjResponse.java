package com.example.techsupport.infra.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// Resposta da BrasilAPI. @JsonProperty liga os nomes do JSON (razao_social) aos campos Java; o resto do JSON e ignorado.
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrasilApiCnpjResponse {

    private String cnpj;

    @JsonProperty("razao_social")
    private String razaoSocial;

    @JsonProperty("nome_fantasia")
    private String nomeFantasia;

    @JsonProperty("descricao_situacao_cadastral")
    private String situacaoCadastral;

}
