package com.casadelacultura.casadelacultura.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ObrasFonograficasPorAutor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAutor;

    @OneToOne
    @JoinColumn(name = "idAutorFK", nullable = false)
    private Autor autor;

    @OneToOne
    @JoinColumn(name = "idObraFonograficas", nullable = false)
    private ObrasFonograficas obrasFonograficas;
}
