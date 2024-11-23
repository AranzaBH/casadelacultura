package com.casadelacultura.casadelacultura.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObra;

    @lombok.NonNull
    private String tituloObra;

    @lombok.NonNull
    @Column(nullable = false)
    private String tituloOriginalObra;

    @lombok.NonNull
    @Column(nullable = false, length = 10000)
    private String descripcion;

    @NonNull
    @Column(nullable = false)
    private String dimension;

    @NonNull
    private String localizacion;

    @NonNull
    private LocalDate fechaObra;

    @Transient
    private String idUrlImagenPortada;

    private String imagenPath;

    private boolean estadoActivo;

    @NonNull
    private LocalDateTime fechaCreacion;

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