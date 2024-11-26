package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import lombok.*;
import javax.validation.constraints.*;

@Getter
@Setter
@Entity
public class LibrosPorAutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibroPorAutor;

    @OneToOne
    @NotNull(message = "El ID del autor es abligatoria")
    @JoinColumn(name = "idAutorF", nullable = false)
    private Autor autor;

    @OneToOne
    @JoinColumn(name = "idLibro", nullable = false)
    @NotNull(message = "El ID del libro es obligatoria ")
    private Libro libro;
}
