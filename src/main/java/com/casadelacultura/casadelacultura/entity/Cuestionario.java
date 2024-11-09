package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Cuestionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuestionario;

    @NonNull
    private String Calificacion;

}
