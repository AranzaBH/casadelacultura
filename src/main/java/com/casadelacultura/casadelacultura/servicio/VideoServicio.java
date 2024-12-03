package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Video;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.VideoRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class VideoServicio {
    private final VideoRepositorio videoRepositorio;

    // Obtener todos los videos
    public Iterable<Video> listarVideos() {
        return videoRepositorio.findAll();
    }

    // Obtener un video por ID
    public Video obtenerVideoPorId(Long idVideo) {
        return videoRepositorio.findById(idVideo).orElseThrow(() -> new GlobalExceptionNoEncontrada("No se encontro el reactivo con el ID: " + idVideo));
    }

    // Crear un nuevo video
    public Video crearVideo(Video video) {
        return videoRepositorio.save(video);
    }

    // Actualizar un video existente
    public Video actualizarVideo(Long idVideo, Video formulario) {
        Video videoFronmDB = obtenerVideoPorId(idVideo);
        videoFronmDB.setTitulo(formulario.getTitulo());
        videoFronmDB.setUrlVideo(formulario.getUrlVideo());
        return videoRepositorio.save(videoFronmDB);
    }

    // Eliminar un video por ID
    public void eliminarVideo(Long idVideo) {
        Video videoFromDB = obtenerVideoPorId(idVideo);
        videoRepositorio.delete(videoFromDB);
    }
}
