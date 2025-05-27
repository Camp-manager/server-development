package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.tipoacampamento.AdicionarTipoAcampamentoUC;
import com.camp.manager.application.usecases.tipoacampamento.BuscarCategoriasTipoAcampamentoUC;
import com.camp.manager.application.usecases.tipoacampamento.BuscarTipoAcampamentosUC;
import com.camp.manager.application.usecases.tipoacampamento.DeletarTipoAcampanentoUC;
import com.camp.manager.infra.http.dto.tipoacampamento.TipoAcampamentoDTO;
import com.camp.manager.infra.http.request.tipoacampamento.CriarTipoAcampamentoRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-acampamento")
public class TipoAcampamentoController {

    private final BuscarTipoAcampamentosUC buscarTipoAcampamentoUC;
    private final BuscarCategoriasTipoAcampamentoUC buscarCategoriasTipoAcampamentoUC;
    private final AdicionarTipoAcampamentoUC adicionarTipoAcampamentoUC;
    private final DeletarTipoAcampanentoUC deletarTipoAcampanentoUC;

    @Autowired
    public TipoAcampamentoController(BuscarTipoAcampamentosUC buscarTipoAcampamentoUC,
                                     BuscarCategoriasTipoAcampamentoUC buscarCategoriasTipoAcampamentoUC,
                                     AdicionarTipoAcampamentoUC adicionarTipoAcampamentoUC,
                                     DeletarTipoAcampanentoUC deletarTipoAcampanentoUC) {
        this.buscarTipoAcampamentoUC = buscarTipoAcampamentoUC;
        this.buscarCategoriasTipoAcampamentoUC = buscarCategoriasTipoAcampamentoUC;
        this.adicionarTipoAcampamentoUC = adicionarTipoAcampamentoUC;
        this.deletarTipoAcampanentoUC = deletarTipoAcampanentoUC;
    }

    @GetMapping(path = "/buscar-todos")
    public ResponseEntity<List<TipoAcampamentoDTO>> buscarTiposDeAcampamento() {
        var response = this.buscarTipoAcampamentoUC.execute(null);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @GetMapping(path = "/buscar-categorias")
    public ResponseEntity<List<String>> buscarCategoriasTipoAcampamento() {
        var response = this.buscarCategoriasTipoAcampamentoUC.execute(null);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @PostMapping(path = "/criar-tipo-acampamento")
    public ResponseEntity<Void> criarTipoAcampamento(@RequestBody @Valid CriarTipoAcampamentoRequest tipoAcampamentoRequest) {
        var response = this.adicionarTipoAcampamentoUC.execute(tipoAcampamentoRequest);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @DeleteMapping(path = "/deletar-tipo-acampamento/{idTipoAcampamento}")
    public ResponseEntity<Void> deletarTipoAcampamento(@PathVariable Long idTipoAcampamento) {
        var response = this.deletarTipoAcampanentoUC.execute(idTipoAcampamento);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }
}
