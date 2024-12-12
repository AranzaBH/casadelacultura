package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Entity
public class ObrasFonograficasPorAutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObrasFonograficasPorAutor;

    @OneToOne
    @NotNull(message = "El ID del autor es abligatoria")
    @JoinColumn(name = "idAutorFK", nullable = false)
    private Autor autor;

    @OneToOne
    @NotNull(message = "El ID de la obra fonografica es abligatoria")
    @JoinColumn(name = "idObraFonograficas", nullable = false)
    private ObrasFonograficas obrasFonograficas;
}