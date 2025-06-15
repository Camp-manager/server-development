package com.camp.manager.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(schema = "public", name = "cronograma_equipe")
public class CronogramaEquipeEntityJpa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_inicial")
    private String dataInicio;

    @Column(name = "data_final")
    private String dataFinal;

    @Column(name = "descricao")
    private String descricao;


    @JoinColumn(name = "id_equipe")
    @ManyToOne(fetch = FetchType.LAZY)
    private EquipeEntityJpa equipe;

    @OneToMany(
            mappedBy = "cronograma",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AtividadeEntityJpa> atividades = new ArrayList<>();

    public CronogramaEquipeEntityJpa(Long id, String dataInicio, String dataFinal, String descricao, EquipeEntityJpa equipe) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.descricao = descricao;
        this.equipe = equipe;
    }

    public void addAtividade(AtividadeEntityJpa atividade) {
        atividades.add(atividade);
        atividade.setCronograma(this);
    }

    public void removeAtividade(AtividadeEntityJpa atividade) {
        atividades.remove(atividade);
        atividade.setCronograma(null);
    }
}