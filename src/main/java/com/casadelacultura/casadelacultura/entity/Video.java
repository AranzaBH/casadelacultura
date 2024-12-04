package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVideo;

    @NotBlank(message = "El nombre del video es obligatorio")
    @Size(max = 200, message = "El nombre nombre no puede tener más de 200 caracteres")
    @Column(nullable = false, length = 200)
    private String titulo;

    @NotBlank(message = "La URL del Video es obligatorio")
    @Size(max = 200, message = "La URL del video no puede tener más de 200 caracteres")
    @Column(nullable = false, length = 200)
    private String urlVideo;
}
