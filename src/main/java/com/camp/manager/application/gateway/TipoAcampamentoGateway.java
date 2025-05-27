package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.TipoAcampamentoEntityDomain;

import java.util.List;

public interface TipoAcampamentoGateway {
    List<TipoAcampamentoEntityDomain> buscarTodosTiposDeAcampamento();
    boolean tipoAcampamentoEhExistentePorDescricao(String descricaoDoTipoAcampamento);
    boolean tipoAcampamentoEhExistentePorId(Long descricaoDoTipoAcampamento);
    void inserirTipoAcampamento(TipoAcampamentoEntityDomain tipoAcampamentoDomain);
    void deletarTipoAcampamentoPorId(Long idDoTipoAcampamento);
    TipoAcampamentoEntityDomain buscarTipoAcampamentoPorId(Long idDoTipoAcampamento);
}
