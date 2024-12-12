package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;

import com.casadelacultura.casadelacultura.entity.Actividades;
import com.casadelacultura.casadelacultura.entity.Evaluaciones;
import com.casadelacultura.casadelacultura.entity.Inscripciones;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.EvaluacionesRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EvaluacionesServicio {
    private final EvaluacionesRepositorio evaluacionesRepositorio;
    private final ActividadesServicio actividadesServicio;
    private final InscripcionesServicio inscripcionesServicio;

    //Obtener todas las evaluaciones 
    public Iterable<Evaluaciones> listarEvaluaciones(){
        return evaluacionesRepositorio.findAll();
    }

    //Obtener actividad por ID
    public Evaluaciones obtenerEvaluacionesPorID(Long idEvaluaciones){
        return evaluacionesRepositorio.findById(idEvaluaciones).orElseThrow(
                () -> new GlobalExceptionNoEncontrada("No se encontro la Evaluacion con el ID: " + idEvaluaciones));
    }

    //Crear evaluacion
    public Evaluaciones crearEvaluaciones(Evaluaciones evaluaciones){
        //Valida existencia de actividad
        Actividades actividades = actividadesServicio.obtenerActividadPorId(evaluaciones.getActividades().getIdActividades());
        evaluaciones.setActividades(actividades);

        //Valida existencia de insclipciones
        Inscripciones inscripciones = inscripcionesServicio.obtenerInscripcionPorId(evaluaciones.getInscripciones().getIdInscripciones());
        evaluaciones.setInscripciones(inscripciones);
        return evaluacionesRepositorio.save(evaluaciones);
    }

    public Evaluaciones actualizarEvaluaciones(Long idEvaluaciones, Evaluaciones formulario){
        Evaluaciones evaluacionesFromDB = obtenerEvaluacionesPorID(idEvaluaciones);
        //Valida existencia de actividad
        Actividades actividades = actividadesServicio.obtenerActividadPorId(formulario.getActividades().getIdActividades());
        formulario.setActividades(actividades);

        //Valida existencia de insclipciones
        Inscripciones inscripciones = inscripcionesServicio.obtenerInscripcionPorId(formulario.getInscripciones().getIdInscripciones());
        formulario.setInscripciones(inscripciones);

        evaluacionesFromDB.setInscripciones(formulario.getInscripciones());
        evaluacionesFromDB.setActividades(formulario.getActividades());
        evaluacionesFromDB.setCalificacion(formulario.getCalificacion());
        return evaluacionesRepositorio.save(evaluacionesFromDB);
    }

    //Eliminar 
    public void eliminarEvalucion(Long idEvaluaciones){
        Evaluaciones evaluacionesFromDB = obtenerEvaluacionesPorID(idEvaluaciones);
        evaluacionesRepositorio.delete(evaluacionesFromDB);
    }

}
