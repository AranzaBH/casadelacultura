package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ObrasFonograficasPorAutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutor;

    @OneToOne
    @JoinColumn(name = "idAutorFK", nullable = false)
    private Autor autor;

    @OneToOne
    @JoinColumn(name = "idObraFonograficas", nullable = false)
    private ObrasFonograficas obrasFonograficas;
}
