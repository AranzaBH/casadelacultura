package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Preguntas;
import com.casadelacultura.casadelacultura.repositorio.PreguntasRepositorio;

import lombok.*;

@AllArgsConstructor
@Service
public class PreguntasServicio {
    private final PreguntasRepositorio preguntasRepositorio;

    // Obtener todas las preguntas
    public Iterable<Preguntas> listarPreguntas() {
        return preguntasRepositorio.findAll();
    }

    // Obtener una pregunta por ID
    public Preguntas obtenerPreguntaPorId(Long idPregunta) {
        return preguntasRepositorio.findById(idPregunta).orElse(null);
    }

    // Crear una nueva pregunta
    public Preguntas crearPregunta(Preguntas pregunta) {
        return preguntasRepositorio.save(pregunta);
    }

    // Actualizar una pregunta existente
    public Preguntas actualizarPregunta(Long idPregunta, Preguntas formulario) {
        Preguntas preguntasFromDB = obtenerPreguntaPorId(idPregunta);
        preguntasFromDB.setCuestionario(formulario.getCuestionario());
        preguntasFromDB.setReactivo(formulario.getReactivo());
        return preguntasRepositorio.save(preguntasFromDB);
        
    }

    // Eliminar una pregunta por ID
    public void eliminarPregunta(Long idPregunta) {
        Preguntas preguntasFromDB = obtenerPreguntaPorId(idPregunta);
        preguntasRepositorio.delete(preguntasFromDB);
    }
}
