package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Operaciones;
import com.casadelacultura.casadelacultura.repositorio.OperacionesRepositorio;

import java.util.Optional;

@Service
public class OperacionesServicio {

    @Autowired
    private OperacionesRepositorio operacionesRepositorio;

    // Obtener todas las operaciones
    public Iterable<Operaciones> listarOperaciones() {
        return operacionesRepositorio.findAll();
    }

    // Obtener una operación específica por ID
    public Optional<Operaciones> obtenerOperacionPorId(Integer idOperaciones) {
        return operacionesRepositorio.findById(idOperaciones);
    }

    // Crear una nueva operación
    public Operaciones crearOperacion(Operaciones operaciones) {
        return operacionesRepositorio.save(operaciones);
    }

    // Actualizar una operación existente
    public Optional<Operaciones> actualizarOperacion(Integer idOperaciones, Operaciones formulario) {
        return operacionesRepositorio.findById(idOperaciones).map(operacionesExistente -> {
            operacionesExistente.setNombreOperacion(formulario.getNombreOperacion());
            operacionesExistente.setDescripcion(formulario.getDescripcion());
            operacionesExistente.setModulo(formulario.getModulo());
            return operacionesRepositorio.save(operacionesExistente);
        });
    }

    // Eliminar una operación
    public boolean eliminarOperacion(Integer idOperaciones) {
        Optional<Operaciones> operacion = operacionesRepositorio.findById(idOperaciones);
        if (operacion.isPresent()) {
            operacionesRepositorio.delete(operacion.get());
            return true;
        }
        return false;
    }
}
