package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.acampamento.AdicionarAcampamentoUC;
import com.camp.manager.infra.http.request.acampamento.CriarAcampamentoRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/acampamento")
public class AcampamentoController {

    private final AdicionarAcampamentoUC adicionarAcampamentoUC;

    @Autowired
    public AcampamentoController(AdicionarAcampamentoUC adicionarAcampamentoUC) {
        this.adicionarAcampamentoUC = adicionarAcampamentoUC;
    }

    @PostMapping(path = "/adicionar")
    public ResponseEntity<Void> adicionarAcampamento(@RequestBody @Valid CriarAcampamentoRequest request) {
        var response = this.adicionarAcampamentoUC.execute(request);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

}
