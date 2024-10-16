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

/**
 * Entidad que representa un tipo de taller dentro del sistema.
 * 
 * @Entity Indica que esta clase es una entidad JPA mapeada a una tabla de la base de datos.
 * @Getter y @Setter Generan automáticamente los métodos getter y setter para los atributos de la clase.
 * @RequiredArgsConstructor Genera un constructor para los atributos marcados con @NonNull.
 * @NoArgsConstructor Genera un constructor sin argumentos, necesario para algunas operaciones de JPA.
 */
@Getter
@Setter
@Entity 
@RequiredArgsConstructor 
@NoArgsConstructor 
public class TipoTaller {
    /**
     * Identificador único del tipo de taller. Es la clave primaria de la tabla.
     * 
     * @Id Marca el atributo como clave primaria.
     * @GeneratedValue(strategy = GenerationType.AUTO) Indica que el valor de esta columna será generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Autogenera el valor del ID.
    private Integer idTipoTaller;

    /**
     * Nombre del tipo de taller.
     * 
     * @NonNull Indica que este campo no puede ser nulo y que debe estar presente en el constructor generado.
     */
    @NonNull
    private String nombreTipoTaller;

    /**
     * Descripción del tipo de taller.
     * 
     * @NonNull Indica que este campo no puede ser nulo y que debe estar presente en el constructor generado.
     */
    @NonNull
    private String descripcion;

    /**
     * Fecha de creación del tipo de taller.
     * 
     * @NonNull Indica que este campo no puede ser nulo y que debe estar presente en el constructor generado.
     */
    @NonNull
    private LocalDateTime fechaCreacion;
}