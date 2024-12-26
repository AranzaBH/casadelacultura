package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.casadelacultura.casadelacultura.entity.Taller;

public interface TallerRepositorio extends JpaRepository<Taller, Long> {
    boolean existsByTituloTallerIgnoreCaseAndClaveIgnoreCase(String tituloTaller, String clave);
    boolean existsByTituloTallerIgnoreCaseAndClaveIgnoreCaseAndIdTallerNot(String tituloTaller, String clave, Long idTaller);
    //boolean existsByTipoTaller(TipoTaller tipoTaller);
}
