package com.casadelacultura.casadelacultura.entity;


import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.*;



@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibro;

    @NonNull
    @NotBlank(message = "El título del libro es obligatorio")
    private String tituloLibro;

    @NonNull
    private String asin;

    @NonNull
    @NotBlank(message = "El nombre de la editorial es obligatorio")
    private String nombreEditorial;

    @NonNull
    @Min(value = 1, message = "La edición debe ser al menos 1")
    private Integer edicionlibro;

    @NonNull
    private String lugarProsedenciaLibro;

    @NonNull
    @Min(value = 1, message = "La cantidad de páginas debe ser al menos 1")
    private Integer cantidadPaginas;

    @NonNull
    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NonNull
    private String imagenPath;

    @Transient
    private String idUrlImagenPortada;

    @NonNull
    private Boolean estaActivo;

    @NonNull
    private LocalDate fechaLibro;

    @NonNull
    private LocalDateTime fechaCreacion;//Lo define el sistema

    @ManyToOne
    @JoinColumn(name = "idCategoriaLibro", nullable = false)
    @NotNull(message = "La categoría del libro es obligatoria")
    private CategoriaLibro categoriaLibro;

    
}
