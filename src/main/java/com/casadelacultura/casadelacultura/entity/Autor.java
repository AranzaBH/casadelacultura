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
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAutor;

    @NonNull
    private String nombreAutor;

    @NonNull
    private String apellidoPaterno;

    @NonNull
    private String apellidoMaterno;

    @NonNull
    private LocalDateTime fechaNacimiento;

    @NonNull
    private LocalDateTime fechaFallecimiento;


}
