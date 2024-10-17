package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.RolOperaciones;
import com.casadelacultura.casadelacultura.repositorio.RolOperacionesRepositorio;

import java.util.Optional;

// Controlador para manejar las operaciones CRUD de RolOperaciones
@RestController
@RequestMapping("/api/rolOperaciones")
public class RolOperacionesControlador {

    @Autowired
    private RolOperacionesRepositorio rolOperacionesRepositorio;

    // Obtener todas las relaciones de rol y operaciones
    @GetMapping
    public ResponseEntity<Iterable<RolOperaciones>> list() {
        return ResponseEntity.ok(rolOperacionesRepositorio.findAll());
    }

    // Obtener una relación de rol y operación por ID
    @GetMapping("{idRolOperaciones}")
    public ResponseEntity<RolOperaciones> get(@PathVariable Integer idRolOperaciones) {
        Optional<RolOperaciones> rolOperaciones = rolOperacionesRepositorio.findById(idRolOperaciones);
        return rolOperaciones.map(ResponseEntity::ok)
                              .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva relación entre rol y operación
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RolOperaciones create(@RequestBody RolOperaciones rolOperaciones) {
        return rolOperacionesRepositorio.save(rolOperaciones);
    }

    // Actualizar una relación existente entre rol y operación
    @PutMapping("{idRolOperaciones}")
    public ResponseEntity<RolOperaciones> update(@PathVariable Integer idRolOperaciones, @RequestBody RolOperaciones formulario) {
        Optional<RolOperaciones> optionalRolOperaciones = rolOperacionesRepositorio.findById(idRolOperaciones);
        if (optionalRolOperaciones.isPresent()) {
            RolOperaciones rolOperacionesFromDB = optionalRolOperaciones.get();
            rolOperacionesFromDB.setNombreOperacion(formulario.getNombreOperacion());
            rolOperacionesFromDB.setOperaciones(formulario.getOperaciones());
            rolOperacionesFromDB.setRol(formulario.getRol());
            return ResponseEntity.ok(rolOperacionesRepositorio.save(rolOperacionesFromDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Relación no encontrada
    }

    // Eliminar una relación entre rol y operación
    @DeleteMapping("{idRolOperaciones}")
    public ResponseEntity<Void> delete(@PathVariable Integer idRolOperaciones) {
        Optional<RolOperaciones> optionalRolOperaciones = rolOperacionesRepositorio.findById(idRolOperaciones);
        if (optionalRolOperaciones.isPresent()) {
            rolOperacionesRepositorio.delete(optionalRolOperaciones.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Eliminación exitosa
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Relación no encontrada
    }
}
