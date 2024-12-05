package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;
import java.time.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Taller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTaller;

    @NotBlank(message = "El Titulo del Taller es obligatorio")
    @Size(max = 200, message = "El Titulo del Taller no puede tener más de 200 caracteres")
    @Column(nullable = false, length = 200)
    private String tituloTaller;

    @NotBlank(message = "La descripcion obligatorio")
    @Size(max = 1000, message = "La descripcion no puede tener más de 200 caracteres")
    @Column(nullable = false, length = 100)
    private String descripcion;

    @OneToOne
    @JoinColumn(name = "idTipoTaller", nullable = false)
    @NotNull(message = "El ID del tipo taller es obligatoria")
    private TipoTaller tipoTaller;

    private LocalDate  fechaInico;

    private LocalDate  fechaFinal;

    @NotBlank(message = "La es obligatoria")
    @Size(max = 30, message = "La no puede tener más de 30 caracteres")
    @Column(nullable = false, length = 30)
    private String clave;

    private LocalDateTime  fechaCreacion;
    
    private boolean estaActivo = false;

    @NonNull
    private String imagenPath;

    @Transient
    private String urlImagenPortadaTaller;

    private Integer avanceGeneral;
}
