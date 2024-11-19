package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Actividades;
import com.casadelacultura.casadelacultura.repositorio.ActividadesRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ActividadesServicio {
    private final ActividadesRepositorio actividadesRepositorio;

    // Obtener todas las actividades
    public Iterable<Actividades> listarActividades() {
        return actividadesRepositorio.findAllActividadesWithTaller();
        //return actividadesRepositorio.findAll();
    }

    // Obtener una actividad por ID
    public Actividades obtenerActividadPorId(Long idActividad) {
        return actividadesRepositorio.findById(idActividad).orElse(null);
    }

    // Crear una nueva actividad
    public Actividades crearActividad(Actividades actividad) {
        return actividadesRepositorio.save(actividad);
    }

    // Actualizar una actividad existente
    public Actividades actualizarActividad(Long idActividad, Actividades formulario) {
        Actividades actividadesFromDB = obtenerActividadPorId(idActividad);
        actividadesFromDB.setVideo(formulario.getVideo());
        actividadesFromDB.setCuestionario(formulario.getCuestionario());
        actividadesFromDB.setTaller(formulario.getTaller());
        actividadesFromDB.setModulo(formulario.getModulo());
        actividadesFromDB.setAvance(formulario.getAvance());
        actividadesFromDB.setEstatus(formulario.isEstatus());
        return actividadesRepositorio.save(actividadesFromDB);
        
    }

    // Eliminar una actividad por ID
    public void eliminarActividad(Long idActividad) {
        Actividades actividadesFromDB = obtenerActividadPorId(idActividad);
        actividadesRepositorio.delete(actividadesFromDB);
    }
}
