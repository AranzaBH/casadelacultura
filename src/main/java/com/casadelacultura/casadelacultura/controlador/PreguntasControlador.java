package com.casadelacultura.casadelacultura.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.casadelacultura.casadelacultura.entity.Preguntas;
import com.casadelacultura.casadelacultura.servicio.PreguntasServicio;

import lombok.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/pregunta")
@CrossOrigin("*")
public class PreguntasControlador {
    private final PreguntasServicio preguntasServicio;

    // Obtener todas las preguntas
    @GetMapping
    public Iterable<Preguntas> obtenerTodasLasPreguntas() {
        return preguntasServicio.listarPreguntas();
    }

    // Obtener una pregunta por su ID
    @GetMapping("{idPregunta}")
    public Preguntas obtenerPreguntaPorId(@PathVariable Long idPregunta) {
        return preguntasServicio.obtenerPreguntaPorId(idPregunta);
    }

    // Crear una nueva pregunta
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Preguntas crearPregunta(@RequestBody Preguntas pregunta) {
        return preguntasServicio.crearPregunta(pregunta);
    }

    // Actualizar una pregunta existente
    @PutMapping("{idPregunta}")
    public Preguntas actualizarPregunta(@PathVariable Long idPregunta, @RequestBody Preguntas formulario) {
        return preguntasServicio.actualizarPregunta(idPregunta, formulario);
    }

    // Eliminar una pregunta por su ID
    @DeleteMapping("{idPregunta}")
    public void eliminarPregunta(@PathVariable Long idPregunta) {
        preguntasServicio.eliminarPregunta(idPregunta);
    }
}
