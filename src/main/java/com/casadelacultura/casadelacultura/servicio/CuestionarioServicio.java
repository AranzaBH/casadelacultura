package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Cuestionario;
import com.casadelacultura.casadelacultura.repositorio.CuestionarioRepositorio;

import java.util.Optional;

@Service
public class CuestionarioServicio {

    @Autowired
    private CuestionarioRepositorio cuestionarioRepositorio;

    // Obtener todos los cuestionarios
    public Iterable<Cuestionario> listarCuestionarios() {
        return cuestionarioRepositorio.findAll();
    }

    // Obtener un cuestionario por ID
    public Optional<Cuestionario> obtenerCuestionarioPorId(Long idCuestionario) {
        return cuestionarioRepositorio.findById(idCuestionario);
    }

    // Crear un nuevo cuestionario
    public Cuestionario crearCuestionario(Cuestionario cuestionario) {
        return cuestionarioRepositorio.save(cuestionario);
    }

    // Actualizar un cuestionario existente
    public Optional<Cuestionario> actualizarCuestionario(Long idCuestionario, Cuestionario formulario) {
        return cuestionarioRepositorio.findById(idCuestionario).map(cuestionarioExistente -> {
            cuestionarioExistente.setCalificacion(formulario.getCalificacion());
            return cuestionarioRepositorio.save(cuestionarioExistente);
        });
    }

    // Eliminar un cuestionario por ID
    public boolean eliminarCuestionario(Long idCuestionario) {
        Optional<Cuestionario> cuestionario = cuestionarioRepositorio.findById(idCuestionario);
        if (cuestionario.isPresent()) {
            cuestionarioRepositorio.delete(cuestionario.get());
            return true;
        }
        return false;
    }
}
