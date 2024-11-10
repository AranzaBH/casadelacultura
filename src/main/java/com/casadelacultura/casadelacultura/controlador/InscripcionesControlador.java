package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.casadelacultura.casadelacultura.entity.Inscripciones;
import com.casadelacultura.casadelacultura.servicio.InscripcionesServicio;

import java.util.Optional;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionesControlador {

    @Autowired
    private InscripcionesServicio inscripcionesServicio;

    // Obtener todas las inscripciones
    @GetMapping
    public ResponseEntity<Iterable<Inscripciones>> obtenerTodasLasInscripciones() {
        Iterable<Inscripciones> inscripciones = inscripcionesServicio.listarInscripciones();
        return new ResponseEntity<>(inscripciones, HttpStatus.OK);
    }

    // Obtener una inscripción por su ID
    @GetMapping("/{idInscripcion}")
    public ResponseEntity<Inscripciones> obtenerInscripcionPorId(@PathVariable Long idInscripcion) {
        Optional<Inscripciones> inscripcion = inscripcionesServicio.obtenerInscripcionPorId(idInscripcion);
        return inscripcion.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva inscripción
    @PostMapping
    public ResponseEntity<Inscripciones> crearInscripcion(@RequestBody Inscripciones inscripcion) {
        Inscripciones nuevaInscripcion = inscripcionesServicio.crearInscripcion(inscripcion);
        return new ResponseEntity<>(nuevaInscripcion, HttpStatus.CREATED);
    }

    // Actualizar una inscripción existente
    @PutMapping("/{idInscripcion}")
    public ResponseEntity<Inscripciones> actualizarInscripcion(@PathVariable Long idInscripcion, @RequestBody Inscripciones formulario) {
        Optional<Inscripciones> inscripcionActualizada = inscripcionesServicio.actualizarInscripcion(idInscripcion, formulario);
        return inscripcionActualizada.map(ResponseEntity::ok)
                                     .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar una inscripción por su ID
    @DeleteMapping("/{idInscripcion}")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable Long idInscripcion) {
        boolean eliminado = inscripcionesServicio.eliminarInscripcion(idInscripcion);
        return eliminado ? ResponseEntity.noContent().build()
                         : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
