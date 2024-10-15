package com.casadelacultura.casadelacultura.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.NonNull;




@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor

public class TipoTaller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTipoTaller;

    @NonNull
    private String nombreTipoTaller;

    @NonNull
    private String descripcion;

    @NonNull
    private LocalDateTime fechaCreacion;
}

    

    

    /* 
    public TipoTaller(String nombreTipoTaller, String descripcion, LocalDateTime fechaCreacion) {
        this.nombreTipoTaller = nombreTipoTaller;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
    }
}*/

