package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@Entity
public class ObrasImagenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObrasImagenes;

    @OneToOne
    @JoinColumn(name = "idImagenes", nullable = false)
    private Imagenes imagenes;

    @OneToOne
    @JoinColumn(name = "idObras", nullable = false)
    private Obra obra;
}
