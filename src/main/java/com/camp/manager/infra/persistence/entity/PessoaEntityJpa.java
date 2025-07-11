package com.camp.manager.infra.persistence.entity;

import com.camp.manager.domain.enums.Resposta;
import com.camp.manager.domain.enums.Sexo;
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
@Table(name = "pessoa", schema = "public")
public class PessoaEntityJpa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "data_nascimento")
    private String dataNascimento;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "sexo")
    private Sexo sexo;

    @Column(name = "peso")
    private Double peso;

    @Column(name = "altura")
    private Double altura;

    @Column(name = "alimento_predileto")
    private String alimentoPredileto;

    @Column(name = "foi_batizada")
    private Resposta foiBatizado;

    @Column(name = "tem_primeira_comunhao")
    private Resposta temPrimeiraComunhao;

    @JoinColumn(name = "id_endereco")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private EnderecoEntityJpa endereco;

    @JoinColumn(name = "id_familiar")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private FamiliarEntityJpa familiar;

}
