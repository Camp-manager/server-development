package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.usuario.AlterarSenhaUC;
import com.camp.manager.application.usecases.usuario.RealizarLoginUC;
import com.camp.manager.application.usecases.usuario.RegistrarUsuarioUC;
import com.camp.manager.infra.http.dto.TokenResponseDTO;
import com.camp.manager.infra.http.request.user.AlterarSenhaUsuarioRequest;
import com.camp.manager.infra.http.request.user.CriarUsuarioRequest;
import com.camp.manager.infra.http.request.user.LogarUsuarioRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private final RealizarLoginUC realizarLoginUC;
    private final RegistrarUsuarioUC registrarUsuarioUC;
    private final AlterarSenhaUC alterarSenhaUC;

    @Autowired
    public UsuarioController(RealizarLoginUC realizarLoginUC,
                             RegistrarUsuarioUC registrarUsuarioUC,
                             AlterarSenhaUC alterarSenhaUC) {
        this.realizarLoginUC = realizarLoginUC;
        this.registrarUsuarioUC = registrarUsuarioUC;
        this.alterarSenhaUC = alterarSenhaUC;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Void> registrarUsuario(@RequestBody @Valid CriarUsuarioRequest request) {
        var response = this.registrarUsuarioUC.execute(request);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @PostMapping(path = "/login")
    public ResponseEntity<TokenResponseDTO> validarUsuario(@RequestBody @Valid LogarUsuarioRequest request) {
        var response = this.realizarLoginUC.execute(request);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Void> atualizarUsuario(@RequestBody @Valid AlterarSenhaUsuarioRequest request) {
        var response = this.alterarSenhaUC.execute(request);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }
}
