package com.camp.manager.infra.persistence.entity;

import com.camp.manager.domain.enums.LocalEstoque;
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
@Table(name = "estoque", schema = "public")
public class EstoqueEntityJpa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local")
    private LocalEstoque localEstoque;

    @Column(name = "quantidade")
    private Long quantidade;

    @Column(name = "limite")
    private Long limite;

}
