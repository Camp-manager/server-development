package com.camp.manager.infra.persistence.entity;

import com.camp.manager.domain.enums.TipoEquipe;
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
@Table(name = "equipe", schema = "public")
public class EquipeEntityJpa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo")
    private TipoEquipe tipoEquipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cronograma")
    private CronogramaEntityJpa cronograma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_acampamento")
    private AcampamentoEntityJpa acampamento;

}
