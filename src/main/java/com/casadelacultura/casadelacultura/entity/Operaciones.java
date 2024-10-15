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



@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Operaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idOperaciones;

    @NonNull
    private String nombreOperacion;

    @NonNull
    private String descripcion;

    @OneToOne
    @JoinColumn(name = "idModulo", nullable = false)
    private Modulo modulo;
}


