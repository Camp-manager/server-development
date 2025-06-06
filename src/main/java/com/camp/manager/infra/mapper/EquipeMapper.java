package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.*;
import com.camp.manager.domain.enums.TipoEquipe;
import com.camp.manager.infra.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EquipeMapper implements Mapper<EquipeEntityJpa, EquipeEntityDomain>{

    private final Mapper<CronogramaEntityJpa, CronogramaEntityDomain> mapperCronograma;
    private final Mapper<AcampamentoEntityJpa, AcampamentoEntityDomain> mapperAcampamento;
    private final Mapper<CampistaEntityJpa, CampistaEntityDomain> mapperCampista;
    private final Mapper<FuncionarioEntityJpa, FuncionarioEntityDomain> mapperFuncionario;

    @Autowired
    public EquipeMapper(@Lazy Mapper<CronogramaEntityJpa, CronogramaEntityDomain> mapperCronograma,
                        @Lazy Mapper<AcampamentoEntityJpa, AcampamentoEntityDomain> mapperAcampamento,
                        @Lazy Mapper<CampistaEntityJpa, CampistaEntityDomain> mapperCampista,
                        @Lazy Mapper<FuncionarioEntityJpa, FuncionarioEntityDomain> mapperFuncionario) {
        this.mapperCronograma = mapperCronograma;
        this.mapperAcampamento = mapperAcampamento;
        this.mapperCampista = mapperCampista;
        this.mapperFuncionario = mapperFuncionario;
    }

    @Override
    public EquipeEntityDomain toDomain(EquipeEntityJpa equipeEntityJpa) {
        return new EquipeEntityDomain(
                equipeEntityJpa.getId(),
                equipeEntityJpa.getNome(),
                equipeEntityJpa.getTipoEquipe().getDescricao(),
                this.mapperCronograma.toDomain(equipeEntityJpa.getCronograma()),
                this.mapperAcampamento.toDomain(equipeEntityJpa.getAcampamento()),
                this.mapDomainCampistas(equipeEntityJpa.getCampistas()),
                this.mapDomainFuncionarios(equipeEntityJpa.getFuncionarios())
        );
    }

    @Override
    public EquipeEntityJpa toEntity(EquipeEntityDomain equipeEntityDomain) {
        return new EquipeEntityJpa(
                equipeEntityDomain.id(),
                equipeEntityDomain.nome(),
                TipoEquipe.fromDescricao(equipeEntityDomain.tipoEquipe()),
                this.mapperCronograma.toEntity(equipeEntityDomain.cronograma()),
                this.mapperAcampamento.toEntity(equipeEntityDomain.acampamento()),
                this.mapEntityCampistas(equipeEntityDomain.campistasNaEquipe()),
                this.mapEntityFuncionarios(equipeEntityDomain.funcionariosNaEquipe())
        );
    }

    private List<CampistaEntityDomain> mapDomainCampistas(List<CampistaEntityJpa> campistasJpa) {
        return campistasJpa.stream()
                .map(mapperCampista::toDomain)
                .toList();
    }
    private List<CampistaEntityJpa> mapEntityCampistas(List<CampistaEntityDomain> campistasJpa) {
        return campistasJpa.stream()
                .map(mapperCampista::toEntity)
                .toList();
    }


    private List<FuncionarioEntityDomain> mapDomainFuncionarios(List<FuncionarioEntityJpa> funcionariosJpa) {
        return funcionariosJpa.stream()
                .map(mapperFuncionario::toDomain)
                .toList();
    }
    private List<FuncionarioEntityJpa> mapEntityFuncionarios(List<FuncionarioEntityDomain> funcionariosDomain) {
        return funcionariosDomain.stream()
                .map(mapperFuncionario::toEntity)
                .toList();
    }

}
