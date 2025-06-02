package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.ImagemEntityDomain;
import com.camp.manager.infra.persistence.entity.ImagemEntityJpa;
import com.camp.manager.utils.converter.localDate.LocalDateConverterAPP;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.Base64;

@Component
public class ImagemMapper implements Mapper<ImagemEntityJpa, ImagemEntityDomain> {

    @Override
    public ImagemEntityDomain toDomain(ImagemEntityJpa imagemEntityJpa) {
        return new ImagemEntityDomain(
                imagemEntityJpa.getId(),
                Base64.getDecoder().decode(imagemEntityJpa.getPathLocalizacao()),
                Path.of(imagemEntityJpa.getPathLocalizacao()),
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
