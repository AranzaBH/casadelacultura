package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibro;

    @NonNull
    private String asin;

    @NonNull
    private String tituloLibro;

    @NonNull
    private String nombreEditorial;

    @NonNull
    private String lugarProsedenciaLibro;

    @NonNull
    private Integer cantidadPaginas;

    @NonNull
    private String descripcion;

    @NonNull
    private String idUrlImagenPortada;

    @NonNull
    private Boolean estaActivo;

    @NonNull
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "idCategoriaLibro", nullable = false)
    private CategoriaLibro categoriaLibro;

    @ManyToOne
    @JoinColumn(name = "idTipoLibro", nullable = false)
    private TipoLibro tipoLibro;






    





}
