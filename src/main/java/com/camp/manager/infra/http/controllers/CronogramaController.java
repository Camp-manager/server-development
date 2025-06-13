package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.cronograma.*;
import com.camp.manager.infra.http.dto.cronograma.TodosCronogramaDTO;
import com.camp.manager.infra.http.request.cronograma.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cronograma")
public class CronogramaController {

    private final AdicionarCronogramaCampistasUC adicionarCronogramaCampistasUC;
    private final AdicionarCronogramaTrabalhoUC adicionarCronogramaTrabalhoUC;
    private final AlterarCronogramaUC alterarCronogramaUC;
    private final BuscarCronogramaUC buscarCronogramaUC;
    private final BuscarCronogramasUC buscarCronogramasUC;
    private final EstenderCronogramaUC estenderCronogramaUC;

    @Autowired
    public CronogramaController(AdicionarCronogramaCampistasUC adicionarCronogramaCampistasUC, AdicionarCronogramaTrabalhoUC adicionarCronogramaTrabalhoUC,
                                AlterarCronogramaUC alterarCronogramaUC,
                                BuscarCronogramaUC buscarCronogramaUC,
                                BuscarCronogramasUC buscarCronogramasUC,
                                EstenderCronogramaUC estenderCronogramaUC) {
        this.adicionarCronogramaCampistasUC = adicionarCronogramaCampistasUC;
        this.adicionarCronogramaTrabalhoUC = adicionarCronogramaTrabalhoUC;
        this.alterarCronogramaUC = alterarCronogramaUC;
        this.buscarCronogramaUC = buscarCronogramaUC;
        this.buscarCronogramasUC = buscarCronogramasUC;
        this.estenderCronogramaUC = estenderCronogramaUC;
    }

    @PostMapping(path = "/adicionar/campistas")
    public ResponseEntity<Void> adicionarCronograma(@Valid @RequestBody CriarCronogramCampistasRequest cronogramaRequest) {
        var response = this.adicionarCronogramaCampistasUC.execute(cronogramaRequest);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @PostMapping(path = "/adicionar/trabalho")
    public ResponseEntity<Void> adicionarCronogramaTrabalho(@Valid @RequestBody CriarCronogramaTrabalhoRequest cronogramaRequest) {
        var response = this.adicionarCronogramaTrabalhoUC.execute(cronogramaRequest);
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
