package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Material;
import com.casadelacultura.casadelacultura.repositorio.MaterialRepositorio;

import java.util.Optional;

// Controlador para manejar las operaciones CRUD de Material
@RestController
@RequestMapping("/api/material")
public class MaterialControlador {

    @Autowired
    private MaterialRepositorio materialRepositorio;

    // Obtener todos los materiales
    @GetMapping
    public ResponseEntity<Iterable<Material>> list() {
        return ResponseEntity.ok(materialRepositorio.findAll());
    }

    // Obtener un material por ID
    @GetMapping("{idMaterial}")
    public ResponseEntity<Material> get(@PathVariable Long idMaterial) {
        Optional<Material> material = materialRepositorio.findById(idMaterial);
        return material.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo material
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Material create(@RequestBody Material material) {
        return materialRepositorio.save(material);
    }

    // Actualizar un material existente
    @PutMapping("{idMaterial}")
    public ResponseEntity<Material> update(@PathVariable Long idMaterial, @RequestBody Material formulario) {
        Optional<Material> optionalMaterial = materialRepositorio.findById(idMaterial);
        if (optionalMaterial.isPresent()) {
            Material materialFromDB = optionalMaterial.get();
            materialFromDB.setNombreMaterial(formulario.getNombreMaterial());
            materialFromDB.setDescripcionMaterial(formulario.getDescripcionMaterial());
            return ResponseEntity.ok(materialRepositorio.save(materialFromDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Material no encontrado
    }

    // Eliminar un material
    @DeleteMapping("{idMaterial}")
    public ResponseEntity<Void> delete(@PathVariable Long idMaterial) {
        Optional<Material> optionalMaterial = materialRepositorio.findById(idMaterial);
        if (optionalMaterial.isPresent()) {
            materialRepositorio.delete(optionalMaterial.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Eliminación exitosa
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Material no encontrado
    }
}
