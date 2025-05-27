package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.tema.AdicionarTemaUC;
import com.camp.manager.application.usecases.tema.AlterarTemaUC;
import com.camp.manager.application.usecases.tema.BuscarTemasUC;
import com.camp.manager.application.usecases.tema.DeletarTemaUC;
import com.camp.manager.infra.http.dto.tema.TemaDTO;
import com.camp.manager.infra.http.request.tema.AtualizarTemaRequest;
import com.camp.manager.infra.http.request.tema.CriarTemaRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/tema")
public class TemaController {

    private final AdicionarTemaUC adicionarTemaUC;
    private final AlterarTemaUC alterarTemaUC;
    private final BuscarTemasUC buscarTemasUC;
    private final DeletarTemaUC deletarTemaUC;

    @Autowired
    public TemaController(AdicionarTemaUC adicionarTemaUC,
                          AlterarTemaUC alterarTemaUC,
                          BuscarTemasUC buscarTemasUC,
                          DeletarTemaUC deletarTemaUC) {
        this.adicionarTemaUC = adicionarTemaUC;
        this.alterarTemaUC = alterarTemaUC;
        this.buscarTemasUC = buscarTemasUC;
        this.deletarTemaUC = deletarTemaUC;
    }

    @PostMapping(path = "/adicionar")
    public ResponseEntity<Void> adicionarTema(@RequestBody @Valid CriarTemaRequest temaRequest, @RequestParam("file") MultipartFile imagemTema) {
        CriarTemaRequest temaRequestComImagem = new CriarTemaRequest(
                temaRequest.descricao(),
                temaRequest.precoCamiseta(),
                temaRequest.precoAcampamento(),
                imagemTema
        );
        var response = this.adicionarTemaUC.execute(temaRequestComImagem);
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
