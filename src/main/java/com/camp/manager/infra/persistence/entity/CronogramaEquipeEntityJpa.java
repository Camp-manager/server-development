package com.camp.manager.infra.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "id_equipe")
    private Long equipeId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_equipe", insertable = false, updatable = false)
    private EquipeEntityJpa equipe;

    public CronogramaEquipeEntityJpa(Long id, String dataInicio, String dataFinal, String descricao, Long equipeId) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.descricao = descricao;
        this.equipeId = equipeId;
    }
}
