package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Operaciones;
import com.casadelacultura.casadelacultura.repositorio.OperacionesRepositorio;

import java.util.Optional;

// Controlador para manejar las operaciones CRUD de Operaciones
@RestController
@RequestMapping("/api/operaciones")
public class OperacionesControlador {

    @Autowired
    private OperacionesRepositorio operacionesRepositorio;

    // Obtener todas las operaciones
    @GetMapping
    public ResponseEntity<Iterable<Operaciones>> list() {
        return ResponseEntity.ok(operacionesRepositorio.findAll());
    }

    // Obtener una operación por ID
    @GetMapping("{idOperaciones}")
    public ResponseEntity<Operaciones> get(@PathVariable Integer idOperaciones) {
        Optional<Operaciones> operaciones = operacionesRepositorio.findById(idOperaciones);
        return operaciones.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva operación
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Operaciones create(@RequestBody Operaciones operaciones) {
        return operacionesRepositorio.save(operaciones);
    }

    // Actualizar una operación existente
    @PutMapping("{idOperaciones}")
    public ResponseEntity<Operaciones> update(@PathVariable Integer idOperaciones, @RequestBody Operaciones formulario) {
        Optional<Operaciones> optionalOperaciones = operacionesRepositorio.findById(idOperaciones);
        if (optionalOperaciones.isPresent()) {
            Operaciones operacionesFromDB = optionalOperaciones.get();
            operacionesFromDB.setNombreOperacion(formulario.getNombreOperacion());
            operacionesFromDB.setDescripcion(formulario.getDescripcion());
            operacionesFromDB.setModulo(formulario.getModulo());
            return ResponseEntity.ok(operacionesRepositorio.save(operacionesFromDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Relación no encontrada
    }

    // Eliminar una operación
    @DeleteMapping("{idOperaciones}")
    public ResponseEntity<Void> delete(@PathVariable Integer idOperaciones) {
        Optional<Operaciones> optionalOperaciones = operacionesRepositorio.findById(idOperaciones);
        if (optionalOperaciones.isPresent()) {
            operacionesRepositorio.delete(optionalOperaciones.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Eliminación exitosa
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Operación no encontrada
    }
}
