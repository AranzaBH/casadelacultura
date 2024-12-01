package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;


@Getter
@Setter
@Entity 
@RequiredArgsConstructor 
@NoArgsConstructor 
public class Reactivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autogenera el valor del ID.
    private Long idReactivo;

    @NotBlank(message = "La pregutna es obligatorio")
    @Size(max = 200, message = "La pregunta no puede tener más de 200 caracteres")
    @Column(nullable = false, length = 200)
    private String pregunta;

    
    @NotBlank(message = "La respuesta correcta es obligatorio")
    @Size(max = 100, message = "La respuesta correcta no puede tener más de 200 caracteres")
    @Column(nullable = false, length = 100)
    private String respuestaCorrecta;

    @NonNull
    @NotBlank(message = "La primer respuesta incorrecta es obligatorio")
    @Size(max = 100, message = "La primer respuesta incorrecta no puede tener más de 200 caracteres")
    @Column(nullable = false, length = 100)
    private String respuesta1;

    @NonNull
    @NotBlank(message = "La segunda respuesta incorrecta es obligatorio")
    @Size(max = 100, message = "La segunda respuesta incorrecta no puede tener más de 200 caracteres")
    @Column(nullable = false, length = 100)
    private String respuesta2;

    @NonNull
    @NotBlank(message = "La tercera respuesta incorrecta es obligatorio")
    @Size(max = 100, message = "La tercera respuesta incorrecta no puede tener más de 200 caracteres")
    @Column(nullable = false, length = 100)
    private String respuesta3;

    private String respuesta4;

    private String respuesta5;

    @OneToOne
    @JoinColumn(name = "idCuestionario", nullable = false)
    @NotNull(message = "El ID de la pregunta es obligatoria")
    private Cuestionario cuestionario;
}