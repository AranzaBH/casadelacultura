package com.casadelacultura.casadelacultura.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class RolOperaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idRolOperaciones;

    @NonNull
    private String nombreOperacion;

    
    @ManyToOne
    @JoinColumn(name = "idOperacion", nullable = false)
    private Operaciones operaciones;

    @ManyToOne
    @JoinColumn(name = "idRol", nullable = false)
    private Rol rol;
}

