package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casadelacultura.casadelacultura.entity.Preguntas;

public interface PreguntasRepositorio extends JpaRepository<Preguntas, Long> {
    //boolean existeByPreguntaAndCuestionario_IdCuestionario(String pregunta, Long idCuestionario);
    boolean existsByPreguntaIgnoreCaseAndCuestionarioIdCuestionario(String pregunta, Long idCuestionario);
    boolean existsByPreguntaIgnoreCaseAndCuestionarioIdCuestionarioAndIdPreguntaNot(String pregunta, Long idCuestionario, Long idPregunta);
}

