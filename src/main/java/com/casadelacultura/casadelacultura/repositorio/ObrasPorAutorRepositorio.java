package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.casadelacultura.casadelacultura.entity.ObrasPorAutor;

public interface ObrasPorAutorRepositorio extends JpaRepository<ObrasPorAutor, Long> {
    boolean existsByAutor_IdAutorAndObra_IdObra(Long idAutores, Long idObra);

    boolean existsByAutor_IdAutorAndObra_IdObraAndIdObrasPorAutorNot(Long idAutores, Long idObra, Long idObrasPorAutor);
}