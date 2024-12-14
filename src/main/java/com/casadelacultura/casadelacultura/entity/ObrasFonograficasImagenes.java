package com.casadelacultura.casadelacultura.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ObrasFonograficasImagenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObrasFonograficasImagenes;

    @OneToOne
    @NotNull(message = "El ID del Imagenes es abligatoria")
    @JoinColumn(name = "idImagenes", nullable = false)
    private Imagenes imagenes;

    @OneToOne
    @NotNull(message = "El ID de las obras fonograficas es abligatoria")
    @JoinColumn(name = "idObrasFonograficas", nullable = false)
    private ObrasFonograficas obrasFonograficas;



}
