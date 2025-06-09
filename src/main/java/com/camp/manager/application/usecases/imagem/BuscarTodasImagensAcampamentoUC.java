package com.camp.manager.application.usecases.imagem;

import com.camp.manager.application.gateway.ImagemGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.ImagemEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.dto.galeria.DiretorioDeImagensDTO;
import com.camp.manager.infra.http.dto.galeria.ImagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BuscarTodasImagensAcampamentoUC implements UseCase<Void, MethodResponse<List<DiretorioDeImagensDTO>>> {

    private final ImagemGateway imagemGateway;

    @Autowired
    public BuscarTodasImagensAcampamentoUC(ImagemGateway imagemGateway) {
        this.imagemGateway = imagemGateway;
    }

    public MethodResponse<List<DiretorioDeImagensDTO>> execute(Void input) {
        List<ImagemEntityDomain> todasAsImagens = this.imagemGateway.buscarTodasImagens();
        if(todasAsImagens.isEmpty()) throw new NotFoundException("Não existem imagens cadastradas!");

        Map<String, List<ImagemEntityDomain>> imagensPorAcampamento = new HashMap<>();

        todasAsImagens.forEach(imagemEntityDomain -> {
            String chave = imagemEntityDomain.nomeAcampamento();
            imagensPorAcampamento
                    .computeIfAbsent(chave, k -> new ArrayList<>())
                    .add(imagemEntityDomain);
        });

        List<DiretorioDeImagensDTO> diretoriosDeImagens = new ArrayList<>();

        imagensPorAcampamento.forEach((chave, imagens) -> {
            List<ImagemDTO> imagensDto = ImagemDTO.converter(imagens);
            diretoriosDeImagens.add(new DiretorioDeImagensDTO(chave, imagens.get(0) != null ? imagens.get(0).arquivoImagem() : null,imagensDto));
        });

        return new MethodResponse<>(200, "Imagens encontradas com sucesso!", diretoriosDeImagens);
    }
}
