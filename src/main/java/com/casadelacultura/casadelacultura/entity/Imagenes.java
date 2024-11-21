package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity 
@RequiredArgsConstructor
@NoArgsConstructor
public class Imagenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImagen;

    @NonNull
    private String imagenPath;

    @Transient
    private String urlImagen;
}
