package com.casadelacultura.casadelacultura.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Inscripciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInscripciones;

    @OneToOne
    @NotNull(message = "El ID del usuario es abligatoria")
    @JoinColumn(name = "idusuarios", nullable = false)
    private Usuario usuario;

    @NonNull
    private LocalDateTime fechaInscripcion;

    @OneToOne
    @NotNull(message = "El ID del taller es abligatoria")
    @JoinColumn(name = "idTalleres", nullable = false)
    private  Taller taller;

    private int avanceGeneral;
}
