package com.casadelacultura.casadelacultura.servicio;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Preguntas;
import com.casadelacultura.casadelacultura.entity.Respuestas;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.RespuestasRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RespuestasServicio {
    private final RespuestasRepositorio respuestasRepositorio;
    private final PreguntasServicio preguntasServicio;

    public List<Respuestas>listarRespuestas(){
        return respuestasRepositorio.findAll();
    }

    public Respuestas obtenerRespuestaPorId(Long idRespuesta){
        return respuestasRepositorio.findById(idRespuesta)
        .orElseThrow(() -> new GlobalExceptionNoEncontrada(
                        "No se encontro la respuesta con el ID: " + idRespuesta));
    }

    public Respuestas crearRespuestas( Respuestas respuestas){
        respuestas.setFechaCreacion(LocalDateTime.now());
        Preguntas preguntas = preguntasServicio.obtenerPreguntaPorId(respuestas.getPreguntas().getIdPregunta());
        respuestas.setPreguntas(preguntas);
        return respuestasRepositorio.save(respuestas);
    }

    public Respuestas actualizarRespuesta(Long idRespuesta, Respuestas formulario){
        Respuestas respuestasExistente = obtenerRespuestaPorId(idRespuesta);
        //Valida si existe una pregunta
        Preguntas preguntas = preguntasServicio.obtenerPreguntaPorId(formulario.getPreguntas().getIdPregunta());
        formulario.setPreguntas(preguntas);

        respuestasExistente.setPreguntas(formulario.getPreguntas());
        respuestasExistente.setEsCorrecta(formulario.isEsCorrecta());
        respuestasExistente.setPreguntas(formulario.getPreguntas());
        return respuestasRepositorio.save(respuestasExistente);
    }

    public void eliminarRespuesta(Long idRespuesta){
        Respuestas respuestasExistente = obtenerRespuestaPorId(idRespuesta);
        respuestasRepositorio.delete(respuestasExistente);
    }
}