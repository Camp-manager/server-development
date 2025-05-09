package com.camp.manager.infra.http.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imagens")
public class ImagensController {

    @GetMapping(path = "/buscar/{id_acampamento}")
    public void buscarImagens(@PathVariable Long id_acampamento) {

    }
}
