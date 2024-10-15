package com.casadelacultura.casadelacultura.entity;


import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
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
