package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.casadelacultura.casadelacultura.entity.LibrosImagenes;

public interface LibrosImagenesRepositorio extends CrudRepository<LibrosImagenes, Long>{
    //
    boolean existsByLibro_IdLibroAndImagenes_IdImagen(Long idLibro, Long idImagenes);

    // Verificar si la relación ya existe, excluyendo un ID específico
    boolean existsByLibro_IdLibroAndImagenes_IdImagenAndIdLibrosImagenesNot(Long idLibro, Long idImagenes, Long idLibrosImagenes);

}
