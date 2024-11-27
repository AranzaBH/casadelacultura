package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.casadelacultura.casadelacultura.entity.Libro;

public interface LibroRepositorio extends CrudRepository<Libro, Long> {
    // Validar si ya existe un libro con los campos especificados
    // Verificar si existe un libro con los campos especificados
    boolean existsByTituloLibroAndAsinAndNombreEditorialAndEdicionlibroAndCantidadPaginas(
        String tituloLibro, String asin, String nombreEditorial, Integer edicionlibro, Integer cantidadPaginas);

    // Verificar si existe un libro con los campos especificados, excluyendo un ID específico
    boolean existsByTituloLibroAndAsinAndNombreEditorialAndEdicionlibroAndCantidadPaginasAndIdLibroNot(
        String tituloLibro, String asin, String nombreEditorial, Integer edicionlibro, Integer cantidadPaginas, Long idLibro);
}
