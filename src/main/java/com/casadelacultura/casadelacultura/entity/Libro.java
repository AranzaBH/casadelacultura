package com.casadelacultura.casadelacultura.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idLibro;

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
