package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class LibrosPorAutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutor;

    @OneToOne
    @JoinColumn(name = "idAutorF", nullable = false)
    private Autor autor;

    @OneToOne
    @JoinColumn(name = "idLibro", nullable = false)
    private Libro libro;
}
