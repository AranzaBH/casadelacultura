package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Actividades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActividades;

    @OneToOne
    @JoinColumn(name = "idVideo", nullable = false)
    private Video video;

    @OneToOne
    @JoinColumn(name = "idCuestionario", nullable = false)
    private Cuestionario cuestionario;

    @OneToOne
    @JoinColumn(name = "idTaller", nullable = false)
    private Taller taller;

    @NonNull
    private int modulo;

    private int avance;

    private boolean estatus;
}
