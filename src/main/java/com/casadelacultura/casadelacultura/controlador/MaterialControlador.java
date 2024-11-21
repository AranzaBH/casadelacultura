package com.casadelacultura.casadelacultura.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Material;
import com.casadelacultura.casadelacultura.servicio.MaterialServicio;

import lombok.AllArgsConstructor;

// Controlador para manejar las operaciones CRUD de Material
@AllArgsConstructor
@RestController
@RequestMapping("/api/material")
@CrossOrigin("*")
public class MaterialControlador {
    private final MaterialServicio materialServicio;

    // Obtener todos los materiales
    @GetMapping
    public Iterable<Material> list() {
        return materialServicio.listarMateriales();
    }

    // Obtener un material por ID
    @GetMapping("{idMaterial}")
    public Material gerObtenerMaterialId(@PathVariable Long idMaterial) {
        return materialServicio.obtenerMaterialPorId(idMaterial);
    }

    // Crear un nuevo material
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Material create(@RequestBody Material material) {
        return materialServicio.crearMaterial(material);
    }

    // Actualizar un material existente
    @PutMapping("{idMaterial}")
    public Material update(@PathVariable Long idMaterial, @RequestBody Material formulario) {
        return materialServicio.actualizarMaterial(idMaterial, formulario);
    }

    // Eliminar un material
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idMaterial}")
    public void delete(@PathVariable Long idMaterial) {
        materialServicio.eliminarMaterial(idMaterial);
    }
}
