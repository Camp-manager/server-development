package com.camp.manager.application.usecases;

import com.camp.manager.application.gateway.ImagemGateway;
import com.camp.manager.domain.entity.ImagemEntityDomain;
import com.camp.manager.infra.http.dto.buscarGaleriasUC.ImagemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BuscarImagensUC implements UseCase<Void, ResponseEntity<List<? extends ImagemDTO>>> {

    @Value("${url.pasta.utils}")
    private static String pathPadraoDeImagens;

    private final ImagemGateway imagemGateway;

    @Autowired
    public BuscarImagensUC(ImagemGateway imagemGateway) {
        this.imagemGateway = imagemGateway;
    }

    public ResponseEntity<List<? extends ImagemDTO>> execute(Void input) {
        List<ImagemEntityDomain> imagensRetornadas = this.imagemGateway.buscarTodasImagens();
        Map<String,List<ImagemEntityDomain>> imagensAgrupadasPorDiretorio = imagensRetornadas.stream()
                .collect(Collectors.groupingBy(ImagemEntityDomain::nomeAcampamento));

        return null;
    }
}
