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
@Table(name = "pessoa_medicamento_controller", schema = "public")
public class PessoaMedicamentoControllerEntityJpa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private PessoaEntityJpa pessoa;

    @ManyToOne
    @JoinColumn(name = "id_medicamento")
    private MedicamentoEntityJpa medicamento;
}
