package com.casadelacultura.casadelacultura.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
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

    @NonNull
    @Column(nullable = false)
    private String nombreTipoTaller;

    
    @NonNull
    private String descripcion;

    @NonNull
    private LocalDateTime fechaCreacion;
}