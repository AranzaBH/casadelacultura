package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class CategoriaObra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoriaObra;

    @NonNull
    @NotBlank(message = "El Nombre de la Categoria es Obligatorio")
    @Size(max = 100, message = "El Nombre de la Categoria no puede tener más de 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombreCategoria;

    @NonNull
    @NotBlank(message = "La Descripción es Obligatoria")
    @Size(max = 1000, message = "La Descripción no puede tener más de 1000 caracteres")
    @Column(nullable = false, length = 1000)
    private String descripcionCategoria;
}
