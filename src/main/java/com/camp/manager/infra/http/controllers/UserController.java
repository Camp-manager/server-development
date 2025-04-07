package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.CreateUserUC;
import com.camp.manager.application.usecases.ValidateUserUC;
import com.camp.manager.infra.http.dto.TokenResponseDTO;
import com.camp.manager.infra.http.request.user.CreateUserRequest;
import com.camp.manager.infra.http.request.user.LoginUserRequest;
import com.camp.manager.infra.http.request.user.UserRequestMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUC createUserUC;
    private final ValidateUserUC validateUserUC;

    public UserController(CreateUserUC createUserUC, ValidateUserUC validateUserUC) {
        this.createUserUC = createUserUC;
        this.validateUserUC = validateUserUC;
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registrarUsuario(@RequestBody @Valid CreateUserRequest request) {
        this.createUserUC.criarNovoUsuario(UserRequestMapper.convert(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> validarUsuario(@RequestBody @Valid LoginUserRequest request) {
        String token = this.validateUserUC.validarUsuario(request.login(), request.password());
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }


}
