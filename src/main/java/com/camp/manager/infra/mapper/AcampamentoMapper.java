package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.*;
import com.camp.manager.infra.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AcampamentoMapper implements Mapper<AcampamentoEntityJpa, AcampamentoEntityDomain> {

    private final Mapper<TemaEntityJpa, TemaEntityDomain> mapperTema;
    private final Mapper<TipoAcampamentoEntityJpa, TipoAcampamentoEntityDomain> mapperTipo;
    private final Mapper<CronogramaEntityJpa, CronogramaEntityDomain> mapperCronograma;
    private final Mapper<EquipeEntityJpa, EquipeEntityDomain> mapperEquipe;
    private final Mapper<ImagemEntityJpa, ImagemEntityDomain> mapperImagem;

    @Autowired
    public AcampamentoMapper(@Lazy Mapper<TemaEntityJpa, TemaEntityDomain> mapperTema,
                             @Lazy Mapper<TipoAcampamentoEntityJpa, TipoAcampamentoEntityDomain> mapperTipo,
                             @Lazy Mapper<CronogramaEntityJpa, CronogramaEntityDomain> mapperCronograma,
                             @Lazy Mapper<EquipeEntityJpa, EquipeEntityDomain> mapperEquipe,
                             @Lazy Mapper<ImagemEntityJpa, ImagemEntityDomain> mapperImagem) {
        this.mapperTema = mapperTema;
        this.mapperTipo = mapperTipo;
        this.mapperCronograma = mapperCronograma;
        this.mapperEquipe = mapperEquipe;
        this.mapperImagem = mapperImagem;
    }

    @Override
    public AcampamentoEntityDomain toDomain(AcampamentoEntityJpa acampamentoEntityJpa) {
        return new AcampamentoEntityDomain(
                acampamentoEntityJpa.getId(),
                acampamentoEntityJpa.getNome(),
                acampamentoEntityJpa.getLimiteCampistas(),
                acampamentoEntityJpa.getLimiteFuncionario(),
                acampamentoEntityJpa.getCodigoRegistro(),
                mapperTema.toDomain(acampamentoEntityJpa.getTema()),
                mapperTipo.toDomain(acampamentoEntityJpa.getTipoAcampamento()),
                mapperCronograma.toDomain(acampamentoEntityJpa.getCronograma()),
                this.mapDomainImagens(acampamentoEntityJpa.getListImagem()),
                this.mapDomainEquipe(acampamentoEntityJpa.getListEquipe())
        );
    }

    @Override
    public AcampamentoEntityJpa toEntity(AcampamentoEntityDomain acampamentoEntityDomain) {
        return new AcampamentoEntityJpa(
                acampamentoEntityDomain.id(),
                acampamentoEntityDomain.nome(),
                acampamentoEntityDomain.limiteCampistas(),
                acampamentoEntityDomain.limiteFuncionario(),
                acampamentoEntityDomain.codigoRegistro(),
                mapperTema.toEntity(acampamentoEntityDomain.tema()),
                mapperTipo.toEntity(acampamentoEntityDomain.tipoAcampamento()),
                mapperCronograma.toEntity(acampamentoEntityDomain.cronograma()),
                this.mapEntityImagens(acampamentoEntityDomain.imagensDoAcampamento()),
                this.mapEntityEquipe(acampamentoEntityDomain.equipesDoAcampamento())
        );
    }

    private List<ImagemEntityDomain> mapDomainImagens(List<ImagemEntityJpa> imagensJpa) {
        return imagensJpa.stream()
                .map(mapperImagem::toDomain)
                .toList();
    }
    private List<ImagemEntityJpa> mapEntityImagens(List<ImagemEntityDomain> imagensDomain) {
        return imagensDomain.stream()
                .map(mapperImagem::toEntity)
                .toList();
    }

    private List<EquipeEntityDomain> mapDomainEquipe(List<EquipeEntityJpa> equipeJpa) {
        return equipeJpa.stream()
                .map(mapperEquipe::toDomain)
                .toList();
    }
    private List<EquipeEntityJpa> mapEntityEquipe(List<EquipeEntityDomain> equipeDomain) {
        return equipeDomain.stream()
                .map(mapperEquipe::toEntity)
                .toList();
    }

}
