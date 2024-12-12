package com.casadelacultura.casadelacultura.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Musica;
import com.casadelacultura.casadelacultura.servicio.MusicaServicio;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/musica")
@CrossOrigin("*")
public class MusicaControlador {
    private final MusicaServicio musicaServicio;

    // Obtener todas las músicas
    @GetMapping
    public Iterable<Musica> list() {
        return musicaServicio.listarMusica();
    }

    // Obtener una música por ID
    @GetMapping("{idMusica}")
    public Musica getMusica(@PathVariable Long idMusica) {
        return musicaServicio.obtenerMusicaPorId(idMusica);
    }

    // Crear una nueva música
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Musica createMusica(@Valid @RequestBody Musica musica) {
        return musicaServicio.crearMusica(musica);
    }

    // Actualizar una música existente
    @PutMapping("{idMusica}")
    public Musica update(@PathVariable Long idMusica, @RequestBody @Valid Musica formulario) {
        return musicaServicio.actualizarMusica(idMusica, formulario);
    }

    // Eliminar una música
    @DeleteMapping("{idMusica}")
    public void delete(@PathVariable Long idMusica) {
        musicaServicio.eliminarMusica(idMusica);
    }  
}
