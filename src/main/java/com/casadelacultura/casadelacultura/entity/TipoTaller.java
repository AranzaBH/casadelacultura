package com.casadelacultura.casadelacultura.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;


@Getter
@Setter
@Entity 
@RequiredArgsConstructor 
@NoArgsConstructor 
public class TipoTaller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autogenera el valor del ID.
    private Long idTipoTaller;

    @NotBlank(message = "El nombre del la categoria es obligatorio")
    @Size(max = 200, message = "El nombre del la categoria no puede tener más de 200 caracteres")
    @Column(nullable = false, length = 200)
    private String nombreTipoTaller;

    
    @NotBlank(message = "La descripcion es obligatorio")
    @Size(max = 200, message = "La descripcion no puede tener más de 200 caracteres")
    @Column(nullable = false, length = 200)
    private String descripcion;

    @NonNull
    private LocalDateTime fechaCreacion;

    // Relación con Talleres: Un Categoria puede tener varios Talleres.
    @OneToMany(mappedBy = "tipoTaller", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("tipoTaller")
    private List <Taller>taller;
}