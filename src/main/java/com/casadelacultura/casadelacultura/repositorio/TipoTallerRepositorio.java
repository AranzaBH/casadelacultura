package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.casadelacultura.casadelacultura.entity.TipoTaller;

public interface TipoTallerRepositorio extends CrudRepository<TipoTaller, Long> {
    // @Query("SELECT CASE WHEN COUNT(t) > 0 THEN TRUE ELSE FALSE END FROM
    // TipoTaller t WHERE LOWER(t.nombreTipoTaller) = LOWER(:nombreTipoTaller)")
    boolean existsByNombreTipoTallerIgnoreCase(String nombreTipoTaller);

    // @Query("SELECT CASE WHEN COUNT(t) > 0 THEN TRUE ELSE FALSE END FROM
    // TipoTaller t WHERE LOWER(t.nombreTipoTaller) = LOWER(:nombreTipoTaller) AND
    // t.idTipoTaller != :idTipoTaller")
    boolean existsByNombreTipoTallerIgnoreCaseAndIdTipoTallerNot(String nombreTipoTaller, Long idTipoTaller);

    // boolean existsByNombreTipoTallerAndIdTipoTallerNot(String nombreTipoTaller,
    // Long idTipoTaller);

}
