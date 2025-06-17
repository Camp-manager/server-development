package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.*;
import com.camp.manager.domain.enums.TipoEquipe;
import com.camp.manager.infra.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class EquipeMapper implements Mapper<EquipeEntityJpa, EquipeEntityDomain>{

    private final CronogramaEquipeMapper mapperCronograma;
    private final AcampamentoMapper mapperAcampamento;
    private final CampistaMapper mapperCampista;
    private final FuncionarioMapper mapperFuncionario;
    private final EquipeDiaFuncaoMapper mapperEquipeDiaFuncao;

    @Autowired
    public EquipeMapper(@Lazy CronogramaEquipeMapper mapperCronograma,
                        @Lazy AcampamentoMapper mapperAcampamento,
                        @Lazy CampistaMapper mapperCampista,
                        @Lazy FuncionarioMapper mapperFuncionario,
                        @Lazy EquipeDiaFuncaoMapper mapperEquipeDiaFuncao) {
        this.mapperCronograma = mapperCronograma;
        this.mapperAcampamento = mapperAcampamento;
        this.mapperCampista = mapperCampista;
        this.mapperFuncionario = mapperFuncionario;
        this.mapperEquipeDiaFuncao = mapperEquipeDiaFuncao;
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
                equipeEntityJpa.getLiderDeTime() != null ? this.mapperFuncionario.toDomainWithoutEquipe(equipeEntityJpa.getLiderDeTime()) : null,
                this.mapDomainFuncoes(equipeEntityJpa.getEquipeDiaFuncao())
        );
    }

    public EquipeEntityDomain toDomainWithoutRelationships(EquipeEntityJpa equipeEntityJpa) {
        if (equipeEntityJpa == null) {
            return null;
        }
        return new EquipeEntityDomain(
                equipeEntityJpa.getId(),
                equipeEntityJpa.getNome(),
                equipeEntityJpa.getTipoEquipe().getDescricao(),
                new ArrayList<>(),
                this.mapperAcampamento.toDomainWithoutEquipes(equipeEntityJpa.getAcampamento()),
                new ArrayList<>(),
                new ArrayList<>(),
                equipeEntityJpa.getLiderDeTime() != null ? this.mapperFuncionario.toDomainWithoutEquipe(equipeEntityJpa.getLiderDeTime()) : null,
                new ArrayList<>()
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
                equipeEntityDomain.liderDaEquipe() != null ? this.mapperFuncionario.toEntity(equipeEntityDomain.liderDaEquipe()) : null,
                new ArrayList<>()
        );

        if (equipeEntityDomain.cronograma() != null) {
            List<CronogramaEquipeEntityJpa> cronogramasJpa = equipeEntityDomain.cronograma().stream()
                    .filter(Objects::nonNull)
                    .map(cronogramaDomain -> {
                        CronogramaEquipeEntityJpa cronogramaJpa = this.mapperCronograma.toEntity(cronogramaDomain);
                        cronogramaJpa.setEquipe(equipeJpa);
                        return cronogramaJpa;
                    })
                    .collect(Collectors.toList());
            equipeJpa.setCronograma(cronogramasJpa);
        }

        if (equipeEntityDomain.diasDaFuncao() != null) {
            List<EquipeDiaFuncaoEntityJpa> funcoesJpa = this.mapEntityFuncoes(equipeEntityDomain.diasDaFuncao());
            funcoesJpa.forEach(funcao -> funcao.setEquipe(equipeJpa));
            equipeJpa.setEquipeDiaFuncao(funcoesJpa);
        }


        if (equipeEntityDomain.campistasNaEquipe() != null) {
            List<CampistaEntityJpa> campistasJpa = this.mapEntityCampistas(equipeEntityDomain.campistasNaEquipe());
            campistasJpa.forEach(campista -> campista.setEquipe(equipeJpa)); // <<< Vínculo
            equipeJpa.setCampistas(campistasJpa);
        }

        if (equipeEntityDomain.funcionariosNaEquipe() != null) {
            List<FuncionarioEntityJpa> funcionariosJpa = this.mapEntityFuncionarios(equipeEntityDomain.funcionariosNaEquipe());
            funcionariosJpa.forEach(funcionario -> funcionario.setEquipe(equipeJpa));
            equipeJpa.setFuncionarios(funcionariosJpa);
        }

        return equipeJpa;
    }

    public EquipeEntityJpa toEntityWithoutCronogramas(EquipeEntityDomain equipeEntityDomain) {
        if (equipeEntityDomain == null) {
            return null;
        }

        EquipeEntityJpa equipeJpa = new EquipeEntityJpa(
                equipeEntityDomain.id(),
                equipeEntityDomain.nome(),
                TipoEquipe.fromDescricao(equipeEntityDomain.tipoEquipe()),
                new ArrayList<>(),
                this.mapperAcampamento.toEntity(equipeEntityDomain.acampamento()),
                new ArrayList<>(),
                new ArrayList<>(),
                equipeEntityDomain.liderDaEquipe() != null ? this.mapperFuncionario.toEntity(equipeEntityDomain.liderDaEquipe()) : null,
                new ArrayList<>()
        );

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

        if (equipeEntityDomain.diasDaFuncao() != null) {
            List<EquipeDiaFuncaoEntityJpa> funcoesJpa = this.mapEntityFuncoes(equipeEntityDomain.diasDaFuncao());
            funcoesJpa.forEach(funcao -> funcao.setEquipe(equipeJpa));
            equipeJpa.setEquipeDiaFuncao(funcoesJpa);
        }

        return equipeJpa;
    }

    private List<EquipeDiaFuncaoEntityDomain> mapDomainFuncoes(List<EquipeDiaFuncaoEntityJpa> funcoesJpa) {
        if (funcoesJpa == null) return new ArrayList<>();
        return funcoesJpa.stream()
                .map(mapperEquipeDiaFuncao::toDomain)
                .collect(Collectors.toList());
    }

    private List<EquipeDiaFuncaoEntityJpa> mapEntityFuncoes(List<EquipeDiaFuncaoEntityDomain> funcoesDomain) {
        if (funcoesDomain == null) return new ArrayList<>();
        return funcoesDomain.stream()
                .map(mapperEquipeDiaFuncao::toEntity)
                .collect(Collectors.toList());
    }

    private List<CampistaEntityDomain> mapDomainCampistas(List<CampistaEntityJpa> campistasJpa) {
        if (campistasJpa == null) return new ArrayList<>();
        return campistasJpa.stream()
                .map(mapperCampista::toDomainWithoutEquipe)
                .collect(Collectors.toList());
    }
    private List<CampistaEntityJpa> mapEntityCampistas(List<CampistaEntityDomain> campistasJpa) {
        return campistasJpa.stream()
                .map(mapperCampista::toEntity)
                .collect(Collectors.toList());
    }


    private List<FuncionarioEntityDomain> mapDomainFuncionarios(List<FuncionarioEntityJpa> funcionariosJpa) {
        if (funcionariosJpa == null) return new ArrayList<>();
        return funcionariosJpa.stream()
                .map(mapperFuncionario::toDomainWithoutEquipe)
                .collect(Collectors.toList());
    }
    private List<FuncionarioEntityJpa> mapEntityFuncionarios(List<FuncionarioEntityDomain> funcionariosDomain) {
        return funcionariosDomain.stream()
                .map(mapperFuncionario::toEntity)
                .collect(Collectors.toList());
    }


    private List<CronogramaEquipeEntityDomain> mapDomainCronogramas(List<CronogramaEquipeEntityJpa> cronogramasJpa) {
        return cronogramasJpa.stream()
                .map(mapperCronograma::toDomain)
                .collect(Collectors.toList());
    }
    private List<CronogramaEquipeEntityJpa> mapEntityCronogramas(List<CronogramaEquipeEntityDomain> cronogramasDomain) {
        return cronogramasDomain.stream()
                .map(mapperCronograma::toEntity)
                .collect(Collectors.toList());
    }


    public EquipeEntityDomain toDomainWithoutCronogramas(EquipeEntityJpa equipeEntityJpa) {
        if (equipeEntityJpa == null) {
            return null;
        }
        return new EquipeEntityDomain(
                equipeEntityJpa.getId(),
                equipeEntityJpa.getNome(),
                equipeEntityJpa.getTipoEquipe().getDescricao(),
                new ArrayList<>(),
                this.mapperAcampamento.toDomainWithoutEquipes(equipeEntityJpa.getAcampamento()),
                this.mapDomainCampistas(equipeEntityJpa.getCampistas()),
                this.mapDomainFuncionarios(equipeEntityJpa.getFuncionarios()),
                equipeEntityJpa.getLiderDeTime() != null ? this.mapperFuncionario.toDomainWithoutEquipe(equipeEntityJpa.getLiderDeTime()) : null,
                this.mapDomainFuncoes(equipeEntityJpa.getEquipeDiaFuncao())
        );
    }

}
