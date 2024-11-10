package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Video;
import com.casadelacultura.casadelacultura.repositorio.VideoRepositorio;

import java.util.Optional;

@Service
public class VideoServicio {

    @Autowired
    private VideoRepositorio videoRepositorio;

    // Obtener todos los videos
    public Iterable<Video> listarVideos() {
        return videoRepositorio.findAll();
    }

    // Obtener un video por ID
    public Optional<Video> obtenerVideoPorId(Long idVideo) {
        return videoRepositorio.findById(idVideo);
    }

    // Crear un nuevo video
    public Video crearVideo(Video video) {
        return videoRepositorio.save(video);
    }

    // Actualizar un video existente
    public Optional<Video> actualizarVideo(Long idVideo, Video formulario) {
        return videoRepositorio.findById(idVideo).map(videoExistente -> {
            videoExistente.setTitulo(formulario.getTitulo());
            videoExistente.setUrlVideo(formulario.getUrlVideo());
            return videoRepositorio.save(videoExistente);
        });
    }

    // Eliminar un video por ID
    public boolean eliminarVideo(Long idVideo) {
        Optional<Video> video = videoRepositorio.findById(idVideo);
        if (video.isPresent()) {
            videoRepositorio.delete(video.get());
            return true;
        }
        return false;
    }
}
