package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.imagem.AdicionarImagensUC;
import com.camp.manager.application.usecases.imagem.BuscarTodasImagensAcampamentoUC;
import com.camp.manager.infra.http.dto.galeria.ImagemDTO;
import com.camp.manager.infra.http.request.imagens.InserirImagemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    private final BuscarTodasImagensAcampamentoUC buscarTodasImagensAcampamentoUC;
    private final AdicionarImagensUC adicionarImagensUC;

    @Autowired
    public ImagemController(BuscarTodasImagensAcampamentoUC buscarTodasImagensAcampamentoUC,
                            AdicionarImagensUC adicionarImagensUC) {
        this.buscarTodasImagensAcampamentoUC = buscarTodasImagensAcampamentoUC;
        this.adicionarImagensUC = adicionarImagensUC;
    }



    @PostMapping(path = "/inserir-zip/{idAcampamento}/{anoDasImagens}")
    public ResponseEntity<Void> inserirImagensNoAcampamentoPorZip(@PathVariable Long idAcampamento,@RequestParam("file") MultipartFile zipComAsImagens, @PathVariable Long anoDasImagens) {
        var response = this.adicionarImagensUC.execute(new InserirImagemRequest(idAcampamento, zipComAsImagens, anoDasImagens));
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }
}
