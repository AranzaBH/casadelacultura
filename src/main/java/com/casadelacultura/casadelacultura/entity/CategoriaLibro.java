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
public class CategoriaLibro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoriaLibro;

    @NonNull
    @NotBlank(message = "El nombre de la ategoria del libro es Obligatorio.")
    @Size(max = 100, message = "El nombre de la categoria no puede tener más de 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String nombreCategoria;

    @NonNull
    @NotBlank(message = "La descripción es obligatoria.")
    @Size(max = 1000, message = "La descripción no puede tener más de 1000 caracteres.")
    @Column(nullable = false, length = 1000)
    private String descripcionCategoria;
}
