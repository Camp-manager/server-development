package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.acampamento.AdicionarAcampamentoUC;
import com.camp.manager.application.usecases.acampamento.BuscarAcampamentoBasicoUC;
import com.camp.manager.application.usecases.acampamento.BuscarAcampamentoCompletoUC;
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
    private final BuscarAcampamentoCompletoUC buscarAcampamentoCompletoUC;
    private final BuscarAcampamentoBasicoUC buscarAcampamentoBasicoUC;

    @Autowired
    public AcampamentoController(AdicionarAcampamentoUC adicionarAcampamentoUC,
                                 BuscarAcampamentosUC buscarAcampamentosUC,
                                 BuscarAcampamentoCompletoUC buscarAcampamentoCompletoUC,
                                 BuscarAcampamentoBasicoUC buscarAcampamentoBasicoUC) {
        this.adicionarAcampamentoUC = adicionarAcampamentoUC;
        this.buscarAcampamentosUC = buscarAcampamentosUC;
        this.buscarAcampamentoCompletoUC = buscarAcampamentoCompletoUC;
        this.buscarAcampamentoBasicoUC = buscarAcampamentoBasicoUC;
    }

    @PostMapping(path = "/adicionar")
    public ResponseEntity<Void> adicionarAcampamento(@RequestBody @Valid CriarAcampamentoRequest request) {
        var response = this.adicionarAcampamentoUC.execute(request);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @GetMapping(path = "/buscar-todos")
    public ResponseEntity<List<AcampamentoBasicoDTO>> buscarTodosAcampamentos() {
        var response = this.buscarAcampamentosUC.execute(null);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @GetMapping(path = "/buscar-completo/{id}")
    public ResponseEntity<AcampamentoCompletoDTO> buscarAcampamentoCompleto(@PathVariable Long id) {
        var response = this.buscarAcampamentoCompletoUC.execute(id);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @GetMapping(path = "/buscar-basico/{id}")
    public ResponseEntity<AcampamentoBasicoDTO> buscarAcampamentoBasico(@PathVariable Long id) {
        var response = this.buscarAcampamentoBasicoUC.execute(id);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

}
