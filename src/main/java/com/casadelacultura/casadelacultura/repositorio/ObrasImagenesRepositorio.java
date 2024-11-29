package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.casadelacultura.casadelacultura.entity.ObrasImagenes;

public interface ObrasImagenesRepositorio extends CrudRepository <ObrasImagenes, Long>{
    boolean existsByObra_IdObraAndImagenes_IdImagen(Long idObra, Long idImagenes);

    // Validar si ya existe la relación obra-imagen, excluyendo un ID específico
    boolean existsByObra_IdObraAndImagenes_IdImagenAndIdObrasImagenesNot(Long idObra, Long idImagen, Long idObrasImagenes);
}
