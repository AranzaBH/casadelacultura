package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.casadelacultura.casadelacultura.entity.TipoTaller;

public interface TipoTallerRepositorio extends CrudRepository<TipoTaller, Long> {
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN TRUE ELSE FALSE END FROM TipoTaller t WHERE LOWER(t.nombreTipoTaller) = LOWER(:nombreTipoTaller)")
    boolean existsByNombreTipoTallerIgnoreCase(@Param("nombreTipoTaller") String nombreTipoTaller);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN TRUE ELSE FALSE END FROM TipoTaller t WHERE LOWER(t.nombreTipoTaller) = LOWER(:nombreTipoTaller) AND t.idTipoTaller != :idTipoTaller")
    boolean existsByNombreTipoTallerIgnoreCaseAndIdTipoTallerNot(@Param("nombreTipoTaller") String nombreTipoTaller,
            @Param("idTipoTaller") Long idTipoTaller);

    //boolean existsByNombreTipoTallerAndIdTipoTallerNot(String nombreTipoTaller, Long idTipoTaller);

}
