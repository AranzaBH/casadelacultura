package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Convocatorias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConvocatorias;

    @NonNull
    private LocalDateTime fechaInicioInscripcion;

    @NonNull
    private LocalDateTime fechaFinInscripcion;

    


    private boolean estaActiva;

    

}
