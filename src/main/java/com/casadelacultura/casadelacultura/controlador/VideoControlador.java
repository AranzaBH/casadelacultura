package com.casadelacultura.casadelacultura.controlador;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.casadelacultura.casadelacultura.entity.Video;
import com.casadelacultura.casadelacultura.servicio.VideoServicio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/video")
@CrossOrigin("*")
public class VideoControlador {
    private final VideoServicio videoServicio;

    // Obtener todos los videos
    @GetMapping
    public Iterable<Video> obtenerTodosLosVideos() {
        return videoServicio.listarVideos();
    }

    // Obtener un video por su ID
    @GetMapping("{idVideo}")
    public Video obtenerVideoPorId(@PathVariable Long idVideo) {
        return videoServicio.obtenerVideoPorId(idVideo);
    }

    // Crear un nuevo video
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Video crearVideo(@Valid @RequestBody Video video) {
        return videoServicio.crearVideo(video);
    }

    // Actualizar un video existente
    @PutMapping("{idVideo}")
    public Video actualizarVideo(@PathVariable Long idVideo, @RequestBody @Valid Video formulario) {
        return videoServicio.actualizarVideo(idVideo, formulario);
    }

    // Eliminar un video por su ID
    @DeleteMapping("{idVideo}")
    public void eliminarVideo(@PathVariable Long idVideo) {
        videoServicio.eliminarVideo(idVideo);
    }
}
