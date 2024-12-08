package com.casadelacultura.casadelacultura.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.casadelacultura.casadelacultura.entity.Reactivo;

public interface ReactivoRepositorio extends CrudRepository<Reactivo, Long> {
    boolean existsByPreguntaIgnoreCaseAndRespuestaCorrectaIgnoreCaseAndCuestionario_IdCuestionario(String pregunta,String respuestaCorrecta, Long idCuestionario);

    boolean existsByPreguntaIgnoreCaseAndRespuestaCorrectaIgnoreCaseAndCuestionario_IdCuestionarioAndIdReactivoNot(String pregunta, String respuestaCorrecta, Long idCuestionario, Long idReactivo);
    // Método para obtener los reactivos por el ID del cuestionario
    List<Reactivo> findByCuestionario_IdCuestionario(Long idCuestionario);
}
