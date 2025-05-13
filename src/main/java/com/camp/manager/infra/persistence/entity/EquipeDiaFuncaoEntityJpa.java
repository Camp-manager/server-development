package com.camp.manager.infra.persistence.entity;

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
@Table(name = "equipe_dia_funcao", schema = "public")
public class EquipeDiaFuncaoEntityJpa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "funcao")
    private String funcao;

    @Column(name = "data")
    private String data;

    @ManyToOne
    @JoinColumn(name = "id_equipe")
    private EquipeEntityJpa equipe;

}
