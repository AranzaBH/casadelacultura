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
    private Long idCategoriaObra;

    @NonNull
    @Column(nullable = false)
    private String nombreCategoria;

    @NonNull
    private String descripcionCategoria;
}
