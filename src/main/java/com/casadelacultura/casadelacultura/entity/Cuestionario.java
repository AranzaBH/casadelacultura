package com.casadelacultura.casadelacultura.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Cuestionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuestionario;

    @NotBlank(message = "El nombre del cuestionario es obligatorio")
    @Size(max = 100, message = "El nombre del cuestionario no puede tener más de 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombreCuestionario;

    @NotBlank(message = "La instruccion es obligatoria")
    @Size(max = 100, message = "La instruccion no puede tener más de 100 caracteres")
    @Column(nullable = false, length = 100)
    private String instruccion;

    @NonNull
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "cuestionario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("cuestionario")
    private List<Preguntas> preguntas; 

    @OneToMany(mappedBy = "cuestionario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("cuestionario") // Evita recursión infinita en serialización
    private List<Actividades> actividades;
}