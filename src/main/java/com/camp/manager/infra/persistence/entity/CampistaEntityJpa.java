package com.camp.manager.infra.persistence.entity;

import com.camp.manager.domain.enums.Resposta;
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
@Table(name = "campista", schema = "public")
public class CampistaEntityJpa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usa_medicamento")
    private Resposta usaMedicamento;

    @Column(name = "tem_alergia")
    private Resposta temAlergia;

    @Column(name = "alegias")
    private String alergias;

    @Column(name = "codigo_acampamento")
    private String codigoRegistro;

    @Column(name = "ja_fez_acampamento")
    private Resposta jaFezAcampamento;

    @Column(name = "acampamentos_feitos")
    private String acampamentosFeitos;

    @Column(name = "tem_barraca")
    private Resposta temBarraca;

    @JoinColumn(name = "id_camiseta")
    @OneToOne(cascade = CascadeType.ALL)
    private CamisetaEntityJpa camiseta;

    @JoinColumn(name = "id_pessoa")
    @OneToOne(cascade = CascadeType.ALL)
    private PessoaEntityJpa pessoa;

    @JoinColumn(name = "id_equipe")
    @ManyToOne(cascade = CascadeType.ALL)
    private EquipeEntityJpa equipe;

}
