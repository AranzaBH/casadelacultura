package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAudioLibro;

    @NonNull
    private String asin;

    @NonNull
    private String nombreEditorial;

    @ManyToOne
    @JoinColumn(name = "idCategoriaLibro", nullable = false)
    private CategoriaLibro categoriaLibro;

    

    @ManyToOne
    @JoinColumn(name = "idObrasFonograficas", nullable = false)
    private ObrasFonograficas obrasFonograficas;
}
