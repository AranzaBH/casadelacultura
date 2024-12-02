package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.casadelacultura.casadelacultura.entity.Cuestionario;

public interface CuestionarioRepositorio extends CrudRepository<Cuestionario, Long> {
    //Valida si ya existe un cuestionario con los mismos datos
    boolean existsByNombreCuestionarioAndInstrucciones(
        String nombreCuestionario,String instrucciones);
    boolean existsByNombreCuestionarioAndInstruccionesAndIdCuestionario(String nombreCuestionario,String instrucciones, Long idCuestionario);

    
}
