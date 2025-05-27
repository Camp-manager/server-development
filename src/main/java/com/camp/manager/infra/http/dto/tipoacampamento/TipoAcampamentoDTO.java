package com.camp.manager.infra.http.dto.tipoacampamento;

import com.camp.manager.domain.entity.TipoAcampamentoEntityDomain;
import lombok.Getter;

import java.util.List;

@Getter
public class TipoAcampamentoDTO {
    private final Long id;
    private final String descricao;
    private final String categoriaDoAcampamento;

    public TipoAcampamentoDTO(TipoAcampamentoEntityDomain tipoAcampamentoEntityDomain) {
        this.id = tipoAcampamentoEntityDomain.id();
        this.descricao = tipoAcampamentoEntityDomain.descricao();
        this.categoriaDoAcampamento = tipoAcampamentoEntityDomain.categoriaDoAcampamento();
    }

    public static List<TipoAcampamentoDTO> converter(List<TipoAcampamentoEntityDomain> tipoAcampamentoEntityDomains) {
        return tipoAcampamentoEntityDomains.stream()
                .map(TipoAcampamentoDTO::new)
                .toList();
    }
}
