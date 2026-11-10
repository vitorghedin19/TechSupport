package com.example.techsupport.presentation;

import com.example.techsupport.application.services.ConsultaCnpjService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
@Tag(name = "Empresa", description = "Endpoints de apoio ao cadastro de empresas, com consulta externa de CNPJ.")
public class EmpresaController {

    @Autowired
    private ConsultaCnpjService consultaCnpjService;

    @GetMapping("/consulta-cnpj/{cnpj}")
    @Operation(summary = "Consulta de empresa por CNPJ!", description = "Busca razão social e nome fantasia na BrasilAPI a partir do CNPJ informado (somente números).")
    public ResponseEntity<?> consultarCnpj(@PathVariable String cnpj) {
        try {
            return ResponseEntity.ok(consultaCnpjService.buscarEmpresa(cnpj));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
