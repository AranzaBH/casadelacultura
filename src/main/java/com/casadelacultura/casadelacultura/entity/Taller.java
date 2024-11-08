package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Taller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTaller;

    @NonNull
    private String tituloTaller;

    @NonNull
    private String clave;

    @NonNull
    private String descripcion;

    @NonNull
    private LocalDateTime  fechaInico;

    @NonNull
    private LocalDateTime  fechaFinal;

    @NonNull
    private LocalDateTime  fechaCreacion;

    @OneToOne
    @JoinColumn(name = "idTipoTaller", nullable = false)
    private TipoTaller tipoTaller;
    
    private boolean estaActivo;
}
