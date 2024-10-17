package com.casadelacultura.casadelacultura.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Modulo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idModulo;

    @NonNull
    private String nombreModulo;
}

