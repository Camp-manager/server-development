package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.equipe.BuscarEquipesUC;
import com.camp.manager.application.usecases.equipe.CriarEquipesUC;
import com.camp.manager.infra.http.dto.equipe.EquipeDTO;
import com.camp.manager.infra.http.request.equipe.EquipeRequest;
import com.camp.manager.infra.http.request.equipe.EquipesRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipe")
public class EquipeController {

    private final CriarEquipesUC criarEquipesUC;
    private final BuscarEquipesUC buscarEquipesUC;

    @Autowired
    public EquipeController(CriarEquipesUC criarEquipesUC,
                            BuscarEquipesUC buscarEquipesUC) {
        this.criarEquipesUC = criarEquipesUC;
        this.buscarEquipesUC = buscarEquipesUC;
    }

    @PostMapping(path = "/cadastrar/{idAcampamento}")
    public ResponseEntity<Void> cadastrarEquipesDeTrabalho(@PathVariable Long idAcampamento, @RequestBody @Valid List<EquipeRequest> equipes) {
        var response = this.criarEquipesUC.execute(new EquipesRequest(idAcampamento, equipes));
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @GetMapping(path = "/buscar-todos/{idAcampamento}")
    public ResponseEntity<List<EquipeDTO>> buscarEquipesDeTrabalho(@PathVariable Long idAcampamento) {
        var response = this.buscarEquipesUC.execute(idAcampamento);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

}
