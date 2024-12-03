package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

@Getter
@Setter
@Entity

public class Actividades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActividades;

    @NotBlank(message = "El nombre del la actividad es obligatoria")
    @Size(max = 200, message = "El nombre de la actividad no puede tener más de 200 caracteres")
    @Column(nullable = false, length = 200)
    private String nombreActividad;

    @OneToOne
    @JoinColumn(name = "idVideo", nullable = false)
    @NotNull(message = "El ID del video es obligatoria")
    private Video video;

    @ManyToOne
    @JoinColumn(name = "idCuestionario", nullable = true)
    private Cuestionario cuestionario;

    @OneToOne
    @JoinColumn(name = "idTaller", nullable = false)
    @NotNull(message = "El ID del taller es obligatoria")
    private Taller taller;

    private Integer modulo;

    private Integer avance;

    private boolean estatus;
}
