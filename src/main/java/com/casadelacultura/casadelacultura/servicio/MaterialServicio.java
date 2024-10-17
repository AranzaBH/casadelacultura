package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Material;
import com.casadelacultura.casadelacultura.repositorio.MaterialRepositorio;

import java.util.Optional;

@Service
public class MaterialServicio {

    @Autowired
    private final MaterialRepositorio materialRepositorio;

    public MaterialServicio(MaterialRepositorio materialRepositorio) {
        this.materialRepositorio = materialRepositorio;
    }

    // Obtener todos los materiales
    public Iterable<Material> listarMateriales() {
        return materialRepositorio.findAll();
    }

    // Obtener un material por ID
    public Optional<Material> obtenerMaterialPorId(Integer idMaterial) {
        return materialRepositorio.findById(idMaterial);
    }

    // Crear un nuevo material
    public Material crearMaterial(Material material) {
        return materialRepositorio.save(material);
    }

    // Actualizar un material existente
    public Optional<Material> actualizarMaterial(Integer idMaterial, Material formulario) {
        return materialRepositorio.findById(idMaterial).map(materialExistente -> {
            materialExistente.setNombreMaterial(formulario.getNombreMaterial());
            materialExistente.setDescripcionMaterial(formulario.getDescripcionMaterial());
            return materialRepositorio.save(materialExistente);
        });
    }

    // Eliminar un material
    public boolean eliminarMaterial(Integer idMaterial) {
        Optional<Material> material = materialRepositorio.findById(idMaterial);
        if (material.isPresent()) {
            materialRepositorio.delete(material.get());
            return true;
        }
        return false;
    }
}
