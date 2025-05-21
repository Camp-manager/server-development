package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.AdicionarImagensUC;
import com.camp.manager.application.usecases.BuscarImagensUC;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.infra.http.dto.buscarGaleriasUC.ImagemDTO;
import com.camp.manager.infra.http.request.imagens.InserirImagemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

    private final BuscarImagensUC buscarImagensUC;
    private final AdicionarImagensUC adicionarImagensUC;

    @Autowired
    public ImagemController(BuscarImagensUC buscarImagensUC, AdicionarImagensUC adicionarImagensUC) {
        this.buscarImagensUC = buscarImagensUC;
        this.adicionarImagensUC = adicionarImagensUC;
    }

    @GetMapping(path = "/buscar")
    public ResponseEntity<List<? extends ImagemDTO>> buscarImagens() {
        return this.buscarImagensUC.execute(null);
    }

    @PostMapping(path = "/inserir-zip/{idAcampamento}/{anoDasImagens}")
    public MethodResponse<Void> inserirImagensNoAcampamentoPorZip(@PathVariable Long idAcampamento,@RequestParam("file") MultipartFile zipComAsImagens, @PathVariable Long anoDasImagens) {
        return this.adicionarImagensUC.execute(new InserirImagemRequest(idAcampamento, zipComAsImagens, anoDasImagens));
    }
}
