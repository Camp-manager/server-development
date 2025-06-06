package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.tema.*;
import com.camp.manager.infra.http.dto.tema.TemaDTO;
import com.camp.manager.infra.http.request.tema.AtualizarTemaRequest;
import com.camp.manager.infra.http.request.tema.CriarTemaRequest;
import com.camp.manager.infra.http.request.tema.ImagemTemaRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/tema")
public class TemaController {

    private final AdicionarTemaUC adicionarTemaUC;
    private final AdicionarImagemTemaUC adicionarImagemUC;
    private final AlterarTemaUC alterarTemaUC;
    private final BuscarTemasUC buscarTemasUC;
    private final DeletarTemaUC deletarTemaUC;

    @Autowired
    public TemaController(AdicionarTemaUC adicionarTemaUC, AdicionarImagemTemaUC adicionarImagemUC,
                          AlterarTemaUC alterarTemaUC,
                          BuscarTemasUC buscarTemasUC,
                          DeletarTemaUC deletarTemaUC) {
        this.adicionarTemaUC = adicionarTemaUC;
        this.adicionarImagemUC = adicionarImagemUC;
        this.alterarTemaUC = alterarTemaUC;
        this.buscarTemasUC = buscarTemasUC;
        this.deletarTemaUC = deletarTemaUC;
    }

    @PostMapping(path = "/adicionar")
    public ResponseEntity<Long> adicionarTema(@RequestBody @Valid CriarTemaRequest temaRequest) {
        var response = this.adicionarTemaUC.execute(temaRequest);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @PutMapping(path = "/adicionar-imagem", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> adicionarImagemNoTema(@RequestParam("file") MultipartFile imagem, @RequestParam("idTema") Long idTema) {
        var response = this.adicionarImagemUC.execute(new ImagemTemaRequest(idTema, imagem));
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }


    @PutMapping(path = "/atualizar")
    public ResponseEntity<Void> atualizarTema(@RequestBody @Valid AtualizarTemaRequest temaRequest) {
        var response = this.alterarTemaUC.execute(temaRequest);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @GetMapping(path = "/buscar-todos")
    public ResponseEntity<List<TemaDTO>> buscarTemas() {
        var response = this.buscarTemasUC.execute(null);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }

    @DeleteMapping(path = "/deletar/{idTema}")
    public ResponseEntity<Void> deletarTema(@PathVariable Long idTema) {
        var response = this.deletarTemaUC.execute(idTema);
        return ResponseEntity
                .status(response.status())
                .body(response.data());
    }
}
