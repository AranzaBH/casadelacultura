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
    @Size(max = 200, message = "El titulo del libro no puede tener más de 200 caracteres")
    @Column(nullable = false, length = 200)
    private String tituloLibro;

    @Size(max = 30, message = "El ain no puede tener más de 30 caracteres")
    @Column(length = 30)
    private String asin;

    @NonNull
    @NotBlank(message = "El nombre de la editorial es obligatorio")
    @Size(max = 100, message = "El nombre de Editorial no puede tener más de 200 caracteres")
    @Column(nullable = false, length = 100)
    private String nombreEditorial;

    @NonNull
    @Min(value = 1, message = "La edición debe ser al menos 1")
    @Max(value = 100, message = "La cantidad de ediciones del libro no puede exceder 100")
    @Column(nullable = false)
    private Integer edicionlibro;

    @Size(max = 255, message = "El lugar de procedencia del libro no puede tener más de 255 caracteres")
    @Column(length = 255)
    private String lugarProsedenciaLibro;

    @NonNull
    @Min(value = 1, message = "La cantidad de páginas debe ser al menos 1")
    @Max(value = 9999, message = "La cantidad de páginas no puede exceder 9999")
    @Column(nullable = false)
    private Integer cantidadPaginas;

    @NonNull
    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 1000, message = "La descripción no puede tener más de 1000 caracteres")
    @Column(nullable = false, length = 1000)
    private String descripcion;

    private String imagenPath;

    @Transient
    private String urlImagenPortada;

    private Boolean estaActivo;

    private LocalDate fechaLibro;

    @NonNull
    private LocalDateTime fechaCreacion;// Lo define el sistema

    @ManyToOne
    @JoinColumn(name = "idCategoriaLibro", nullable = false)
    @NotNull(message = "La categoría del libro es obligatoria")
    private CategoriaLibro categoriaLibro;

}
