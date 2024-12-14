package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @NotBlank(message = "El el nombre de la editoiral es obligatorio")
    @Size(max = 200, message = "El nombre de la editorial no puede tener más de 200 caracteres")
    @Column(nullable = false, length = 200)
    private String nombreEditorial;

    private String asin;

    @ManyToOne
    @JoinColumn(name = "idCategoriaLibro", nullable = false)
    @NotNull(message = "El ID de la categoria del libro es obligatoria ")
    private CategoriaLibro categoriaLibro;

    @ManyToOne
    @JoinColumn(name = "idObrasFonograficas", nullable = false)
    @NotNull(message = "El ID de la obra fonografica es obligatoria ")
    private ObrasFonograficas obrasFonograficas;
}
