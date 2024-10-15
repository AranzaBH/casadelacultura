package com.casadelacultura.casadelacultura.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Taller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTaller;

    @NonNull
    private String tituloTaller;

    @NonNull
    private String clave;


    @NonNull
    private String descripcion;

    @NonNull
    private LocalDateTime  fechaInico;

    @NonNull
    private LocalDateTime  fechaFinal;

    @NonNull
    private LocalDateTime  fechaCreacion;

    @OneToOne
    @JoinColumn(name = "idTipoTaller", nullable = false)
    private TipoTaller tipoTaller;

    @NonNull
    private boolean estaActivo;
}
