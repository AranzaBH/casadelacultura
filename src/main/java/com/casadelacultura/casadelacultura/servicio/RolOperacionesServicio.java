package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.RolOperaciones;
import com.casadelacultura.casadelacultura.repositorio.RolOperacionesRepositorio;

import java.util.Optional;

@Service
public class RolOperacionesServicio {

    @Autowired
    private RolOperacionesRepositorio rolOperacionesRepositorio;

    // Obtener todas las relaciones de rol y operaciones
    public Iterable<RolOperaciones> listarRolOperaciones() {
        return rolOperacionesRepositorio.findAll();
    }

    // Obtener una relación de rol y operación por ID
    public Optional<RolOperaciones> obtenerRolOperacionPorId(Integer idRolOperaciones) {
        return rolOperacionesRepositorio.findById(idRolOperaciones);
    }

    // Crear una nueva relación entre rol y operación
    public RolOperaciones crearRolOperacion(RolOperaciones rolOperaciones) {
        return rolOperacionesRepositorio.save(rolOperaciones);
    }

    // Actualizar una relación existente entre rol y operación
    public Optional<RolOperaciones> actualizarRolOperacion(Integer idRolOperaciones, RolOperaciones formulario) {
        return rolOperacionesRepositorio.findById(idRolOperaciones).map(rolOperacionesExistente -> {
            rolOperacionesExistente.setNombreOperacion(formulario.getNombreOperacion());
            rolOperacionesExistente.setOperaciones(formulario.getOperaciones());
            rolOperacionesExistente.setRol(formulario.getRol());
            return rolOperacionesRepositorio.save(rolOperacionesExistente);
        });
    }

    // Eliminar una relación entre rol y operación
    public boolean eliminarRolOperacion(Integer idRolOperaciones) {
        Optional<RolOperaciones> rolOperacion = rolOperacionesRepositorio.findById(idRolOperaciones);
        if (rolOperacion.isPresent()) {
            rolOperacionesRepositorio.delete(rolOperacion.get());
            return true;
        }
        return false;
    }
}
