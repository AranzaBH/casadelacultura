package com.casadelacultura.casadelacultura.entity;

import java.time.LocalDateTime;


import javax.persistence.*;
import lombok.Getter;
import lombok.NonNull;
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

    @lombok.NonNull
    private String nombreObra;

   
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