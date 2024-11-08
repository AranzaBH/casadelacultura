package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class CategoriaObra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoriaObra;

    @NonNull
    private String nombreCategoria;

    @NonNull
    private String descripcionCategoria;
}
