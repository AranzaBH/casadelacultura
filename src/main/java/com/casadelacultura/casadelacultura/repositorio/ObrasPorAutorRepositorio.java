package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.casadelacultura.casadelacultura.entity.ObrasPorAutor;

public interface ObrasPorAutorRepositorio extends CrudRepository<ObrasPorAutor, Long> {
    boolean existsByAutor_IdAutorAndObra_IdObra(Long idAutores, Long idObra);

    boolean existsByAutor_IdAutorAndObra_IdObraAndIdObrasPorAutorNot(Long idAutores, Long idObra, Long idObrasPorAutor);
}
