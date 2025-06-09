package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.ImagemEntityDomain;
import com.camp.manager.infra.persistence.entity.ImagemEntityJpa;
import com.camp.manager.utils.converter.localDate.LocalDateConverterAPP;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

@Component
public class ImagemMapper implements Mapper<ImagemEntityJpa, ImagemEntityDomain> {

    @Override
    public ImagemEntityDomain toDomain(ImagemEntityJpa imagemEntityJpa) {
        Path pathDaImagem = Path.of(imagemEntityJpa.getPathLocalizacao());
        byte[] bytesDaImagem;
        try {
            bytesDaImagem = Files.readAllBytes(pathDaImagem);
        } catch (IOException e) {
            bytesDaImagem = null;
        }

        return new ImagemEntityDomain(
                imagemEntityJpa.getId(),
                bytesDaImagem,
                pathDaImagem,
                LocalDateConverterAPP.converterStringParaLocalDate(imagemEntityJpa.getData()),
                imagemEntityJpa.getAcampamento().getNome());
    }

    @Override
    public ImagemEntityJpa toEntity(ImagemEntityDomain imagemEntityDomain) {
        return new ImagemEntityJpa(
                imagemEntityDomain.id(),
                Base64.getEncoder().encodeToString(imagemEntityDomain.arquivoImagem()),
                LocalDateConverterAPP.converterLocalDateParaString(imagemEntityDomain.data())
        );
    }
}
