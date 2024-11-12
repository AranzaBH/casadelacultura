package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Actividades;
import com.casadelacultura.casadelacultura.repositorio.ActividadesRepositorio;

import java.util.Optional;

@Service
public class ActividadesServicio {

    @Autowired
    private ActividadesRepositorio actividadesRepositorio;

    // Obtener todas las actividades
    public Iterable<Actividades> listarActividades() {
        return actividadesRepositorio.findAll();
    }

    // Obtener una actividad por ID
    public Optional<Actividades> obtenerActividadPorId(Long idActividad) {
        return actividadesRepositorio.findById(idActividad);
    }

    // Crear una nueva actividad
    public Actividades crearActividad(Actividades actividad) {
        return actividadesRepositorio.save(actividad);
    }

    // Actualizar una actividad existente
    public Optional<Actividades> actualizarActividad(Long idActividad, Actividades formulario) {
        return actividadesRepositorio.findById(idActividad).map(actividadExistente -> {
            actividadExistente.setVideo(formulario.getVideo());
            actividadExistente.setCuestionario(formulario.getCuestionario());
            actividadExistente.setTaller(formulario.getTaller());
            actividadExistente.setModulo(formulario.getModulo());
            actividadExistente.setAvance(formulario.getAvance());
            actividadExistente.setEstatus(formulario.isEstatus());
            return actividadesRepositorio.save(actividadExistente);
        });
    }

    // Eliminar una actividad por ID
    public boolean eliminarActividad(Long idActividad) {
        Optional<Actividades> actividad = actividadesRepositorio.findById(idActividad);
        if (actividad.isPresent()) {
            actividadesRepositorio.delete(actividad.get());
            return true;
        }
        return false;
    }
}
