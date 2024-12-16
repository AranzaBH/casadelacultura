package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Inscripciones;
import com.casadelacultura.casadelacultura.entity.Taller;
import com.casadelacultura.casadelacultura.entity.Usuario;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.InscripcionesRepositorio;
import com.casadelacultura.casadelacultura.repositorio.UsuarioRepository;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class InscripcionesServicio {
    private final InscripcionesRepositorio inscripcionesRepositorio;
    private final TallerServicio tallerServicio;
    private final UsuarioRepository usuarioRepository;

    // Obtener todas las inscripciones
    public Iterable<Inscripciones> listarInscripciones() {
        return inscripcionesRepositorio.findAll();
    }

    // Obtener una inscripción por ID
    public Inscripciones obtenerInscripcionPorId(Long idInscripcion) {
        return inscripcionesRepositorio.findById(idInscripcion).orElseThrow(
                () -> new GlobalExceptionNoEncontrada("No se encontro la inscripcion con el ID: " + idInscripcion));
    }

    // Crear una nueva inscripción
    public Inscripciones crearInscripcion(Inscripciones inscripcion) {
        // Valida duplicado de datos
        if (inscripcionesRepositorio.existsByUsuario_IdAndTaller_IdTaller(inscripcion.getUsuario().getId(),
                inscripcion.getTaller().getIdTaller())) {
            throw new GlobalExceptionNoEncontrada(
                    "Ya existe una relación para el usuario ID: " + inscripcion.getUsuario().getId() +
                            " y taller con ID: " + inscripcion.getTaller().getIdTaller());

        }

        // Valida la existencia del taller
        Taller taller = tallerServicio.obtenerTallerPorId(inscripcion.getTaller().getIdTaller());
        inscripcion.setTaller(taller);

        // Valida la existencia del usuario
        Usuario usuario = usuarioRepository.findById(inscripcion.getUsuario().getId())
                .orElseThrow(() -> new GlobalExceptionNoEncontrada(
                        "No se encontro el usuario con el ID: " + inscripcion.getUsuario().getId()));
        inscripcion.setUsuario(usuario);

        // Valida la existencia del usuario por su ID
        inscripcion.setFechaInscripcion(LocalDateTime.now());
        return inscripcionesRepositorio.save(inscripcion);
    }

    // Actualizar una inscripción existente
    public Inscripciones actualizarInscripcion(Long idInscripcion, Inscripciones formulario) {
        Inscripciones inscripcionesFromDB = obtenerInscripcionPorId(idInscripcion);

        // Valida duplicado de datos
        if (inscripcionesRepositorio.existsByUsuario_IdAndTaller_IdTallerAndIdInscripcionesNot(formulario.getUsuario().getId(), formulario.getTaller().getIdTaller(), idInscripcion)) {
            throw new GlobalExceptionNoEncontrada(
                    "Ya existe una relación para el usuario ID: " + formulario.getUsuario().getId() +
                            " y taller con ID: " + formulario.getTaller().getIdTaller());

        }

        // Valida la existencia del taller
        Taller taller = tallerServicio.obtenerTallerPorId(formulario.getTaller().getIdTaller());
        formulario.setTaller(taller);

        // Valida la existencia del usuario
        Usuario usuario = usuarioRepository.findById(formulario.getUsuario().getId())
                .orElseThrow(() -> new GlobalExceptionNoEncontrada(
                        "No se encontro el usuario con el ID: " + formulario.getUsuario().getId()));
        formulario.setUsuario(usuario);

        inscripcionesFromDB.setUsuario(formulario.getUsuario());
        inscripcionesFromDB.setTaller(formulario.getTaller());
        inscripcionesFromDB.setAvanceGeneral(formulario.getAvanceGeneral());
        return inscripcionesRepositorio.save(inscripcionesFromDB);
    }

    // Eliminar una inscripción por ID
    public void eliminarInscripcion(Long idInscripcion) {
        Inscripciones inscripcionesFromDB = obtenerInscripcionPorId(idInscripcion);
        inscripcionesRepositorio.delete(inscripcionesFromDB);
    }

    public List<Inscripciones> obtenerInscripcionesPorUsuario(Long idUsuario) {
        return (List<Inscripciones>) inscripcionesRepositorio.findByUsuarioId(idUsuario);
    }

    // busque la inscripción según el idUsuario y el idTaller.
    public List<Inscripciones> obtenerInscripcionPorUsuarioYTaller(Long idUsuario, Long idTaller) {
         // Valida la existencia del taller
    if (tallerServicio.obtenerTallerPorId(idTaller) == null) {
        throw new GlobalExceptionNoEncontrada("No se encontró el taller con el ID: " + idTaller);
    }
    // Valida la existencia del usuario
    if (!usuarioRepository.existsById(idUsuario)) {
        throw new GlobalExceptionNoEncontrada("No se encontró el usuario con el ID: " + idUsuario);
    }
        return inscripcionesRepositorio.findByUsuarioIdAndTallerId(idUsuario, idTaller);
    }
}