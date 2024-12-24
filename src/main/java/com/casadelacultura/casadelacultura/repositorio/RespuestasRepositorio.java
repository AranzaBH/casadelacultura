package com.casadelacultura.casadelacultura.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import com.casadelacultura.casadelacultura.entity.Respuestas;
public interface RespuestasRepositorio extends JpaRepository <Respuestas, Long>{
    //Busca una respuesta por el texto (ignorando mayúsculas/minúsculas) y el ID de la pregunta.
    boolean existsByRespuestaIgnoreCaseAndPreguntasIdPregunta(String respuesta, Long idPregunta);

    //Verifica si ya existe una respuesta con el mismo texto (ignorando mayúsculas/minúsculas)
    //para una pregunta específica, excluyendo el ID de la respuesta actual.
    boolean existsByRespuestaIgnoreCaseAndPreguntasIdPreguntaAndIdRespuestaNot(String respuesta, Long idPregunta, Long idRespuesta);
}

