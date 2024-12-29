package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class ObrasPorAutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObrasPorAutor;

    @ManyToOne
    @NotNull(message = "El ID del autor es abligatoria")
    @JoinColumn(name = "idAutores", nullable = false)
    @JsonIgnoreProperties({"obrasPorAutor"})
    private Autor autor;

    @ManyToOne
    @NotNull(message = "El ID de obra es abligatoria")
    @JoinColumn(name = "idObra", nullable = false)
    @JsonIgnoreProperties({"obrasPorAutor"})
    private Obra obra;

}
