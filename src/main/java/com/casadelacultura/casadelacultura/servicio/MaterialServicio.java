package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Material;
import com.casadelacultura.casadelacultura.repositorio.MaterialRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MaterialServicio {
    private final MaterialRepositorio materialRepositorio;

    // Obtener todos los materiales
    public Iterable<Material> listarMateriales() {
        return materialRepositorio.findAll();
    }

    // Obtener un material por ID
    public Material obtenerMaterialPorId(Long idMaterial) {
        return materialRepositorio.findById(idMaterial).orElse(null);
    }

    // Crear un nuevo material
    public Material crearMaterial(Material material) {
        return materialRepositorio.save(material);
    }

    // Actualizar un material existente
    public Material actualizarMaterial(Long idMaterial, Material formulario) {
        Material materialFromDB = obtenerMaterialPorId(idMaterial);
        materialFromDB.setNombreMaterial(formulario.getNombreMaterial());
        materialFromDB.setDescripcionMaterial(formulario.getDescripcionMaterial());
        return materialRepositorio.save(materialFromDB);
        
    }

    // Eliminar un material
    public void eliminarMaterial(Long idMaterial) {
        Material materialFromDB = obtenerMaterialPorId(idMaterial);
        materialRepositorio.delete(materialFromDB);
    }
}
