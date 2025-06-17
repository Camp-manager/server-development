package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.estoque.AdicionarItensEstoqueUC;
import com.camp.manager.application.usecases.estoque.BuscarItensPorEstoqueUC;
import com.camp.manager.application.usecases.estoque.BuscarTodosCompradoresCamisaUC;
import com.camp.manager.infra.http.dto.estoque.CompradoresCamisetaDTO;
import com.camp.manager.infra.http.dto.estoque.EstoqueDTO;
import com.camp.manager.infra.http.request.estoque.AdicionarItensRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private final BuscarTodosCompradoresCamisaUC buscarTodosCompradoresCamisaUC;
    private final AdicionarItensEstoqueUC adicionarItensEstoqueUC;
    private final BuscarItensPorEstoqueUC buscarItensPorEstoqueUC;

    @Autowired
    public EstoqueController(BuscarTodosCompradoresCamisaUC buscarTodosCompradoresCamisaUC,
                             AdicionarItensEstoqueUC adicionarItensEstoqueUC,
                             BuscarItensPorEstoqueUC buscarItensPorEstoqueUC) {
        this.buscarTodosCompradoresCamisaUC = buscarTodosCompradoresCamisaUC;
        this.adicionarItensEstoqueUC = adicionarItensEstoqueUC;
        this.buscarItensPorEstoqueUC = buscarItensPorEstoqueUC;
    }

    @GetMapping("/compradores-camisa/{idAcampamento}")
    public ResponseEntity<CompradoresCamisetaDTO> buscarTodosCompradoresCamisa(@PathVariable Long idAcampamento) {
        var response = this.buscarTodosCompradoresCamisaUC.execute(idAcampamento);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @GetMapping
    public ResponseEntity<List<EstoqueDTO>> buscarTodosEstoques() {
        var response = this.buscarItensPorEstoqueUC.execute(null);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @PutMapping("/adicionar-itens")
    public ResponseEntity<Void> adicionarItensEstoque(@RequestBody AdicionarItensRequest request) {
        var response = this.adicionarItensEstoqueUC.execute(request);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }
}
