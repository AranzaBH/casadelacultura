package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.casadelacultura.casadelacultura.entity.LibrosPorAutor;

public interface LibrosPorAutorRepositorio extends CrudRepository<LibrosPorAutor, Long> {
    // Verifica si ya existe una relacion entre el autor y el libro en la base de
    // datos
    boolean existsByAutor_IdAutorAndLibro_IdLibro(Long idAutor, Long idLibro);

    // Verifica si ya existe una relacion entre el autor y el libro en la base de
    // datos, exclyendo un redistro con un ID especifico
    boolean existsByAutor_IdAutorAndLibro_IdLibroAndIdLibroPorAutor(Long idAutor, Long idLibro, Long idLibroPorAutor);
}
