package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.CreateUserUC;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUC createUserUC;

    public UserController(CreateUserUC createUserUC) {
        this.createUserUC = createUserUC;
    }
}
