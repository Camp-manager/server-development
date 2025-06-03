package com.camp.manager.application.usecases.tema;

import com.camp.manager.application.gateway.TemaGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.TemaEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.EntityFoundException;
import com.camp.manager.domain.exception.custom.FileProcessingException;
import com.camp.manager.infra.http.request.tema.CriarTemaRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Service
public class AdicionarTemaUC implements UseCase<CriarTemaRequest, MethodResponse<Void>> {

    private final TemaGateway temaGateway;

    @Autowired
    public AdicionarTemaUC(TemaGateway temaGateway) {
        this.temaGateway = temaGateway;
    }

    @Override
    @Transactional
    public MethodResponse<Void> execute(CriarTemaRequest input) {
        boolean temaEhExistente = this.temaGateway.temaEhExistentePorDescricao(input.descricao());
        if(temaEhExistente) {throw new EntityFoundException("Tema já cadastrado!");}

        byte[] imagemTema = validarETransformar(input.arquivoImagemTema());

        this.temaGateway.inserirTema(
                new TemaEntityDomain(null, input.descricao(), imagemTema, BigDecimal.valueOf(input.precoCamiseta()), BigDecimal.valueOf(input.precoAcampamento())));

        return new MethodResponse<>(201, "Tema cadastrado com sucesso!", null);
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
