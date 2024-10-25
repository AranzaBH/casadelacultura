package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NonNull;
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


