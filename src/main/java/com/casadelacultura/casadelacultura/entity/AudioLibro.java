package com.casadelacultura.casadelacultura.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class AudioLibro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAudioLibro;

    @NonNull
    private String asin;

    @NonNull
    private String nombreEditorial;

    @ManyToOne
    @JoinColumn(name = "idCategoriaLibro", nullable = false)
    private CategoriaLibro categoriaLibro;

    @ManyToOne
    @JoinColumn(name = "idTipoLibro", nullable = false)
    private TipoLibro tipoLibro;

    @ManyToOne
    @JoinColumn(name = "idObrasFonograficas", nullable = false)
    private ObrasFonograficas obrasFonograficas;
}