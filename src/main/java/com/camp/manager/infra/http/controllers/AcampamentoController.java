package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.acampamento.AdicionarAcampamentoUC;
import com.camp.manager.application.usecases.acampamento.BuscarAcampamentoUC;
import com.camp.manager.application.usecases.acampamento.BuscarAcampamentosUC;
import com.camp.manager.infra.http.dto.acampamento.AcampamentoBasicoDTO;
import com.camp.manager.infra.http.dto.acampamento.AcampamentoCompletoDTO;
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
    private final BuscarAcampamentosUC buscarAcampamentosUC;
    private final BuscarAcampamentoUC buscarAcampamentoUC;

    @Autowired
    public AcampamentoController(AdicionarAcampamentoUC adicionarAcampamentoUC,
                                 BuscarAcampamentosUC buscarAcampamentosUC,
                                 BuscarAcampamentoUC buscarAcampamentoUC) {
        this.adicionarAcampamentoUC = adicionarAcampamentoUC;
        this.buscarAcampamentosUC = buscarAcampamentosUC;
        this.buscarAcampamentoUC = buscarAcampamentoUC;
    }

    @PostMapping(path = "/adicionar")
    public ResponseEntity<Void> adicionarAcampamento(@RequestBody @Valid CriarAcampamentoRequest request) {
        var response = this.adicionarAcampamentoUC.execute(request);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @GetMapping(path = "/buscar-todos")
    public ResponseEntity<List<AcampamentoBasicoDTO>> buscarAcampamentos() {
        var response = this.buscarAcampamentosUC.execute(null);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @GetMapping(path = "/buscar/{id}")
    public ResponseEntity<AcampamentoCompletoDTO> buscarAcampamentos(@PathVariable Long id) {
        var response = this.buscarAcampamentoUC.execute(id);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

}
