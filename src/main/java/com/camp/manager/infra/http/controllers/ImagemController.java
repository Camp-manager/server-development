package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.BuscarImagensUC;
import com.camp.manager.infra.http.dto.buscarGaleriasUC.DiretorioDeImagensDTO;
import com.camp.manager.infra.http.dto.buscarGaleriasUC.ImagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    private final BuscarImagensUC buscarImagensUC;

    @Autowired
    public ImagemController(BuscarImagensUC buscarImagensUC) {
        this.buscarImagensUC = buscarImagensUC;
    }

    @GetMapping(path = "/buscar")
    public ResponseEntity<List<? extends ImagemDTO>> buscarImagens() {
        return this.buscarImagensUC.execute(null);
    }

    public void cadastrarImagens(){

    }
}
