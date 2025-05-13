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
@Table(name = "acampamento_estoque_controller", schema = "public")
public class AcampamentoEstoqueControllerEntityJpa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_acampamento")
    private AcampamentoEntityJpa acampamento;

    @ManyToOne
    @JoinColumn(name = "id_estoque")
    private EstoqueEntityJpa estoque;
}
