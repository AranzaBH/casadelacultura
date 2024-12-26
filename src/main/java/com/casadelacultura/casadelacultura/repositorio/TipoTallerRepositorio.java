package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casadelacultura.casadelacultura.entity.TipoTaller;

public interface TipoTallerRepositorio extends JpaRepository<TipoTaller, Long> {
    boolean existsByNombreTipoTallerIgnoreCase(String nombreTipoTaller);
    boolean existsByNombreTipoTallerIgnoreCaseAndIdTipoTallerNot(String nombreTipoTaller, Long idTipoTaller);
}
