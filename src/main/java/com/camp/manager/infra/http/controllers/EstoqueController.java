package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.estoque.BuscarTodosCompradoresCamisaUC;
import com.camp.manager.infra.http.dto.estoque.CompradoresCamisetaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private final BuscarTodosCompradoresCamisaUC buscarTodosCompradoresCamisaUC;

    @Autowired
    public EstoqueController(BuscarTodosCompradoresCamisaUC buscarTodosCompradoresCamisaUC) {
        this.buscarTodosCompradoresCamisaUC = buscarTodosCompradoresCamisaUC;
    }

    @GetMapping("/compradores-camisa/{idAcampamento}")
    public ResponseEntity<CompradoresCamisetaDTO> buscarTodosCompradoresCamisa(@PathVariable Long idAcampamento) {
        var response = this.buscarTodosCompradoresCamisaUC.execute(idAcampamento);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }
}
