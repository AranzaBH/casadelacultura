package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Musica;
import com.casadelacultura.casadelacultura.servicio.MusicaServicio;

import java.util.Optional;

@RestController
@RequestMapping("/api/musica")
public class MusicaControlador {

    @Autowired
    private MusicaServicio musicaServicio;

    // Obtener todas las músicas
    @GetMapping
    public ResponseEntity<Iterable<Musica>> list() {
        return ResponseEntity.ok(musicaServicio.listarMusica());
    }

    // Obtener una música por ID
    @GetMapping("{idMusica}")
    public ResponseEntity<Musica> get(@PathVariable Long idMusica) {
        Optional<Musica> musica = musicaServicio.obtenerMusicaPorId(idMusica);
        return musica.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva música
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Musica create(@RequestBody Musica musica) {
        return musicaServicio.crearMusica(musica);
    }

    // Actualizar una música existente
    @PutMapping("{idMusica}")
    public ResponseEntity<Musica> update(@PathVariable Long idMusica, @RequestBody Musica formulario) {
        Optional<Musica> updatedMusica = musicaServicio.actualizarMusica(idMusica, formulario);
        return updatedMusica.map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar una música
    @DeleteMapping("{idMusica}")
    public ResponseEntity<Void> delete(@PathVariable Long idMusica) {
        boolean deleted = musicaServicio.eliminarMusica(idMusica);
        return deleted ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                       : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
