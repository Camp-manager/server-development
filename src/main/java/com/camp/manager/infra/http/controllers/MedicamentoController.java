package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.BuscarMedicamentosUC;
import com.camp.manager.infra.http.dto.medicamento.MedicamentoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medicamento")
public class MedicamentoController {

    private final BuscarMedicamentosUC buscarMedicamentosUC;

    public MedicamentoController(BuscarMedicamentosUC buscarMedicamentosUC) {
        this.buscarMedicamentosUC = buscarMedicamentosUC;
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoDTO>> buscarMedicamentos() {
        return ResponseEntity.ok(this.buscarMedicamentosUC.execute(null));
    }
}
