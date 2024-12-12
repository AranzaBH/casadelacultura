package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Evaluaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvaluaciones;

    @NonNull
    @OneToOne
    @JoinColumn(name = "idInscripciones", nullable = false)
    @NotNull(message = "El ID de la inscripcion es obligatoria")
    private Inscripciones inscripciones;

    @OneToOne
    @JoinColumn(name = "idActividades", nullable = false)
    @NotNull(message = "El ID de la actividad es obligatoria")
    private Actividades actividades;

    private Integer calificacion;


}
