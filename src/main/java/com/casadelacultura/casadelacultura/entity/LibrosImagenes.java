package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@Entity
public class LibrosImagenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibrosImagenes;

    @OneToOne
    @NotNull(message = "El ID del Imagenes es abligatoria")
    @JoinColumn(name = "idImagenes", nullable = false)
    private Imagenes imagenes;

    @OneToOne
    @NotNull(message = "El ID del libro es obligatoria ")
    @JoinColumn(name = "idLibro", nullable = false)
    private Libro libro;

}
