package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class ObrasPorAutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObrasPorAutor;

    @OneToOne
    @NotNull(message = "El ID del autor es abligatoria")
    @JoinColumn(name = "idAutores", nullable = false)
    private Autor autor;

    @OneToOne
    @NotNull(message = "El ID de obra es abligatoria")
    @JoinColumn(name = "idObra", nullable = false)
    private Obra obra;

}
