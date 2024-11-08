package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.LibrosPorAutor;
import com.casadelacultura.casadelacultura.repositorio.LibrosPorAutorRepositorio;

import java.util.Optional;

@Service
public class LibroPorAutorServicio {

    @Autowired
    private final LibrosPorAutorRepositorio librosPorAutorRepositorio;

    public LibroPorAutorServicio(LibrosPorAutorRepositorio librosPorAutorRepositorio) {
        this.librosPorAutorRepositorio = librosPorAutorRepositorio;
    }

    // Obtener todos los libros por autor
    public Iterable<LibrosPorAutor> listarLibrosPorAutor() {
        return librosPorAutorRepositorio.findAll();
    }

    // Obtener una relación libro-autor por ID
    public Optional<LibrosPorAutor> obtenerLibroPorAutorPorId(Long idAutor) {
        return librosPorAutorRepositorio.findById(idAutor);
    }

    // Crear una nueva relación libro-autor
    public LibrosPorAutor crearLibroPorAutor(LibrosPorAutor librosPorAutor) {
        return librosPorAutorRepositorio.save(librosPorAutor);
    }

    // Actualizar una relación libro-autor existente
    public Optional<LibrosPorAutor> actualizarLibroPorAutor(Long idAutor, LibrosPorAutor formulario) {
        return librosPorAutorRepositorio.findById(idAutor).map(libroPorAutorExistente -> {
            libroPorAutorExistente.setAutor(formulario.getAutor());
            libroPorAutorExistente.setLibro(formulario.getLibro());
            return librosPorAutorRepositorio.save(libroPorAutorExistente);
        });
    }

    // Eliminar una relación libro-autor
    public boolean eliminarLibroPorAutor(Long idAutor) {
        Optional<LibrosPorAutor> libroPorAutor = librosPorAutorRepositorio.findById(idAutor);
        if (libroPorAutor.isPresent()) {
            librosPorAutorRepositorio.delete(libroPorAutor.get());
            return true;
        }
        return false;
    }
}
