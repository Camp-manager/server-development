package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.BuscarMedicamentosUC;
import com.camp.manager.infra.http.dto.medicamento.MedicamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medicamento")
public class MedicamentoController {

    private final BuscarMedicamentosUC buscarMedicamentosUC;

    @Autowired
    public MedicamentoController(BuscarMedicamentosUC buscarMedicamentosUC) {
        this.buscarMedicamentosUC = buscarMedicamentosUC;
    }

    @GetMapping(path = "/buscar-todos")
    public ResponseEntity<List<MedicamentoDTO>> buscarMedicamentos() {
        var response = this.buscarMedicamentosUC.execute(null);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }
}
