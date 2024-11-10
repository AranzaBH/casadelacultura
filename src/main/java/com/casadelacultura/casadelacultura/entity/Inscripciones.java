package com.casadelacultura.casadelacultura.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

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
    @JoinColumn(name = "idusuarios", nullable = false)
    private Usuario usuario;

    @NonNull
    private LocalDateTime fechaInscripcion;

    @OneToOne
    @JoinColumn(name = "idTalleres", nullable = false)
    private  Taller taller;

    private int avanceGeneral;
}
