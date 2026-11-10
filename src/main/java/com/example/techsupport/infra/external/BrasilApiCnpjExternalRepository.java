package com.example.techsupport.infra.external;

import com.example.techsupport.domain.interfaces.IConsultaCnpjExternalRepository;
import com.example.techsupport.infra.dto.BrasilApiCnpjResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
public class BrasilApiCnpjExternalRepository implements IConsultaCnpjExternalRepository {

    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10)).build();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public BrasilApiCnpjResponse buscarEmpresaPorCnpj(String cnpj) {
        var url = "https://brasilapi.com.br/api/cnpj/v1/" + cnpj.replaceAll("[^0-9]", "");
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), BrasilApiCnpjResponse.class);
            }
            if (response.statusCode() == 400 || response.statusCode() == 404) {
                throw new RuntimeException("CNPJ inválido ou não encontrado!");
            }
            throw new RuntimeException("Erro ao consultar CNPJ!");
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar CNPJ!");
        }
    }
}
