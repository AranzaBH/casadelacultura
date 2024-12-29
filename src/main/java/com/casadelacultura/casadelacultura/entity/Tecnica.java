package com.casadelacultura.casadelacultura.entity;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;*/
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
public class Tecnica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTecnica;

    @NonNull
    @NotBlank(message = "El Nombre de la Tecnica es Obligatorio")
    @Size(max = 100, message = "El Nombre de la Tecnica no puede tener más de 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombreTecnica;

    
    @NonNull
    @NotBlank(message = "La Descripción de la Tecnica es Obligatoria")
    @Size(max = 1000, message = "La Descripción de la Tecnica no puede tener más de 1000 caracteres")
    @Column(nullable = false, length = 1000)
    private String descripcionTecnica;

    @NonNull
    private LocalDateTime fechaCreacion; //Lo define el sistema

    //Relacion con obras: Tecnica pude tener varias obras
    @OneToMany(mappedBy = "tecnica", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("tecnica")
    private List <Obra> obra;

}
