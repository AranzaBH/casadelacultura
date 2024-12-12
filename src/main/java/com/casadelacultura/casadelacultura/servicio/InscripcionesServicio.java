package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Inscripciones;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.InscripcionesRepositorio;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class InscripcionesServicio {

    @Autowired
    private final InscripcionesRepositorio inscripcionesRepositorio;

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
        inscripcion.setFechaInscripcion(LocalDateTime.now());
        return inscripcionesRepositorio.save(inscripcion);
    }

    // Actualizar una inscripción existente
    public Inscripciones actualizarInscripcion(Long idInscripcion, Inscripciones formulario) {
        Inscripciones inscripcionesFromDB = obtenerInscripcionPorId(idInscripcion);
        inscripcionesFromDB.setUsuario(formulario.getUsuario());
        //inscripcionesFromDB.setFechaInscripcion(formulario.getFechaInscripcion());
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

}
