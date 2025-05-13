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
@Table(name = "funcionario", schema = "public")
public class FuncionarioEntityJpa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "habilidade")
    private String habilidade;

    @OneToOne
    @JoinColumn(name = "id_camiseta")
    private CamisetaEntityJpa camiseta;

    @ManyToOne
    @JoinColumn(name = "id_equipe")
    private EquipeEntityJpa equipe;

    @OneToOne
    @JoinColumn(name = "id_carteirinha")
    private CarteirinhaEntityJpa carteirinha;
}
