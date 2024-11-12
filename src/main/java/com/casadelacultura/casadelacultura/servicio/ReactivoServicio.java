package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Reactivo;
import com.casadelacultura.casadelacultura.repositorio.ReactivoRepositorio;

import java.util.Optional;

@Service
public class ReactivoServicio {

    @Autowired
    private ReactivoRepositorio reactivoRepositorio;

    // Obtener todos los reactivos
    public Iterable<Reactivo> listarReactivos() {
        return reactivoRepositorio.findAll();
    }

    // Obtener un reactivo por ID
    public Optional<Reactivo> obtenerReactivoPorId(Long idReactivo) {
        return reactivoRepositorio.findById(idReactivo);
    }

    // Crear un nuevo reactivo
    public Reactivo crearReactivo(Reactivo reactivo) {
        return reactivoRepositorio.save(reactivo);
    }

    // Actualizar un reactivo existente
    public Optional<Reactivo> actualizarReactivo(Long idReactivo, Reactivo formulario) {
        return reactivoRepositorio.findById(idReactivo).map(reactivoExistente -> {
            reactivoExistente.setPregunta(formulario.getPregunta());
            reactivoExistente.setRespuestaCorrecta(formulario.getRespuestaCorrecta());
            reactivoExistente.setRespuesta1(formulario.getRespuesta1());
            reactivoExistente.setRespuesta2(formulario.getRespuesta2());
            reactivoExistente.setRespuesta3(formulario.getRespuesta3());
            reactivoExistente.setRespuesta4(formulario.getRespuesta4());
            reactivoExistente.setRespuesta5(formulario.getRespuesta5());
            return reactivoRepositorio.save(reactivoExistente);
        });
    }

    // Eliminar un reactivo por ID
    public boolean eliminarReactivo(Long idReactivo) {
        Optional<Reactivo> reactivo = reactivoRepositorio.findById(idReactivo);
        if (reactivo.isPresent()) {
            reactivoRepositorio.delete(reactivo.get());
            return true;
        }
        return false;
    }
}
