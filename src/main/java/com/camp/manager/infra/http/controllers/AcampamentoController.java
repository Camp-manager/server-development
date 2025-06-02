package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.acampamento.AdicionarAcampamentoUC;
import com.camp.manager.application.usecases.acampamento.BuscarAcampamentoUC;
import com.camp.manager.infra.http.dto.acampamento.AcampamentoDTO;
import com.camp.manager.infra.http.request.acampamento.CriarAcampamentoRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/acampamento")
public class AcampamentoController {

    private final AdicionarAcampamentoUC adicionarAcampamentoUC;
    private final BuscarAcampamentoUC buscarAcampamentosUC;

    @Autowired
    public AcampamentoController(AdicionarAcampamentoUC adicionarAcampamentoUC,
                                 BuscarAcampamentoUC buscarAcampamentosUC) {
        this.adicionarAcampamentoUC = adicionarAcampamentoUC;
        this.buscarAcampamentosUC = buscarAcampamentosUC;
    }

    @PostMapping(path = "/adicionar")
    public ResponseEntity<Void> adicionarAcampamento(@RequestBody @Valid CriarAcampamentoRequest request) {
        var response = this.adicionarAcampamentoUC.execute(request);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @GetMapping(path = "/buscar-todos")
    public ResponseEntity<List<AcampamentoDTO>> buscarAcampamentos() {
        var response = this.buscarAcampamentosUC.execute(null);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

}
