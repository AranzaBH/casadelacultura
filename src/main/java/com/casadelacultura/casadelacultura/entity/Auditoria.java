package com.casadelacultura.casadelacultura.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String nombreColumna;
    @NonNull
    private String entidad; // Nombre de la entidad (por ejemplo, Cuestionario)
    @NonNull
    private Long idEntidad; // ID de la entidad afectada
    @NonNull
    private String accion; // Acciones como "CREAR", "ACTUALIZAR", "ELIMINAR"
    @NonNull
    private LocalDateTime fechaHora; // Fecha y hora de la acción

    private String valorAnterior; // Valor anterior del campo actualizado
    private String valorNuevo; // Valor nuevo del campo actualizado

    // Constructor personalizado para poder pasar todos los parámetros
    public Auditoria(String entidad, Long idEntidad, String accion, LocalDateTime fechaHora,
            String valorAnterior, String valorNuevo, String nombreColumna) {
        this.entidad = entidad;
        this.idEntidad = idEntidad;
        this.accion = accion;
        this.fechaHora = fechaHora;
        this.valorAnterior = valorAnterior;
        this.valorNuevo = valorNuevo;
        this.nombreColumna = nombreColumna;
    }
}
