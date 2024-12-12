package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.casadelacultura.casadelacultura.entity.ObrasFonograficas;

public interface ObrasFonograficasRepositorio extends CrudRepository<ObrasFonograficas, Long> {
    boolean existsByCodigoIgnoreCase(String codigo);
    boolean existsByCodigoIgnoreCaseAndIdObrasFonograficasNot(String codigo, Long idObrasFonograficas);
}
