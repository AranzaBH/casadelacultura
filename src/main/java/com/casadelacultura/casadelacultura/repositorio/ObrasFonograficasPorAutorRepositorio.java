package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.casadelacultura.casadelacultura.entity.ObrasFonograficasPorAutor;

public interface ObrasFonograficasPorAutorRepositorio extends CrudRepository<ObrasFonograficasPorAutor, Long> {
    boolean existsByAutor_IdAutorAndObrasFonograficas_IdObrasFonograficas(Long idAutor, Long idObrasFonograficas);
    boolean existsByAutor_IdAutorAndObrasFonograficas_IdObrasFonograficasAndIdObrasFonograficasPorAutorNot(Long idAutor, Long idObrasFonograficas, Long idObrasFonograficasPorAutor);

    
}
