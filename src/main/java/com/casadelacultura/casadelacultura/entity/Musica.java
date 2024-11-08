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
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMusica;

    @NonNull
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "idObrasFonograficas", nullable = false)
    private ObrasFonograficas obrasFonograficas;
}
