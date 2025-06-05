package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.pessoa.AdicionarCampistaUC;
import com.camp.manager.application.usecases.pessoa.AdicionarFuncionarioUC;
import com.camp.manager.application.usecases.pessoa.BuscarCampistasUC;
import com.camp.manager.application.usecases.pessoa.BuscarFuncionariosUC;
import com.camp.manager.infra.http.dto.pessoa.CampistaBasicoDTO;
import com.camp.manager.infra.http.dto.pessoa.FuncionarioBasicoDTO;
import com.camp.manager.infra.http.request.pessoa.CriarCampistaRequest;
import com.camp.manager.infra.http.request.pessoa.CriarFuncionarioRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final AdicionarCampistaUC adicionarCampistaUC;
    private final AdicionarFuncionarioUC adicionarFuncionarioUC;
    private final BuscarFuncionariosUC buscarFuncionariosUC;
    private final BuscarCampistasUC buscarCampistasUC;

    @Autowired
    public PessoaController(AdicionarCampistaUC adicionarCampistaUC,
                            AdicionarFuncionarioUC adicionarFuncionarioUC,
                            BuscarFuncionariosUC buscarFuncionariosUC, BuscarCampistasUC buscarCampistasUC) {
        this.adicionarCampistaUC = adicionarCampistaUC;
        this.adicionarFuncionarioUC = adicionarFuncionarioUC;
        this.buscarFuncionariosUC = buscarFuncionariosUC;
        this.buscarCampistasUC = buscarCampistasUC;
    }

    @GetMapping(path = "/buscar-todos/funcionarios/{idAcampamento}")
    public ResponseEntity<List<FuncionarioBasicoDTO>> buscarTodosFuncionarios(@PathVariable Long idAcampamento) {
        var response = this.buscarFuncionariosUC.execute(idAcampamento);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @GetMapping(path = "/buscar-todos/campistas/{idAcampamento}")
    public ResponseEntity<List<CampistaBasicoDTO>> buscarTodosCampistas(@PathVariable Long idAcampamento) {
        var response = this.buscarCampistasUC.execute(idAcampamento);

        return ResponseEntity.ok(List.of());
    }


    @PostMapping(path = "/cadastrar/campista/{codigoRegistro}")
    public ResponseEntity<Void> cadastrarCampista(@PathVariable String codigoRegistro, @RequestBody @Valid CriarCampistaRequest input) {
        input.setCodigoRegistro(codigoRegistro);
        var response = this.adicionarCampistaUC.execute(input);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @PostMapping(path = "/cadastrar/funcionario/{codigoRegistro}")
    public ResponseEntity<Void> cadastrarFuncionario(@PathVariable String codigoRegistro, @RequestBody @Valid CriarFuncionarioRequest input) {
        input.setCodigoRegistro(codigoRegistro);
        var response = this.adicionarFuncionarioUC.execute(input);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }
}
