package com.camp.manager.infra.http.dto.tema;

import com.camp.manager.domain.entity.TemaEntityDomain;
import lombok.Getter;

import java.util.Base64;
import java.util.List;

@Getter
public class TemaDTO {
    private final Long id;
    private final String descricao;
    private final String design;
    private final Double precoCamiseta;
    private final Double precoAcampamento;

    public TemaDTO(TemaEntityDomain temaEntityDomain) {
        this.id = temaEntityDomain.id();
        this.descricao = temaEntityDomain.descricao();
        this.design = temaEntityDomain.imagemDesign() != null ? Base64.getEncoder().encodeToString(temaEntityDomain.imagemDesign()) : null;
        this.precoCamiseta = temaEntityDomain.precoCamiseta().doubleValue();
        this.precoAcampamento = temaEntityDomain.precoAcampamento().doubleValue();
    }

    public static List<TemaDTO> converter(List<TemaEntityDomain> temaEntityDomains) {
        return temaEntityDomains.stream()
                .map(TemaDTO::new)
                .toList();
    }
}
