package com.casadelacultura.casadelacultura.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class CategoriaObra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCategoriaObra;

    @NonNull
    private String nombreCategoria;

    @NonNull
    private String descripcionCategoria;
}