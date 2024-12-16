package com.casadelacultura.casadelacultura.controlador;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Inscripciones;
import com.casadelacultura.casadelacultura.servicio.InscripcionesServicio;
import lombok.*;

@AllArgsConstructor
@RestController // Marca la clase como un controlador REST que gestiona respuestas en formato
                // JSON.
@RequestMapping("/api/inscripciones")
@CrossOrigin("*")
public class InscripcionesControlador {
    private final InscripcionesServicio inscripcionesServicio;

    // Obtener todas las inscripciones
    @GetMapping
    public Iterable<Inscripciones> obtenerTodasLasInscripciones() {
        return inscripcionesServicio.listarInscripciones();
    }

    // Obtener una inscripción por su ID
    @GetMapping("{idInscripcion}")
    public Inscripciones obtenerInscripcionPorId(@PathVariable Long idInscripcion) {
        return inscripcionesServicio.obtenerInscripcionPorId(idInscripcion);
    }

    // Crear una nueva inscripción
    @ResponseStatus(HttpStatus.CREATED) // Indica que, si se crea correctamente, se devuelve el código de estado 201.
    @PostMapping
    public Inscripciones crearInscripcion(@RequestBody Inscripciones inscripcion) {
        return inscripcionesServicio.crearInscripcion(inscripcion);
    }

    // Actualizar una inscripción existente
    @PutMapping("{idInscripcion}")
    public Inscripciones actualizarInscripcion(@PathVariable Long idInscripcion,
            @RequestBody Inscripciones formulario) {
        return inscripcionesServicio.actualizarInscripcion(idInscripcion, formulario);
    }

    // Eliminar una inscripción por su ID
    @ResponseStatus(HttpStatus.NO_CONTENT) // Indica que, si se elimina correctamente, se devuelve el código de estado
                                           // 204.
    @DeleteMapping("{idInscripcion}")
    public void eliminarInscripcion(@PathVariable Long idInscripcion) {
        inscripcionesServicio.eliminarInscripcion(idInscripcion);
    }

    // Obtener los talleres inscritos por usuarios
    // http://localhost:8080/api/inscripciones/usuario/2
    @GetMapping("/usuario/{idUsuario}")
    public List<Inscripciones> obtenerInscripcionesPorUsuario(@PathVariable("idUsuario") Long idUsuario) {
        return inscripcionesServicio.obtenerInscripcionesPorUsuario(idUsuario);
    }

    // Obtener inscripción de un usuario a un taller específico
    // http://localhost:8080/api/inscripciones/{idUsuario}/{idTaller}
    @GetMapping("/{idUsuario}/{idTaller}")
    public ResponseEntity<List<Inscripciones>> obtenerInscripcionPorUsuarioYTaller(
            @PathVariable("idUsuario") Long idUsuario,
            @PathVariable("idTaller") Long idTaller) {
        List<Inscripciones> inscripciones = inscripcionesServicio.obtenerInscripcionPorUsuarioYTaller(idUsuario,
                idTaller);
        if (!inscripciones.isEmpty()) {
            return ResponseEntity.ok(inscripciones);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Devuelve un 404 si no está inscrito
        }
    }

    /*
     * @GetMapping("/{idUsuario}/{idTaller}")
     * public ResponseEntity<Inscripciones> obtenerInscripcionPorUsuarioYTaller(
     * 
     * @PathVariable("idUsuario") Long idUsuario,
     * 
     * @PathVariable("idTaller") Long idTaller) {
     * Inscripciones inscripcion =
     * inscripcionesServicio.obtenerInscripcionPorUsuarioYTaller(idUsuario,
     * idTaller);
     * if (inscripcion != null) {
     * return ResponseEntity.ok(inscripcion);
     * } else {
     * return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Devuelve un
     * 404 si no está inscrito
     * }
     * }
     */

}
