package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.casadelacultura.casadelacultura.entity.ObrasFonograficasImagenes;

public interface ObrasFonograficasImagenesRepositorio extends CrudRepository <ObrasFonograficasImagenes, Long> {
    boolean existsByObrasFonograficas_IdObrasFonograficasAndImagenes_IdImagen(Long idObrasFonograficas, Long idImagenes);
    boolean existsByObrasFonograficas_IdObrasFonograficasAndImagenes_IdImagenAndIdObrasFonograficasImagenesNot(Long idObrasFonograficas, Long idImagenes, Long idObrasFonograficasImagenes);
}
