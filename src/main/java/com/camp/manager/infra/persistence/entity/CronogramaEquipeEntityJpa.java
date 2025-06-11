package com.camp.manager.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @JoinColumn(name = "id_cronograma")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private EquipeEntityJpa equipe;
}
