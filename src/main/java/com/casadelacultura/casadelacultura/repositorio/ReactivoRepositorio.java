package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.casadelacultura.casadelacultura.entity.Reactivo;

public interface ReactivoRepositorio extends CrudRepository<Reactivo, Long> {
    //Verifica si la pregunta ya existe en el cuestionario 
    boolean existsByPreguntaAndRespuestaCorrectaAndCuestionario_IdCuestionario(String pregunta,String respuestaCorrecta,Long idCuestionario);

    //Verifica si el reactivo con los mismo dados ya existe 
    boolean existsByPreguntaAndRespuestaCorrectaAndCuestionario_IdCuestionarioAndIdReactivoNot(String pregunta,String respuestaCorrecta,Long idCuestionario, Long idReactivo);
}
