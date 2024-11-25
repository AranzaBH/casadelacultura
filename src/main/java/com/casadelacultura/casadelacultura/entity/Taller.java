package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Taller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTaller;

    @NonNull
    private String tituloTaller;

    private String descripcion;

    @OneToOne
    @JoinColumn(name = "idTipoTaller", nullable = false)
    private TipoTaller tipoTaller;


    @NonNull
    private LocalDate  fechaInico;

    @NonNull
    private LocalDate  fechaFinal;

    @NonNull
    private String clave;

    @NonNull
    private LocalDateTime  fechaCreacion;
    
    private boolean estaActivo = false;

    private String imagenPath;

    @Transient
    private String urlImagenPortadaTaller;
}
