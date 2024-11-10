package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.casadelacultura.casadelacultura.entity.Reactivo;
import com.casadelacultura.casadelacultura.servicio.ReactivoServicio;

import java.util.Optional;

@RestController
@RequestMapping("/reactivos")
public class ReactivoControlador {

    @Autowired
    private ReactivoServicio reactivoServicio;

    // Obtener todos los reactivos
    @GetMapping
    public ResponseEntity<Iterable<Reactivo>> obtenerTodosLosReactivos() {
        Iterable<Reactivo> reactivos = reactivoServicio.listarReactivos();
        return new ResponseEntity<>(reactivos, HttpStatus.OK);
    }

    // Obtener un reactivo por su ID
    @GetMapping("/{idReactivo}")
    public ResponseEntity<Reactivo> obtenerReactivoPorId(@PathVariable Long idReactivo) {
        Optional<Reactivo> reactivo = reactivoServicio.obtenerReactivoPorId(idReactivo);
        return reactivo.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo reactivo
    @PostMapping
    public ResponseEntity<Reactivo> crearReactivo(@RequestBody Reactivo reactivo) {
        Reactivo nuevoReactivo = reactivoServicio.crearReactivo(reactivo);
        return new ResponseEntity<>(nuevoReactivo, HttpStatus.CREATED);
    }

    // Actualizar un reactivo existente
    @PutMapping("/{idReactivo}")
    public ResponseEntity<Reactivo> actualizarReactivo(@PathVariable Long idReactivo, @RequestBody Reactivo formulario) {
        Optional<Reactivo> reactivoActualizado = reactivoServicio.actualizarReactivo(idReactivo, formulario);
        return reactivoActualizado.map(ResponseEntity::ok)
                                  .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar un reactivo por su ID
    @DeleteMapping("/{idReactivo}")
    public ResponseEntity<Void> eliminarReactivo(@PathVariable Long idReactivo) {
        boolean eliminado = reactivoServicio.eliminarReactivo(idReactivo);
        return eliminado ? ResponseEntity.noContent().build()
                         : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
