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
public class Modulo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idModulo;

    @NonNull
    private String nombreModulo;
}

