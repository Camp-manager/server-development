package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.BuscarTamanhosCamisaUC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/camiseta")
public class CamisetaController {

    private final BuscarTamanhosCamisaUC buscarTamanhosCamisaUC;

    @Autowired
    public CamisetaController(BuscarTamanhosCamisaUC buscarTamanhosCamisaUC) {
        this.buscarTamanhosCamisaUC = buscarTamanhosCamisaUC;
    }

    @GetMapping(path = "/buscar-tamanhos")
    public ResponseEntity<List<String>> buscarTamanhosCamisa() {
        var response = this.buscarTamanhosCamisaUC.execute(null);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }
}
