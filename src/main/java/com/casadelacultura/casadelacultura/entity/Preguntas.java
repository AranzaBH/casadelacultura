package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity 
public class Preguntas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPreguntas;

    @OneToOne
    @JoinColumn(name = "idCuestionario", nullable = false)
    private Cuestionario cuestionario;

    @OneToOne
    @JoinColumn(name = "idReactivo", nullable = false)
    private Reactivo reactivo;

}
