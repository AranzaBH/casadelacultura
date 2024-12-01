package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Cuestionario;
import com.casadelacultura.casadelacultura.repositorio.CuestionarioRepositorio;
import lombok.*;

@AllArgsConstructor
@Service
public class CuestionarioServicio {
    private final CuestionarioRepositorio cuestionarioRepositorio;

    // Obtener todos los cuestionarios
    public Iterable<Cuestionario> listarCuestionarios() {
        return cuestionarioRepositorio.findAll();
    }

    // Obtener un cuestionario por ID
    public Cuestionario obtenerCuestionarioPorId(Long idCuestionario) {
        return cuestionarioRepositorio.findById(idCuestionario).orElse(null);
    }

    /**
     * Crea un nuevo Cuestionario y lo guarda en la base de datos.
     * 
     * @param cuestionario Objeto Cuestionario a ser creado.
     * @return Cuestionario El objeto guardado en la base de datos.
     */
    public Cuestionario crearCuestionario(Cuestionario cuestionario) {
        return cuestionarioRepositorio.save(cuestionario);
    }

    /**
     * Actualiza un Cuestionario existente en la base de datos.
     * 
     * @param idCuestionario ID del cuestionario que se desea actualizar.
     * @param formulario Datos actualizados del Cuestionario.
     * @return Cuestionario El objeto actualizado.
     */
    public Cuestionario actualizarCuestionario(Long idCuestionario, Cuestionario formulario) {
        Cuestionario cuestionarioFromDB = obtenerCuestionarioPorId(idCuestionario);
        cuestionarioFromDB.setCalificacion(formulario.getCalificacion());
        cuestionarioFromDB.setNombreCuestionario(formulario.getNombreCuestionario());
        cuestionarioFromDB.setInstrucciones(formulario.getInstrucciones());
        return cuestionarioRepositorio.save(cuestionarioFromDB);

    }

    // Eliminar un cuestionario por ID
    public void eliminarCuestionario(Long idCuestionario) {
        Cuestionario cuestionarioFromDB = obtenerCuestionarioPorId(idCuestionario);
        cuestionarioRepositorio.delete(cuestionarioFromDB);
    }
}
