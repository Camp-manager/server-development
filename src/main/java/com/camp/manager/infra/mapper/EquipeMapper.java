package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.*;
import com.camp.manager.domain.enums.TipoEquipe;
import com.camp.manager.infra.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EquipeMapper implements Mapper<EquipeEntityJpa, EquipeEntityDomain>{

    private final CronogramaEquipeMapper mapperCronograma;
    private final AcampamentoMapper mapperAcampamento;
    private final CampistaMapper mapperCampista;
    private final FuncionarioMapper mapperFuncionario;

    @Autowired
    public EquipeMapper(@Lazy CronogramaEquipeMapper mapperCronograma,
                        @Lazy AcampamentoMapper mapperAcampamento,
                        @Lazy CampistaMapper mapperCampista,
                        @Lazy FuncionarioMapper mapperFuncionario) {
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
                this.mapDomainCronogramas(equipeEntityJpa.getCronograma()),
                this.mapperAcampamento.toDomainWithoutEquipes(equipeEntityJpa.getAcampamento()),
                this.mapDomainCampistas(equipeEntityJpa.getCampistas()),
                this.mapDomainFuncionarios(equipeEntityJpa.getFuncionarios()),
                equipeEntityJpa.getCampistaLider() != null ? this.mapperCampista.toDomainWithoutEquipe(equipeEntityJpa.getCampistaLider()) : null
        );
    }

    @Override
    public EquipeEntityJpa toEntity(EquipeEntityDomain equipeEntityDomain) {
        EquipeEntityJpa equipeJpa = new EquipeEntityJpa(
                equipeEntityDomain.id(),
                equipeEntityDomain.nome(),
                TipoEquipe.fromDescricao(equipeEntityDomain.tipoEquipe()),
                new ArrayList<>(),
                this.mapperAcampamento.toEntity(equipeEntityDomain.acampamento()),
                new ArrayList<>(),
                new ArrayList<>(),
                equipeEntityDomain.liderDaEquipe() != null ? this.mapperCampista.toEntity(equipeEntityDomain.liderDaEquipe()) : null
        );

        if (equipeEntityDomain.cronograma() != null) {
            List<CronogramaEquipeEntityJpa> cronogramasJpa = equipeEntityDomain.cronograma().stream()
                    .map(cronogramaDomain -> {
                        CronogramaEquipeEntityJpa cronogramaJpa = this.mapperCronograma.toEntity(cronogramaDomain);
                        cronogramaJpa.setEquipe(equipeJpa);
                        return cronogramaJpa;
                    })
                    .toList();
            equipeJpa.setCronograma(cronogramasJpa);
        }

        if (equipeEntityDomain.campistasNaEquipe() != null) {
            List<CampistaEntityJpa> campistasJpa = this.mapEntityCampistas(equipeEntityDomain.campistasNaEquipe());
            campistasJpa.forEach(campista -> campista.setEquipe(equipeJpa));
            equipeJpa.setCampistas(campistasJpa);
        }

        if (equipeEntityDomain.funcionariosNaEquipe() != null) {
            List<FuncionarioEntityJpa> funcionariosJpa = this.mapEntityFuncionarios(equipeEntityDomain.funcionariosNaEquipe());
            funcionariosJpa.forEach(funcionario -> funcionario.setEquipe(equipeJpa));
            equipeJpa.setFuncionarios(funcionariosJpa);
        }

        return equipeJpa;
    }

    private List<CampistaEntityDomain> mapDomainCampistas(List<CampistaEntityJpa> campistasJpa) {
        if (campistasJpa == null) return new ArrayList<>();
        return campistasJpa.stream()
                .map(mapperCampista::toDomainWithoutEquipe) // Usar o método raso
                .toList();
    }
    private List<CampistaEntityJpa> mapEntityCampistas(List<CampistaEntityDomain> campistasJpa) {
        return campistasJpa.stream()
                .map(mapperCampista::toEntity)
                .toList();
    }


    private List<FuncionarioEntityDomain> mapDomainFuncionarios(List<FuncionarioEntityJpa> funcionariosJpa) {
        if (funcionariosJpa == null) return new ArrayList<>();
        return funcionariosJpa.stream()
                .map(mapperFuncionario::toDomainWithoutEquipe) // Usar o método raso
                .toList();
    }
    private List<FuncionarioEntityJpa> mapEntityFuncionarios(List<FuncionarioEntityDomain> funcionariosDomain) {
        return funcionariosDomain.stream()
                .map(mapperFuncionario::toEntity)
                .toList();
    }


    private List<CronogramaEquipeEntityDomain> mapDomainCronogramas(List<CronogramaEquipeEntityJpa> cronogramasJpa) {
        return cronogramasJpa.stream()
                .map(mapperCronograma::toDomain)
                .toList();
    }
    private List<CronogramaEquipeEntityJpa> mapEntityCronogramas(List<CronogramaEquipeEntityDomain> cronogramasDomain) {
        return cronogramasDomain.stream()
                .map(mapperCronograma::toEntity)
                .toList();
    }

}
