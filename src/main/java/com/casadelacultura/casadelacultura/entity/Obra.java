package com.casadelacultura.casadelacultura.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "El título de la obra es obligatorio")
    @Size(max = 200, message = "El titulo de la obra no puede tener más de 200 caracteres")
    @Column(nullable = false, length = 200)
    private String tituloObra;

    @NotBlank(message = "El título original de la obra es obligatorio")
    @Size(max = 200, message = "El titulo original de la obra no puede tener más de 200 caracteres")
    @Column(nullable = false, length = 200)
    private String tituloOriginalObra;

    @NotBlank(message = "El codigo de la obra es obligatorio")
    @Size(max = 30, message = "La dimension no pude tener mas de 30 caracteres")
    @Column(nullable = false, length = 30)
    private String codigo;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 1000, message = "La descripción no puede tener más de 1000 caracteres")
    @Column(nullable = false, length = 1000)
    private String descripcion;

    @NotBlank(message = "La dimencion es obligatoria")
    @Size(max = 50, message = "La dimensión no puede tener más de 50 caracteres")
    @Column(nullable = false, length = 50)
    private String dimension;

    @NotBlank(message = "El material es obligatoria")
    @Size(max = 1000, message = "Los materiales no puede tener más de 1000 caracteres")
    @Column(nullable = false, length = 1000)
    private String material;

    private String localizacion;

    @NotNull(message = "La fecha de la obra es obligatoria")
    @Column(nullable = false)
    private LocalDate fechaObra;

    @Transient
    private String urlImagenPortada;

    private String imagenPath;

    private boolean estadoActivo;

    @NonNull
    private LocalDateTime fechaCreacion;

    @OneToOne
    @JoinColumn(name = "idTecnica", nullable = false)
    @NotNull(message = "La tecnica de la obra es obligatoria")
    private Tecnica tecnica;

    @OneToOne
    @JoinColumn(name = "idCategoriaObra", nullable = false)
    @NotNull(message = "La categoría de la obra es obligatoria")
    private CategoriaObra categoriaObra;
}