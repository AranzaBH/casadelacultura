package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Preguntas;
import com.casadelacultura.casadelacultura.repositorio.PreguntasRepositorio;

import java.util.Optional;

@Service
public class PreguntasServicio {

    @Autowired
    private PreguntasRepositorio preguntasRepositorio;

    // Obtener todas las preguntas
    public Iterable<Preguntas> listarPreguntas() {
        return preguntasRepositorio.findAll();
    }

    // Obtener una pregunta por ID
    public Optional<Preguntas> obtenerPreguntaPorId(Long idPregunta) {
        return preguntasRepositorio.findById(idPregunta);
    }

    // Crear una nueva pregunta
    public Preguntas crearPregunta(Preguntas pregunta) {
        return preguntasRepositorio.save(pregunta);
    }

    // Actualizar una pregunta existente
    public Optional<Preguntas> actualizarPregunta(Long idPregunta, Preguntas formulario) {
        return preguntasRepositorio.findById(idPregunta).map(preguntaExistente -> {
            preguntaExistente.setCuestionario(formulario.getCuestionario());
            preguntaExistente.setReactivo(formulario.getReactivo());
            return preguntasRepositorio.save(preguntaExistente);
        });
    }

    // Eliminar una pregunta por ID
    public boolean eliminarPregunta(Long idPregunta) {
        Optional<Preguntas> pregunta = preguntasRepositorio.findById(idPregunta);
        if (pregunta.isPresent()) {
            preguntasRepositorio.delete(pregunta.get());
            return true;
        }
        return false;
    }
}
