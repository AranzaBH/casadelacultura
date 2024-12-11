package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.casadelacultura.casadelacultura.entity.TipoTaller;

public interface TipoTallerRepositorio extends CrudRepository<TipoTaller, Long> {
    boolean existsByNombreTipoTallerIgnoreCase(String nombreTipoTaller);
    boolean existsByNombreTipoTallerIgnoreCaseAndIdTipoTallerNot(String nombreTipoTaller, Long idTipoTaller);
}
