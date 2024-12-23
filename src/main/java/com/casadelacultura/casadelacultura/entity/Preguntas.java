package com.casadelacultura.casadelacultura.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Preguntas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPregunta;

    @NotBlank(message = "La pregunta es obligatorio")
    @Size(max = 100, message = "La pregunta no puede tener más de 100 caracteres")
    @Column(nullable = false, length = 100)
    private String pregunta;

    @NonNull
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "idCuestionario", nullable = false)
    @NotNull(message = "El ID del cuestionario es obligatoria")
    @JsonBackReference
    private Cuestionario cuestionario; 

    @OneToMany(mappedBy = "preguntas", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuestas> respuestas;
}