package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.user.RealizarLoginUC;
import com.camp.manager.application.usecases.user.RegistrarUsuarioUC;
import com.camp.manager.infra.http.dto.TokenResponseDTO;
import com.camp.manager.infra.http.request.user.CreateUserRequest;
import com.camp.manager.infra.http.request.user.LoginUserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private final RealizarLoginUC realizarLoginUC;
    private final RegistrarUsuarioUC registrarUsuarioUC;

    @Autowired
    public UsuarioController(RealizarLoginUC realizarLoginUC,
                             RegistrarUsuarioUC registrarUsuarioUC) {
        this.realizarLoginUC = realizarLoginUC;
        this.registrarUsuarioUC = registrarUsuarioUC;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Void> registrarUsuario(@RequestBody @Valid CreateUserRequest request) {
        var response = this.registrarUsuarioUC.execute(request);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @PostMapping(path = "/login")
    public ResponseEntity<TokenResponseDTO> validarUsuario(@RequestBody @Valid LoginUserRequest request) {
        var response = this.realizarLoginUC.execute(request);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }
}
