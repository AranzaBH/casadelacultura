package com.casadelacultura.casadelacultura.servicio;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Cuestionario;
import com.casadelacultura.casadelacultura.entity.Preguntas;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.PreguntasRepositorio;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PreguntasServicio {
    private final PreguntasRepositorio preguntasRepositorio;
    private final CuestionarioServicio cuestionarioServicio;

    //Ob
    public List<Preguntas> listarPreguntas(){
        return preguntasRepositorio.findAll();
    }

    public Preguntas obtenerPreguntaPorId(Long idPregunta) {
        return preguntasRepositorio.findById(idPregunta)
                .orElseThrow(() -> new GlobalExceptionNoEncontrada("No se encontro la pregunta con ID: " + idPregunta));
    }

    //Guarda una pregunta
    public Preguntas crearPregunta(Preguntas preguntas){
        preguntas.setFechaCreacion(LocalDateTime.now());
        if (preguntasRepositorio.existsByPreguntaIgnoreCaseAndCuestionarioIdCuestionario(preguntas.getPregunta(), preguntas.getCuestionario().getIdCuestionario())) {
            throw new GlobalExceptionNoEncontrada("Ya existe la pregunta en ese cuestionario");
        }
        //Valida si ya existe la obra con los mismos datos 
        Cuestionario cuestionario = cuestionarioServicio.obtenerCuestionarioPorId(preguntas.getCuestionario().getIdCuestionario());
        preguntas.setCuestionario(cuestionario);
        return preguntasRepositorio.save(preguntas);
    }

    //Actualiza pregunta
    public Preguntas actualizarPregunta(Long idPregunta, Preguntas formulario){
        Preguntas preguntasFrom =  obtenerPreguntaPorId(idPregunta);
        //Valida si ya existe una pregunta en el cuestionario 
        if (preguntasRepositorio.existsByPreguntaIgnoreCaseAndCuestionarioIdCuestionarioAndIdPreguntaNot(formulario.getPregunta(), formulario.getCuestionario().getIdCuestionario(), idPregunta)) {
            throw new GlobalExceptionNoEncontrada("Ya exsite la pregunta "+ formulario.getPregunta()+" en el cuestionario "+formulario.getCuestionario().getIdCuestionario());
        }
        //Valida si existe cuestionaro 
        Cuestionario cuestionario = cuestionarioServicio.obtenerCuestionarioPorId(formulario.getCuestionario().getIdCuestionario());
        formulario.setCuestionario(cuestionario);
        //Actualiza datos
        preguntasFrom.setPregunta(formulario.getPregunta());
        preguntasFrom.setCuestionario(formulario.getCuestionario());
        return preguntasRepositorio.save(preguntasFrom);
    }

    //Elimina Cuestionario 
    public void eliminarPregunta(Long idPregunta){
        Preguntas preguntasFrom =  obtenerPreguntaPorId(idPregunta);
        preguntasRepositorio.delete(preguntasFrom);

    }
}
