package com.camp.manager.infra.http.controllers;

import com.camp.manager.infra.http.request.pessoa.CriarCampistaRequest;
import com.camp.manager.infra.http.request.pessoa.CriarFuncionarioRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {


    @PostMapping(path = "/cadastrar/campista/{codigoRegistro}")
    public ResponseEntity<Void> cadastrarCampista(@PathVariable String codigoRegistro, @RequestBody @Valid CriarCampistaRequest input) {
        return null;
    }

    @PostMapping(path = "/cadastrar/funcionario/{codigoRegistro}")
    public ResponseEntity<Void> cadastrarFuncionario(@PathVariable String codigoRegistro, @RequestBody @Valid CriarFuncionarioRequest input) {
        return null;
    }
}
