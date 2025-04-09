package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.RealizarLoginUC;
import com.camp.manager.application.usecases.RegistrarUsuarioUC;
import com.camp.manager.infra.http.dto.TokenResponseDTO;
import com.camp.manager.infra.http.request.user.CreateUserRequest;
import com.camp.manager.infra.http.request.user.LoginUserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registrarUsuario(@RequestBody @Valid CreateUserRequest request) {
        this.registrarUsuarioUC.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> validarUsuario(@RequestBody @Valid LoginUserRequest request) {
        String token = this.realizarLoginUC.execute(request);
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }


}
