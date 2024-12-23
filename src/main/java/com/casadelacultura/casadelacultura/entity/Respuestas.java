package com.casadelacultura.casadelacultura.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Respuestas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRespuesta;

    @NotBlank(message = "La respuesta es obligatorio")
    @Size(max = 50, message = "La respuesta no puede tener más de 50 caracteres")
    @Column(nullable = false, length = 50)
    private String respuesta;

    @Column(nullable = false)
    private boolean esCorrecta;

    @NonNull
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "idPregunta", nullable = false)
    @NotNull(message = "El ID de la pregunta es obligatoria")
    @JsonBackReference
    private Preguntas preguntas; 
}