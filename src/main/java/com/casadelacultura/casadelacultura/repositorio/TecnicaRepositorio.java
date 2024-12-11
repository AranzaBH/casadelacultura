package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.casadelacultura.casadelacultura.entity.Tecnica;

public interface TecnicaRepositorio extends CrudRepository<Tecnica, Long> {
    boolean existsByNombreTecnicaIgnoreCase(String nombreTecnica);
    boolean existsByNombreTecnicaIgnoreCaseAndIdTecnicaNot(String nombreTecnica, Long idTecnica);
}
