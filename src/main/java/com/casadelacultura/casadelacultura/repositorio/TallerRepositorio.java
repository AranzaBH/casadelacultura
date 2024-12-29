package com.casadelacultura.casadelacultura.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.casadelacultura.casadelacultura.entity.Taller;

public interface TallerRepositorio extends JpaRepository<Taller, Long> {
    boolean existsByTituloTallerIgnoreCaseAndClaveIgnoreCase(String tituloTaller, String clave);
    boolean existsByTituloTallerIgnoreCaseAndClaveIgnoreCaseAndIdTallerNot(String tituloTaller, String clave, Long idTaller);
    // Método de consulta derivada en Spring Data JPA para buscar talleres
    // que contengan un título específico sin importar las mayúsculas o minúsculas.
    List<Taller> findByTituloTallerContainingIgnoreCase(@Param("titulo") String titulo);
}
