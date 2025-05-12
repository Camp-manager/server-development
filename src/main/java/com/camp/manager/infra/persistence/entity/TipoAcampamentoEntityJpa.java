package com.camp.manager.infra.persistence.entity;

import com.camp.manager.domain.enums.CategoriaTipoAcampamento;
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
@Table(name = "tipo_acampamento", schema = "public")
public class TipoAcampamentoEntityJpa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "categoria")
    private CategoriaTipoAcampamento categoriaTipoAcampamento;

}
