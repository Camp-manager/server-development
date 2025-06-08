package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.cronograma.*;
import com.camp.manager.infra.http.dto.cronograma.TodosCronogramaDTO;
import com.camp.manager.infra.http.request.cronograma.AlterarCronogramaRequest;
import com.camp.manager.infra.http.request.cronograma.CriarCronogramaRequest;
import com.camp.manager.infra.http.request.cronograma.EstenderCronogramaRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cronograma")
public class CronogramaController {

    private final AdicionarCronogramaUC adicionarCronogramaUC;
    private final AlterarCronogramaUC alterarCronogramaUC;
    private final BuscarCronogramaUC buscarCronogramaUC;
    private final BuscarCronogramasUC buscarCronogramasUC;
    private final EstenderCronogramaUC estenderCronogramaUC;

    @Autowired
    public CronogramaController(AdicionarCronogramaUC adicionarCronogramaUC,
                                AlterarCronogramaUC alterarCronogramaUC,
                                BuscarCronogramaUC buscarCronogramaUC,
                                BuscarCronogramasUC buscarCronogramasUC,
                                EstenderCronogramaUC estenderCronogramaUC) {
        this.adicionarCronogramaUC = adicionarCronogramaUC;
        this.alterarCronogramaUC = alterarCronogramaUC;
        this.buscarCronogramaUC = buscarCronogramaUC;
        this.buscarCronogramasUC = buscarCronogramasUC;
        this.estenderCronogramaUC = estenderCronogramaUC;
    }

    @PostMapping(path = "/adicionar")
    public ResponseEntity<Void> adicionarCronograma(@Valid @RequestBody CriarCronogramaRequest cronogramaRequest) {
        var response = this.adicionarCronogramaUC.execute(cronogramaRequest);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @PutMapping(path = "/alterar")
    public ResponseEntity<Void> alterarCronograma(@Valid @RequestBody AlterarCronogramaRequest cronogramaRequest) {
        var response = this.alterarCronogramaUC.execute(cronogramaRequest);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @GetMapping(path = "/buscar-todos/{idAcampamento}")
    public ResponseEntity<TodosCronogramaDTO> buscarCronogramas(@PathVariable Long idAcampamento) {
        var response = this.buscarCronogramasUC.execute(idAcampamento);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @PutMapping(path = "/estender")
    public ResponseEntity<Void> estenderCronograma(@RequestBody @Valid EstenderCronogramaRequest estenderCronogramaRequest) {
        var response = this.estenderCronogramaUC.execute(estenderCronogramaRequest);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }
}
