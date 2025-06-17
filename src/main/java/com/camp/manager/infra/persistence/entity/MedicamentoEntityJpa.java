package com.camp.manager.infra.persistence.entity;

import com.camp.manager.domain.enums.TipoMedicamento;
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
@Table(name = "medicamento", schema = "public")
public class MedicamentoEntityJpa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "quantidade")
    private String quantidade;

    @Column(name = "tipo")
    private TipoMedicamento tipo;

    @Column(name = "valor")
    private Double valor;
}
