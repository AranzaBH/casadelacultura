package com.casadelacultura.casadelacultura.controlador;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Preguntas;
import com.casadelacultura.casadelacultura.servicio.PreguntasServicio;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController // Marca la clase como un controlador REST que gestiona respuestas en formato JSON.
@RequestMapping("/api/pregunta") // Define la ruta base para acceder a este controlador.
@CrossOrigin("*")
public class PreguntasControlador {
    private final PreguntasServicio preguntasServicio;

    @GetMapping
    public List<Preguntas> listarPreguntas() {
        return preguntasServicio.listarPreguntas();
    }

    @GetMapping("{idPregunta}")
    public Preguntas obtenerPreguntaPorId(@PathVariable Long idPregunta){
        return preguntasServicio.obtenerPreguntaPorId(idPregunta);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Preguntas crearPreguntas(@RequestBody Preguntas preguntas) {
        return preguntasServicio.crearPregunta(preguntas);
    }

    @PutMapping("{idPregunta}")
    public Preguntas actualizarPregunta(@PathVariable Long idPregunta, @RequestBody @Valid Preguntas formulario){
        return preguntasServicio.actualizarPregunta(idPregunta, formulario);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) // Indica que, si se elimina correctamente, se devuelve el código de estado 204.
    @DeleteMapping("{idPregunta}")
    public void eliminarPregunta(@PathVariable Long idPregunta){
        preguntasServicio.eliminarPregunta(idPregunta);
    }
}