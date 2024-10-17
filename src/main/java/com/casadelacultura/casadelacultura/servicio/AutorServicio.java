package com.casadelacultura.casadelacultura.servicio;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Autor;
import com.casadelacultura.casadelacultura.repositorio.AutorRepositorio;

@Service
public class AutorServicio {

    @Autowired
    private final AutorRepositorio autorRepositorio;

    public AutorServicio(AutorRepositorio autorRepositorio) {
        this.autorRepositorio = autorRepositorio;
    }

    // Obtener todos los autores
    public Iterable<Autor> listarAutores() {
        return autorRepositorio.findAll();
    }

    // Obtener un autor por ID
    public Optional<Autor> obtenerAutorPorId(Integer idAutor) {
        return autorRepositorio.findById(idAutor);
    }

    // Crear un nuevo autor
    public Autor crearAutor(Autor autor) {
        return autorRepositorio.save(autor);
    }

    // Actualizar un autor existente
    public Optional<Autor> actualizarAutor(Integer idAutor, Autor formulario) {
        return autorRepositorio.findById(idAutor).map(autorExistente -> {
            autorExistente.setNombreAutor(formulario.getNombreAutor());
            autorExistente.setApellidoPaterno(formulario.getApellidoPaterno());
            autorExistente.setApellidoMaterno(formulario.getApellidoMaterno());
            autorExistente.setFechaNacimiento(formulario.getFechaNacimiento());
            autorExistente.setFechaFallecimiento(formulario.getFechaFallecimiento());
            return autorRepositorio.save(autorExistente);
        });
    }

    // Eliminar un autor por ID
    public boolean eliminarAutor(Integer idAutor) {
        Optional<Autor> autor = autorRepositorio.findById(idAutor);
        if (autor.isPresent()) {
            autorRepositorio.delete(autor.get());
            return true;
        }
        return false;
    }
}
