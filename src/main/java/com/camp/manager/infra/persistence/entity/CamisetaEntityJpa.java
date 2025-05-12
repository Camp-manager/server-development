package com.camp.manager.infra.persistence.entity;


import com.camp.manager.domain.enums.TamanhoCamiseta;
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
@Table(name = "camiseta", schema = "public")
public class CamisetaEntityJpa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tamanho")
    private TamanhoCamiseta tamanhoCamiseta;

    @JoinColumn(name = "id_tema")
    @ManyToOne(fetch = FetchType.LAZY)
    private TemaEntityJpa tema;


}
