package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.TipoLibro;
import com.casadelacultura.casadelacultura.repositorio.TipoLibroRepositorio;

import java.util.Optional;

@Service
public class TipoLibroServicio {

    @Autowired
    private TipoLibroRepositorio tipoLibroRepositorio;

    // Obtener todos los tipos de libro
    public Iterable<TipoLibro> obtenerTodosTiposDeLibro() {
        return tipoLibroRepositorio.findAll();
    }

    // Obtener un tipo de libro por su ID
    public Optional<TipoLibro> obtenerTipoLibroPorId(Long idTipoLibro) {
        return tipoLibroRepositorio.findById(idTipoLibro);
    }

    // Crear un nuevo tipo de libro
    public TipoLibro crearTipoLibro(TipoLibro tipoLibro) {
        return tipoLibroRepositorio.save(tipoLibro);
    }

    // Actualizar un tipo de libro existente
    public Optional<TipoLibro> actualizarTipoLibro(Long idTipoLibro, TipoLibro formulario) {
        return tipoLibroRepositorio.findById(idTipoLibro).map(tipoLibroExistente -> {
            tipoLibroExistente.setNombreTipoLibro(formulario.getNombreTipoLibro());
            tipoLibroExistente.setDescripcion(formulario.getDescripcion());
            return tipoLibroRepositorio.save(tipoLibroExistente);
        });
    }

    // Eliminar un tipo de libro
    public boolean eliminarTipoLibro(Long idTipoLibro) {
        Optional<TipoLibro> tipoLibro = tipoLibroRepositorio.findById(idTipoLibro);
        if (tipoLibro.isPresent()) {
            tipoLibroRepositorio.delete(tipoLibro.get());
            return true;
        }
        return false;
    }
}
