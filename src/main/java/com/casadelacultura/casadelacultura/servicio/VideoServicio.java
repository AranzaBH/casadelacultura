package com.casadelacultura.casadelacultura.servicio;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.casadelacultura.casadelacultura.entity.Auditoria;
import com.casadelacultura.casadelacultura.entity.Video;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.AuditoriaRepositorio;
import com.casadelacultura.casadelacultura.repositorio.VideoRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class VideoServicio {
    private final VideoRepositorio videoRepositorio;
    private final AuditoriaRepositorio auditoriaRepositorio;

    // Obtener todos los videos
    public Iterable<Video> listarVideos() {
        return videoRepositorio.findAll();
    }

    // Obtener un video por ID
    public Video obtenerVideoPorId(Long idVideo) {
        return videoRepositorio.findById(idVideo)
                .orElseThrow(() -> new GlobalExceptionNoEncontrada("No se encontro el reactivo con el ID: " + idVideo));
    }

    // Crear un nuevo video
    public Video crearVideo(Video video) {
        // Valida si ya existe un video
        if (videoRepositorio.existsByTituloIgnoreCaseAndUrlVideo(video.getTitulo(), video.getUrlVideo())) {
            throw new GlobalExceptionNoEncontrada("Ya existe el video con el nombre: "
                    + video.getTitulo() + " Con URL: " + video.getUrlVideo());

        }
        Video nuevoVideo = videoRepositorio.save(video);
        // Registra una uditoria
        registrarAuditoria("Video", nuevoVideo.getIdVideo(), "CREAR", null, null, nuevoVideo.getIdVideo().toString(),
                "Tabla");
        return nuevoVideo;
    }

    // Actualizar un video existente
    public Video actualizarVideo(Long idVideo, Video formulario) {
        Video videoFronmDB = obtenerVideoPorId(idVideo);

        // Valida si existe un cuestionario con los mismos datos
        if (videoRepositorio.existsByTituloIgnoreCaseAndUrlVideoAndIdVideoNot(formulario.getTitulo(),
                formulario.getUrlVideo(), idVideo)) {
            throw new GlobalExceptionNoEncontrada("Ya existe un video con el nombre: " +
                    formulario.getTitulo() + " con URL " + formulario.getIdVideo());
        }

        // Compara valores anteriores y nuevos
        String valorAnteriorTitulo = videoFronmDB.getTitulo();
        String valorNuevoTitulo = formulario.getTitulo();

        String valorAnteriorUrlVideo = videoFronmDB.getUrlVideo();
        String valorNuevoUrlVideo = formulario.getUrlVideo();

        Video videoActualizado = videoRepositorio.save(videoFronmDB);

        if (!valorAnteriorTitulo.equals(valorNuevoTitulo)) {
            registrarAuditoria(
                    "Video",
                    idVideo,
                    "ACTUALIZAR",
                    "titulo",
                    valorAnteriorTitulo,
                    valorNuevoTitulo,
                    "titulo");
        }

        if (!valorAnteriorUrlVideo.equals(valorNuevoUrlVideo)) {
            registrarAuditoria(
                    "Video",
                    idVideo,
                    "ACTUALIZAR",
                    "urlVideo",
                    valorAnteriorUrlVideo,
                    valorNuevoUrlVideo,
                    "urlVideo");
        }

        videoFronmDB.setTitulo(formulario.getTitulo());
        videoFronmDB.setUrlVideo(formulario.getUrlVideo());
        return videoActualizado;
    }

    // Eliminar un video por ID
    public void eliminarVideo(Long idVideo) {
        Video videoFromDB = obtenerVideoPorId(idVideo);
        registrarAuditoria("Video", videoFromDB.getIdVideo(), "ELIMINAR", null, videoFromDB.getIdVideo().toString(),
                null, "Tabla");
        videoRepositorio.delete(videoFromDB);
    }

    private void registrarAuditoria(String entidad, Long idEntidad, String accion, String campo, String valorAnterior,
            String valorNuevo, String nombreColumna) {
        Auditoria auditoria = new Auditoria(entidad, idEntidad, accion, LocalDateTime.now(), valorAnterior, valorNuevo,
                nombreColumna);
        auditoriaRepositorio.save(auditoria);
    }
}
