package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.*;
import com.camp.manager.infra.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AcampamentoMapper implements Mapper<AcampamentoEntityJpa, AcampamentoEntityDomain> {

    private final TemaMapper mapperTema;
    private final TipoAcampamentoMapper mapperTipo;
    private final CronogramaMapper mapperCronograma;
    private final EquipeMapper mapperEquipe;
    private final ImagemMapper mapperImagem;

    @Autowired
    public AcampamentoMapper(@Lazy TemaMapper mapperTema,
                             @Lazy TipoAcampamentoMapper mapperTipo,
                             @Lazy CronogramaMapper mapperCronograma,
                             @Lazy EquipeMapper mapperEquipe,
                             @Lazy ImagemMapper mapperImagem) {
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
                this.mapperTema.toDomain(acampamentoEntityJpa.getTema()),
                this.mapperTipo.toDomain(acampamentoEntityJpa.getTipoAcampamento()),
                this.mapperCronograma.toDomain(acampamentoEntityJpa.getCronograma()),
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
                this.mapperTema.toEntity(acampamentoEntityDomain.tema()),
                this.mapperTipo.toEntity(acampamentoEntityDomain.tipoAcampamento()),
                this.mapperCronograma.toEntity(acampamentoEntityDomain.cronograma()),
                this.mapEntityImagens(acampamentoEntityDomain.imagensDoAcampamento()),
                this.mapEntityEquipe(acampamentoEntityDomain.equipesDoAcampamento())
        );
    }

    private List<ImagemEntityDomain> mapDomainImagens(List<ImagemEntityJpa> imagensJpa) {
        return imagensJpa.stream()
                .map(mapperImagem::toDomain)
                .collect(Collectors.toList());
    }
    private List<ImagemEntityJpa> mapEntityImagens(List<ImagemEntityDomain> imagensDomain) {
        return imagensDomain.stream()
                .map(mapperImagem::toEntity)
                .collect(Collectors.toList());
    }



    private List<EquipeEntityDomain> mapDomainEquipe(List<EquipeEntityJpa> equipeJpa) {
        return equipeJpa.stream()
                .map(mapperEquipe::toDomain)
                .collect(Collectors.toList());
    }
    private List<EquipeEntityJpa> mapEntityEquipe(List<EquipeEntityDomain> equipeDomain) {
        return equipeDomain.stream()
                .map(mapperEquipe::toEntity)
                .collect(Collectors.toList());
    }


    public AcampamentoEntityDomain toDomainWithoutEquipes(AcampamentoEntityJpa acampamentoEntityJpa) {
        if (acampamentoEntityJpa == null) {
            return null;
        }
        return new AcampamentoEntityDomain(
                acampamentoEntityJpa.getId(),
                acampamentoEntityJpa.getNome(),
                acampamentoEntityJpa.getLimiteCampistas(),
                acampamentoEntityJpa.getLimiteFuncionario(),
                acampamentoEntityJpa.getCodigoRegistro(),
                this.mapperTema.toDomain(acampamentoEntityJpa.getTema()),
                this.mapperTipo.toDomain(acampamentoEntityJpa.getTipoAcampamento()),
                this.mapperCronograma.toDomain(acampamentoEntityJpa.getCronograma()),
                this.mapDomainImagens(acampamentoEntityJpa.getListImagem()),
                new ArrayList<>()
        );
    }
}
