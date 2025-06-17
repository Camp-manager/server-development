package com.camp.manager.infra.persistence.entity;

import com.camp.manager.domain.enums.TipoItem;
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
@Table(name = "item", schema = "public")
public class ItemEntityJpa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "quantidade")
    private Long quantidade;

    @Column(name = "tipo")
    private TipoItem tipoItem;

    @Column(name = "validade")
    private String validade;

    @Column(name = "valor")
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "id_estoque")
    private EstoqueEntityJpa estoque;

    public ItemEntityJpa(Long id, String descricao, Long quantidade, TipoItem tipoItem, String validade, Double valor) {
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.tipoItem = tipoItem;
        this.validade = validade;
        this.valor = valor;
    }
}
