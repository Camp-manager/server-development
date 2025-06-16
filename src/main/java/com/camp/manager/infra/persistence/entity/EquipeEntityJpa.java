package com.camp.manager.infra.persistence.entity;

import com.camp.manager.domain.enums.TipoEquipe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @OneToMany(
            mappedBy = "equipe",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<CronogramaEquipeEntityJpa> cronograma;

    @JoinColumn(
            name = "id_acampamento"
    )
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE
    )
    private AcampamentoEntityJpa acampamento;

    @OneToMany(
            mappedBy = "equipe",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    private List<CampistaEntityJpa> campistas;

    @OneToMany(
            mappedBy = "equipe",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY
    )
    private List<FuncionarioEntityJpa> funcionarios;

    @OneToOne
    @JoinColumn(name = "id_funcionario_lider")
    private FuncionarioEntityJpa liderDeTime;
}
