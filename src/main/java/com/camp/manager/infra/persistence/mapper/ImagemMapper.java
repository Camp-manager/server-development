package com.camp.manager.infra.persistence.mapper;

import com.camp.manager.domain.entity.ImagemEntityDomain;
import com.camp.manager.infra.persistence.entity.ImagemEntityJpa;
import com.camp.manager.utils.converter.localDate.LocalDateConverterAPP;

import java.util.Base64;

public class ImagemMapper {

    public static ImagemEntityJpa toEntity(ImagemEntityDomain imagemEntityDomain) {
        return new ImagemEntityJpa(
                imagemEntityDomain.id(),
                Base64.getEncoder().encodeToString(imagemEntityDomain.arquivoImagem()),
                LocalDateConverterAPP.converterLocalDateParaString(imagemEntityDomain.data())
        );
    }

    public static ImagemEntityDomain toModel(ImagemEntityJpa imagemEntityJpa) {
        return new ImagemEntityDomain(
                imagemEntityJpa.getId(),
                Base64.getDecoder().decode(imagemEntityJpa.getPathLocalizacao()),
                LocalDateConverterAPP.converterStringParaLocalDate(imagemEntityJpa.getData()),
                imagemEntityJpa.getAcampamento().getTipoAcampamento().getDescricao()
                        + " " + LocalDateConverterAPP.converterStringParaLocalDate(imagemEntityJpa.getData()).getDayOfYear());
    }
}
