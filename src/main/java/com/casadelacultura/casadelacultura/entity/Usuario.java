package com.casadelacultura.casadelacultura.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUsuario;

    @NonNull
    private String nombre;

    @NonNull
    private String apellidoMaterno;

    @NonNull
    private String apellidoPaterno;

    @NonNull
    private String correoElectronico;

    @NonNull
    private String password;

    @NonNull
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "idRol", nullable = false)
    private Rol rol;

}


