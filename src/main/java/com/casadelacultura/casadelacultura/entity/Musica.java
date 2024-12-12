package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMusica;

    @NonNull
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 200, message = "El el nombre no puede tener más de 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NonNull
    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 1000, message = "La descripción no puede tener más de 1000 caracteres")
    @Column(nullable = false, length = 1000)
    private String descripcion;
    
    @ManyToOne
    @NotNull(message = "El Id de la obra fonografica es obligatoria")
    @JoinColumn(name = "idObrasFonograficas", nullable = false)
    private ObrasFonograficas obrasFonograficas;
}
