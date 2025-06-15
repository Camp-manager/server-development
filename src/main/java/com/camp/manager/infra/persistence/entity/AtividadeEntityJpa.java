package com.camp.manager.infra.persistence.entity;

import com.camp.manager.domain.enums.TipoAtividade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "atividade", schema = "public")
public class AtividadeEntityJpa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo")
    private TipoAtividade tipoAtividade;

    @Column(name = "horario")
    private String horario;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cronograma")
    private CronogramaEquipeEntityJpa cronograma;
}