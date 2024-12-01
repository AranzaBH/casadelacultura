package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Reactivo;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.CuestionarioRepositorio;
import com.casadelacultura.casadelacultura.repositorio.ReactivoRepositorio;

import lombok.*;

@AllArgsConstructor
@Service
public class ReactivoServicio {
    private final ReactivoRepositorio reactivoRepositorio;
    private final CuestionarioRepositorio cuestionarioRepositorio;

    // Obtener todos los reactivos
    public Iterable<Reactivo> listarReactivos() {
        return reactivoRepositorio.findAll();
    }

    // Obtener un reactivo por ID
    public Reactivo obtenerReactivoPorId(Long idReactivo) {
        return reactivoRepositorio.findById(idReactivo).orElseThrow(() -> new GlobalExceptionNoEncontrada("No se encontro el reactivo con el ID: " + idReactivo));
    }

    // Crear un nuevo reactivo
    public Reactivo crearReactivo(Reactivo reactivo) {
        if (reactivo.getCuestionario() == null ||
        !cuestionarioRepositorio.existsById(reactivo.getCuestionario().getIdCuestionario())
        ) {
            throw new GlobalExceptionNoEncontrada("Cuestionario con ID " +reactivo.getCuestionario().getIdCuestionario() + " no enonctrado.");
            
        }
        return reactivoRepositorio.save(reactivo);
    }

    // Actualizar un reactivo existente
    public Reactivo actualizarReactivo(Long idReactivo, Reactivo formulario) {
        Reactivo reactivoFromDB = obtenerReactivoPorId(idReactivo);
        if (formulario.getCuestionario() == null 
        || !cuestionarioRepositorio.existsById(formulario.getCuestionario().getIdCuestionario())) {
            throw new GlobalExceptionNoEncontrada("Cuestionario con ID:  " + formulario.getCuestionario().getIdCuestionario() + " no encontrado.");
        }
        reactivoFromDB.setPregunta(formulario.getPregunta());
        reactivoFromDB.setRespuestaCorrecta(formulario.getRespuestaCorrecta());
        reactivoFromDB.setRespuesta1(formulario.getRespuesta1());
        reactivoFromDB.setRespuesta2(formulario.getRespuesta2());
        reactivoFromDB.setRespuesta3(formulario.getRespuesta3());
        reactivoFromDB.setRespuesta4(formulario.getRespuesta4());
        reactivoFromDB.setRespuesta5(formulario.getRespuesta5());
        reactivoFromDB.setCuestionario(formulario.getCuestionario());
        return reactivoRepositorio.save(reactivoFromDB);

        
        
        
    }

    // Eliminar un reactivo por ID
    public void eliminarReactivo(Long idReactivo) {
        Reactivo reactivoFromDB = obtenerReactivoPorId(idReactivo);
        reactivoRepositorio.delete(reactivoFromDB);
    }

}
