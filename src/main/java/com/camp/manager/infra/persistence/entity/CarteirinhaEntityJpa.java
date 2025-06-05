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
@Table(name = "carteirinha", schema = "public")
public class CarteirinhaEntityJpa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "texto_apresentacao")
    private String textoApresentacao;

    @JoinColumn(name = "id_tema")
    @ManyToOne(cascade = CascadeType.ALL)
    private TemaEntityJpa tema;
}
