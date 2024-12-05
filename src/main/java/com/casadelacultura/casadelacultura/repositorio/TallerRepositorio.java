package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.casadelacultura.casadelacultura.entity.Taller;

public interface TallerRepositorio extends CrudRepository<Taller, Long> {
    boolean existsByTituloTallerIgnoreCaseAndClaveIgnoreCase(String tituloTaller, String clave);
    boolean existsByTituloTallerIgnoreCaseAndClaveIgnoreCaseAndIdTallerNot(String tituloTaller, String clave, Long idTaller);
}
