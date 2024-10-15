package com.casadelacultura.casadelacultura.entity;

import java.time.LocalDateTime;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idObra;

    @NonNull
    private String nombreObra;

    @NonNull
    private boolean estadoActivo;

    @NonNull
    private LocalDateTime fechaCreacion;

    @NonNull
    private String dimension;

    @NonNull
    private String idUrlImagenPortada;

    @NonNull
    private String nombreUbicacionCreacion;

    
    @OneToOne
    @JoinColumn(name = "idTecnica", nullable = false)
    private Tecnica tecnica;


    @OneToOne
    @JoinColumn(name = "idMaterial", nullable = false)
    private Material material;

    
    @OneToOne
    @JoinColumn(name = "idCategoriaObra", nullable = false)
    private CategoriaObra categoriaObra;



    
}