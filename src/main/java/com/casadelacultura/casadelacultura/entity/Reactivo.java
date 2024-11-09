package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
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

    @NonNull
    private String pregunta;

    
    @NonNull
    private String respuestaCorrecta;

    @NonNull
    private String respuesta1;

    @NonNull
    private String respuesta2;

    @NonNull
    private String respuesta3;

    private String respuesta4;

    private String respuesta5;
}