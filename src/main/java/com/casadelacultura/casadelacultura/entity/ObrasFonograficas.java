package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class ObrasFonograficas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idObrasFonograficas;

    @NonNull
    private String tituloObraFonografica;

    @NonNull
    private Integer duracion;

    @NonNull
    private LocalDateTime fechaLanzamiento;

    @NonNull
    private String descripcion;

    @NonNull
    private String idUrlImagenPortada;

    @NonNull
    private Boolean activo;
}
