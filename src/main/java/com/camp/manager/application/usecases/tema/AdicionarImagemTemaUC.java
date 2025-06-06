package com.camp.manager.application.usecases.tema;

import com.camp.manager.application.gateway.TemaGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.TemaEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.FileProcessingException;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.request.tema.ImagemTemaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AdicionarImagemTemaUC implements UseCase <ImagemTemaRequest, MethodResponse<Void>>{

    private final TemaGateway temaGateway;

    @Autowired
    public AdicionarImagemTemaUC(TemaGateway temaGateway) {
        this.temaGateway = temaGateway;
    }

    @Override
    public MethodResponse<Void> execute(ImagemTemaRequest input) {
        boolean temaEhExistente = this.temaGateway.temaEhExistentePorId(input.idTema());
        if(!temaEhExistente) throw new NotFoundException("Tema com id [" + input.idTema() + "] não encontrado!");
        TemaEntityDomain tema = this.temaGateway.buscarTemaPorId(input.idTema());

        byte[] imagemTema = this.validarETransformar(input.arquivoImagemTema());

        TemaEntityDomain temaAtualizado = new TemaEntityDomain(
                tema.id(),
                tema.descricao(),
                imagemTema,
                tema.precoCamiseta(),
                tema.precoAcampamento()
        );

        this.temaGateway.salvarTemaComImagem(temaAtualizado);

        return new MethodResponse<>(201, "Imagem do tema adicionada com sucesso!", null);
    }

    private byte[] validarETransformar(MultipartFile imagem) {
        if(imagem.isEmpty() || imagem.getSize() == 0) {
            throw new FileProcessingException("Imagem do tema não pode ser vazia!");
        }

        byte[] imagemTema;
        try{
            imagemTema = imagem.getBytes();
        } catch (Exception e){
            throw new FileProcessingException("Erro ao processar a imagem do tema!");
        }

        return imagemTema;
    }
}
