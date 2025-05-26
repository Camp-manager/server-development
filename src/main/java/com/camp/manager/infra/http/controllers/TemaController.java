package com.camp.manager.infra.http.controllers;

import com.camp.manager.application.usecases.tema.AdicionarTemaUC;
import com.camp.manager.application.usecases.tema.AlterarTemaUC;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.infra.http.request.tema.AtualizarTemaRequest;
import com.camp.manager.infra.http.request.tema.CriarTemaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/tema")
public class TemaController {

    private final AdicionarTemaUC adicionarTemaUC;
    private final AlterarTemaUC alterarTemaUC;

    @Autowired
    public TemaController(AdicionarTemaUC adicionarTemaUC, AlterarTemaUC alterarTemaUC) {
        this.adicionarTemaUC = adicionarTemaUC;
        this.alterarTemaUC = alterarTemaUC;
    }

    @PostMapping(path = "/adicionar")
    public ResponseEntity<MethodResponse<Void>> adicionarTema(@RequestBody CriarTemaRequest temaRequest, @RequestParam("file") MultipartFile imagemTema) {
        CriarTemaRequest temaRequestComImagem = new CriarTemaRequest(
                temaRequest.descricao(),
                temaRequest.precoCamiseta(),
                temaRequest.precoAcampamento(),
                imagemTema
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(this.adicionarTemaUC.execute(temaRequestComImagem));
    }

    @PutMapping(path = "/atualizar")
    public ResponseEntity<MethodResponse<Void>> atualizarTema(@RequestBody AtualizarTemaRequest temaRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(this.alterarTemaUC.execute(temaRequest));
    }
}
