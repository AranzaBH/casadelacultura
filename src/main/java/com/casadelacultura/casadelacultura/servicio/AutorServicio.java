package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Autor;
import com.casadelacultura.casadelacultura.repositorio.AutorRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AutorServicio {
    private final AutorRepositorio autorRepositorio;

    // Obtener todos los autores
    public Iterable<Autor> listarAutores() {
        return autorRepositorio.findAll();
    }

    // Obtener un autor por ID
    public Autor obtenerAutorPorId(Long idAutor) {
        return autorRepositorio.findById(idAutor).orElse(null);
    }

    // Crear un nuevo autor
    public Autor crearAutor(Autor autor) {
        return autorRepositorio.save(autor);
    }

    // Actualizar un autor existente
    public Autor actualizarAutor(Long idAutor, Autor formulario) {
        Autor autorFromDB = obtenerAutorPorId(idAutor);
        autorFromDB.setNombreAutor(formulario.getNombreAutor());
        autorFromDB.setApellidoPaterno(formulario.getApellidoPaterno());
        autorFromDB.setApellidoMaterno(formulario.getApellidoMaterno());
        autorFromDB.setFechaNacimiento(formulario.getFechaNacimiento());
        autorFromDB.setFechaFallecimiento(formulario.getFechaFallecimiento());
        return autorRepositorio.save(autorFromDB);
        
    }

    // Eliminar un autor por ID
    public void eliminarAutor(Long idAutor) {
        Autor autorFromDB = obtenerAutorPorId(idAutor);
        autorRepositorio.delete(autorFromDB);
    }
}
