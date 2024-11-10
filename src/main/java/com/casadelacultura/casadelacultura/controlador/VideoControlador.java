package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.casadelacultura.casadelacultura.entity.Video;
import com.casadelacultura.casadelacultura.servicio.VideoServicio;

import java.util.Optional;

@RestController
@RequestMapping("/video")
public class VideoControlador {

    @Autowired
    private VideoServicio videoServicio;

    // Obtener todos los videos
    @GetMapping
    public ResponseEntity<Iterable<Video>> obtenerTodosLosVideos() {
        Iterable<Video> videos = videoServicio.listarVideos();
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    // Obtener un video por su ID
    @GetMapping("/{idVideo}")
    public ResponseEntity<Video> obtenerVideoPorId(@PathVariable Long idVideo) {
        Optional<Video> video = videoServicio.obtenerVideoPorId(idVideo);
        return video.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo video
    @PostMapping
    public ResponseEntity<Video> crearVideo(@RequestBody Video video) {
        Video nuevoVideo = videoServicio.crearVideo(video);
        return new ResponseEntity<>(nuevoVideo, HttpStatus.CREATED);
    }

    // Actualizar un video existente
    @PutMapping("/{idVideo}")
    public ResponseEntity<Video> actualizarVideo(@PathVariable Long idVideo, @RequestBody Video formulario) {
        Optional<Video> videoActualizado = videoServicio.actualizarVideo(idVideo, formulario);
        return videoActualizado.map(ResponseEntity::ok)
                               .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar un video por su ID
    @DeleteMapping("/{idVideo}")
    public ResponseEntity<Void> eliminarVideo(@PathVariable Long idVideo) {
        boolean eliminado = videoServicio.eliminarVideo(idVideo);
        return eliminado ? ResponseEntity.noContent().build()
                         : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
