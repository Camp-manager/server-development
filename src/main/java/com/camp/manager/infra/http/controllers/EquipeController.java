package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.equipe.*;
import com.camp.manager.infra.http.dto.equipe.EquipeDTO;
import com.camp.manager.infra.http.request.equipe.*;
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
    private final AdicionarPessoasEquipeUC adicionarPessoasEquipeUC;
    private final SelecionarLiderDeEquipeUC selecionarLiderDeEquipeUC;
    private final RemoverPessoasEquipeUC removerPessoasEquipeUC;

    @Autowired
    public EquipeController(CriarEquipesUC criarEquipesUC,
                            BuscarEquipesUC buscarEquipesUC,
                            AdicionarPessoasEquipeUC adicionarPessoasEquipeUC,
                            SelecionarLiderDeEquipeUC selecionarLiderDeEquipeUC,
                            RemoverPessoasEquipeUC removerPessoasEquipeUC) {
        this.criarEquipesUC = criarEquipesUC;
        this.buscarEquipesUC = buscarEquipesUC;
        this.adicionarPessoasEquipeUC = adicionarPessoasEquipeUC;
        this.selecionarLiderDeEquipeUC = selecionarLiderDeEquipeUC;
        this.removerPessoasEquipeUC = removerPessoasEquipeUC;
    }

    @PostMapping(path = "/cadastrar/{idAcampamento}")
    public ResponseEntity<Void> cadastrarEquipesDeTrabalho(@PathVariable Long idAcampamento, @RequestBody @Valid List<EquipeRequest> equipes) {
        var response = this.criarEquipesUC.execute(new EquipesRequest(idAcampamento, equipes));
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @PutMapping(path = "/selecionar-lider/{idEquipe}/{idPessoa}/{idAcampamento}")
    public ResponseEntity<Void> selecionarLiderEquipe(@PathVariable Long idEquipe,
                                                              @PathVariable Long idPessoa,
                                                              @PathVariable Long idAcampamento) {
        var response = this.selecionarLiderDeEquipeUC.execute(new LiderEquipeRequest(idEquipe, idPessoa, idAcampamento));
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @PutMapping(path = "/remover-pessoas/{idEquipe}")
    public ResponseEntity<Void> removerPessoasEquipe(@PathVariable Long idEquipe, @RequestBody List<Long> idsPessoas) {
        var response = this.removerPessoasEquipeUC.execute(new RemoverPessoasEquipeRequest(idEquipe, idsPessoas));
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @PostMapping(path = "/adicionar-pessoas/{idEquipe}")
    public ResponseEntity<Void> adicionarPessoasEquipe(@PathVariable Long idEquipe, @RequestBody List<Long> idsPessoas) {
        var response = this.adicionarPessoasEquipeUC.execute(new AdicionarPessoasEquipeRequest(idEquipe, idsPessoas));
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
