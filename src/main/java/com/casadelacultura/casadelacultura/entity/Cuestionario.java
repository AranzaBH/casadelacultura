package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Cuestionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuestionario;

    @NotBlank(message = "El nombre del cuestionario es obligatorio")
    @Size(max = 200, message = "El nombre del cuestionario no puede tener más de 200 caracteres")
    @Column(nullable = false, length = 200)
    private String nombreCuestionario;

    @NotBlank(message = "La instrcción es obligatoria")
    @Size(max = 1000, message = "La instruccion no puede tener más de 1000 caracteres")
    @Column(nullable = false, length = 1000)
    private String instrucciones;

    @NonNull
    private Integer calificacion;

}
