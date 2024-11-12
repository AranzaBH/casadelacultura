package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class CategoriaLibro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoriaLibro;

    @NonNull
    private String nombreCategoria;

    @NonNull
    private String descripcionCategoria;
}
