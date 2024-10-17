package com.casadelacultura.casadelacultura.controlador;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.AudioLibro;
import com.casadelacultura.casadelacultura.servicio.AudioLibroServicio;

@RestController
@RequestMapping("/api/audiolibro")
public class AudioLibroControlador {

    @Autowired
    private AudioLibroServicio audioLibroServicio;

    // Obtener todos los audiolibros
    @GetMapping
    public ResponseEntity<Iterable<AudioLibro>> list() {
        return ResponseEntity.ok(audioLibroServicio.findAll());
    }

    // Obtener un audiolibro por su ID
    @GetMapping("{idAudioLibro}")
    public ResponseEntity<AudioLibro> get(@PathVariable Integer idAudioLibro) {
        Optional<AudioLibro> audioLibro = audioLibroServicio.findById(idAudioLibro);
        return audioLibro.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo audiolibro
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AudioLibro create(@RequestBody AudioLibro audioLibro) {
        return audioLibroServicio.create(audioLibro);
    }

    // Actualizar un audiolibro existente
    @PutMapping("{idAudioLibro}")
    public ResponseEntity<AudioLibro> update(@PathVariable Integer idAudioLibro, @RequestBody AudioLibro audioLibro) {
        Optional<AudioLibro> optionalAudioLibro = audioLibroServicio.findById(idAudioLibro);
        if (optionalAudioLibro.isPresent()) {
            audioLibro.setIdAudioLibro(idAudioLibro); // Establece el ID correcto
            return ResponseEntity.ok(audioLibroServicio.update(audioLibro));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Audiolibro no encontrado
    }

    // Eliminar un audiolibro por su ID
    @DeleteMapping("{idAudioLibro}")
    public ResponseEntity<Void> delete(@PathVariable Integer idAudioLibro) {
        Optional<AudioLibro> optionalAudioLibro = audioLibroServicio.findById(idAudioLibro);
        if (optionalAudioLibro.isPresent()) {
            audioLibroServicio.delete(idAudioLibro);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Eliminación exitosa
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Audiolibro no encontrado
    }
}
