package com.casadelacultura.casadelacultura.controlador;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.AudioLibro;
import com.casadelacultura.casadelacultura.servicio.AudioLibroServicio;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/audiolibro")
@CrossOrigin("*")
public class AudioLibroControlador {
    private final AudioLibroServicio audioLibroServicio;

    // Obtener todos los audiolibros
    @GetMapping
    public Iterable<AudioLibro> listar() {
        return audioLibroServicio.listarAudioLibros();
    }

    // Obtener un audiolibro por su ID
    @GetMapping("{idAudioLibro}")
    public AudioLibro get(@PathVariable Long idAudioLibro) {
        return audioLibroServicio.audioLibrioPorId(idAudioLibro);
    }

    // Crear un nuevo audiolibro
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AudioLibro create(@Valid @RequestBody AudioLibro audioLibro) {
        return audioLibroServicio.crearAudioLibro(audioLibro);
    }

    // Actualizar un audiolibro existente
    @PutMapping("{idAudioLibro}")
    public AudioLibro update(@PathVariable Long idAudioLibro, @RequestBody @Valid AudioLibro audioLibro) {
        return audioLibroServicio.actualizarAudioLibro(idAudioLibro, audioLibro);
    }

    // Eliminar un audiolibro por su ID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idAudioLibro}")
    public void delete(@PathVariable Long idAudioLibro) {
        audioLibroServicio.eliminarAudioLibro(idAudioLibro);
    }
}
