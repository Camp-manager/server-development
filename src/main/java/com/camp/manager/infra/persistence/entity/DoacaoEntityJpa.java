package com.camp.manager.infra.persistence.entity;

import com.camp.manager.domain.enums.Resposta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.web.ReactiveOffsetScrollPositionHandlerMethodArgumentResolver;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doacao", schema = "public")
public class DoacaoEntityJpa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_doador")
    private String nomeDoador;

    @Column(name = "possui_validade")
    private Resposta possuiValidade;

    @Column(name = "data_validade")
    private String dataValidade;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_estoque")
    private EstoqueEntityJpa estoque;

}
