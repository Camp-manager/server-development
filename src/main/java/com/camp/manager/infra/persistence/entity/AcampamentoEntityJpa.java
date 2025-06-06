package com.camp.manager.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "acampamento", schema = "public")
public class AcampamentoEntityJpa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "limite_campistas")
    private Long limiteCampistas;

    @Column(name = "limite_funcionario")
    private Long limiteFuncionario;

    @Column(name = "codigo_registro")
    private String codigoRegistro;

    @OneToOne
    @JoinColumn(name = "id_tema")
    private TemaEntityJpa tema;

    @ManyToOne
    @JoinColumn(name = "id_tipo_acampamento")
    private TipoAcampamentoEntityJpa tipoAcampamento;

    @JoinColumn(name = "id_cronograma")
    @OneToOne(cascade = CascadeType.ALL)
    private CronogramaEntityJpa cronograma;

    @OneToMany(mappedBy = "acampamento")
    private List<ImagemEntityJpa> listImagem;

    @OneToMany(mappedBy = "acampamento")
    private List<EquipeEntityJpa> listEquipe;
}
