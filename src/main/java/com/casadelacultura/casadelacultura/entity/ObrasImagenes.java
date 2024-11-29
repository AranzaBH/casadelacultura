package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@Entity
public class ObrasImagenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObrasImagenes;

    @OneToOne
    @NotNull(message = "El ID del Imagenes es abligatoria")
    @JoinColumn(name = "idImagenes", nullable = false)
    private Imagenes imagenes;

    @OneToOne
    @NotNull(message = "El ID del obra abligatoria")
    @JoinColumn(name = "idObras", nullable = false)
    private Obra obra;
}
