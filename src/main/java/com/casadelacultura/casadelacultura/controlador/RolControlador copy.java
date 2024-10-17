package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Rol;
import com.casadelacultura.casadelacultura.repositorio.RolRepositorio;

import java.util.Optional;

// Controlador para manejar las operaciones CRUD de Rol
@RestController
@RequestMapping("/api/roles")
public class RolControlador {

    @Autowired
    private RolRepositorio rolRepositorio;

    // Obtener todos los roles
    @GetMapping
    public ResponseEntity<Iterable<Rol>> list() {
        return ResponseEntity.ok(rolRepositorio.findAll());
    }

    // Obtener un rol por ID
    @GetMapping("{idRol}")
    public ResponseEntity<Rol> get(@PathVariable Integer idRol) {
        Optional<Rol> rol = rolRepositorio.findById(idRol);
        return rol.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo rol
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Rol create(@RequestBody Rol rol) {
        return rolRepositorio.save(rol);
    }

    // Actualizar un rol existente
    @PutMapping("{idRol}")
    public ResponseEntity<Rol> update(@PathVariable Integer idRol, @RequestBody Rol formulario) {
        Optional<Rol> optionalRol = rolRepositorio.findById(idRol);
        if (optionalRol.isPresent()) {
            Rol rolFromDB = optionalRol.get();
            rolFromDB.setNombreRol(formulario.getNombreRol());
            rolFromDB.setActivo(formulario.getActivo());
            return ResponseEntity.ok(rolRepositorio.save(rolFromDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Rol no encontrado
    }

    // Eliminar un rol
    @DeleteMapping("{idRol}")
    public ResponseEntity<Void> delete(@PathVariable Integer idRol) {
        Optional<Rol> optionalRol = rolRepositorio.findById(idRol);
        if (optionalRol.isPresent()) {
            rolRepositorio.delete(optionalRol.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Eliminación exitosa
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Rol no encontrado
    }
}
