package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Convocatorias {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idConvocatorias;

    @NonNull
    private String nombreConvocatoria;

    @NonNull
    private String descripcion;

    @NonNull
    private LocalDateTime  fechaPublicacion;

    @NonNull
    private LocalDateTime  fechaInicio;

    @NonNull
    private LocalDateTime  fechaFinalizacion;

}
