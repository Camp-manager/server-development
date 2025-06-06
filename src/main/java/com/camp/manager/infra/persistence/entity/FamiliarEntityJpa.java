package com.camp.manager.infra.persistence.entity;


import com.camp.manager.domain.enums.Parentesco;
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
@Table(name = "familiar", schema = "public")
public class FamiliarEntityJpa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "parentesco")
    private Parentesco parentesco;

    @Column(name = "telefone")
    private String telefone;

    @JoinColumn(name = "id_endereco")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private EnderecoEntityJpa endereco;

}
