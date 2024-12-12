package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class ObrasFonograficas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObrasFonograficas;

    @NonNull
    @NotBlank(message = "El título de la obra fonografica es obligatorio")
    @Size(max = 200, message = "El titulo dela obra fonografica no puede tener más de 200 caracteres")
    @Column(nullable = false, length = 200)
    private String tituloObraFonografica;

    @NonNull
    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 1000, message = "La descripción no puede tener más de 1000 caracteres")
    @Column(nullable = false, length = 1000)
    private String descripcion;

    @NotBlank(message = "El codigo de la obra es obligatorio")
    @Size(max = 30, message = "La dimension no pude tener mas de 30 caracteres")
    @Column(nullable = false, length = 30)
    private String codigo;

    private Integer duracion;

    @NonNull
    private LocalDate fechaLanzamiento;

    private LocalDateTime fechaCreacion;// Lo define el sistema

    private String imagenPath;

    @Transient
    private String urlImagenPortada;

    @NonNull
    private Boolean activo;
}
