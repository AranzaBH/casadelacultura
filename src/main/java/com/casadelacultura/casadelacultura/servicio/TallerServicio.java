package com.casadelacultura.casadelacultura.servicio;


import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Taller;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.TallerRepositorio;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@Service
public class TallerServicio {
    private final TallerRepositorio tallerRepositorio;

    // Obtener todos los talleres
    public Iterable<Taller> obtenerTodosTalleres() {
        return tallerRepositorio.findAll();
    }

    // Obtener un taller por su ID
    public Taller obtenerTallerPorId(Long idTaller) {
        return tallerRepositorio.findById(idTaller).orElseThrow(
                () -> new GlobalExceptionNoEncontrada("No se encontro el Taller con el ID: " + idTaller));
    }

    // Crear un nuevo taller
    public Taller crearTaller(Taller taller) {
        taller.setFechaCreacion(LocalDateTime.now());
        return tallerRepositorio.save(taller);
    }

    // Actualizar un taller existente
    public Taller actualizarTaller(Long idTaller, Taller formulario) {
        Taller tallerFromDB = obtenerTallerPorId(idTaller);
        tallerFromDB.setTituloTaller(formulario.getTituloTaller());
        tallerFromDB.setClave(formulario.getClave());
        tallerFromDB.setDescripcion(formulario.getDescripcion());
        tallerFromDB.setFechaInico(formulario.getFechaInico());
        tallerFromDB.setFechaFinal(formulario.getFechaFinal());
        tallerFromDB.setTipoTaller(formulario.getTipoTaller());
        tallerFromDB.setImagenPath(formulario.getImagenPath());
        tallerFromDB.setEstaActivo(formulario.isEstaActivo());
        return tallerRepositorio.save(tallerFromDB);
        
    }

    // Eliminar un taller
    public void eliminarTaller(Long idTaller) {
        Taller tallerFromDB = obtenerTallerPorId(idTaller);
        tallerRepositorio.delete(tallerFromDB);
    }
}
