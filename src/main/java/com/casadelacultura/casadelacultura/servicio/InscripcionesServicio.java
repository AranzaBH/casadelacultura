package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Inscripciones;
import com.casadelacultura.casadelacultura.repositorio.InscripcionesRepositorio;

import java.util.Optional;

@Service
public class InscripcionesServicio {

    @Autowired
    private InscripcionesRepositorio inscripcionesRepositorio;

    // Obtener todas las inscripciones
    public Iterable<Inscripciones> listarInscripciones() {
        return inscripcionesRepositorio.findAll();
    }

    // Obtener una inscripción por ID
    public Optional<Inscripciones> obtenerInscripcionPorId(Long idInscripcion) {
        return inscripcionesRepositorio.findById(idInscripcion);
    }

    // Crear una nueva inscripción
    public Inscripciones crearInscripcion(Inscripciones inscripcion) {
        return inscripcionesRepositorio.save(inscripcion);
    }

    // Actualizar una inscripción existente
    public Optional<Inscripciones> actualizarInscripcion(Long idInscripcion, Inscripciones formulario) {
        return inscripcionesRepositorio.findById(idInscripcion).map(inscripcionExistente -> {
            inscripcionExistente.setUsuario(formulario.getUsuario());
            inscripcionExistente.setFechaInscripcion(formulario.getFechaInscripcion());
            inscripcionExistente.setTaller(formulario.getTaller());
            inscripcionExistente.setAvanceGeneral(formulario.getAvanceGeneral());
            return inscripcionesRepositorio.save(inscripcionExistente);
        });
    }

    // Eliminar una inscripción por ID
    public boolean eliminarInscripcion(Long idInscripcion) {
        Optional<Inscripciones> inscripcion = inscripcionesRepositorio.findById(idInscripcion);
        if (inscripcion.isPresent()) {
            inscripcionesRepositorio.delete(inscripcion.get());
            return true;
        }
        return false;
    }
}
