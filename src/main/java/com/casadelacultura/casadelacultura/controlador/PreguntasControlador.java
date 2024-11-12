package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.casadelacultura.casadelacultura.entity.Preguntas;
import com.casadelacultura.casadelacultura.servicio.PreguntasServicio;

import java.util.Optional;

@RestController
@RequestMapping("/preguntas")
public class PreguntasControlador {

    @Autowired
    private PreguntasServicio preguntasServicio;

    // Obtener todas las preguntas
    @GetMapping
    public ResponseEntity<Iterable<Preguntas>> obtenerTodasLasPreguntas() {
        Iterable<Preguntas> preguntas = preguntasServicio.listarPreguntas();
        return new ResponseEntity<>(preguntas, HttpStatus.OK);
    }

    // Obtener una pregunta por su ID
    @GetMapping("/{idPregunta}")
    public ResponseEntity<Preguntas> obtenerPreguntaPorId(@PathVariable Long idPregunta) {
        Optional<Preguntas> pregunta = preguntasServicio.obtenerPreguntaPorId(idPregunta);
        return pregunta.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva pregunta
    @PostMapping
    public ResponseEntity<Preguntas> crearPregunta(@RequestBody Preguntas pregunta) {
        Preguntas nuevaPregunta = preguntasServicio.crearPregunta(pregunta);
        return new ResponseEntity<>(nuevaPregunta, HttpStatus.CREATED);
    }

    // Actualizar una pregunta existente
    @PutMapping("/{idPregunta}")
    public ResponseEntity<Preguntas> actualizarPregunta(@PathVariable Long idPregunta, @RequestBody Preguntas formulario) {
        Optional<Preguntas> preguntaActualizada = preguntasServicio.actualizarPregunta(idPregunta, formulario);
        return preguntaActualizada.map(ResponseEntity::ok)
                                  .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar una pregunta por su ID
    @DeleteMapping("/{idPregunta}")
    public ResponseEntity<Void> eliminarPregunta(@PathVariable Long idPregunta) {
        boolean eliminado = preguntasServicio.eliminarPregunta(idPregunta);
        return eliminado ? ResponseEntity.noContent().build()
                         : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
