package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.BuscarTamanhosCamisaUC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/camisetas")
public class CamisetaController {

    private final BuscarTamanhosCamisaUC buscarTamanhosCamisaUC;

    public CamisetaController(BuscarTamanhosCamisaUC buscarTamanhosCamisaUC) {
        this.buscarTamanhosCamisaUC = buscarTamanhosCamisaUC;
    }

    @GetMapping(path = "/buscar-tamanhos")
    public ResponseEntity<List<String>> buscarTamanhosCamisa() {
        return ResponseEntity.ok(this.buscarTamanhosCamisaUC.execute(null));
    }
}
