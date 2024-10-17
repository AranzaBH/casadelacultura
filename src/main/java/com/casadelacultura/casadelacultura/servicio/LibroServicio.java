package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Libro;
import com.casadelacultura.casadelacultura.repositorio.LibroRepositorio;

import java.util.Optional;

@Service
public class LibroServicio {

    @Autowired
    private final LibroRepositorio libroRepositorio;

    public LibroServicio(LibroRepositorio libroRepositorio) {
        this.libroRepositorio = libroRepositorio;
    }

    // Obtener todos los libros
    public Iterable<Libro> listarLibros() {
        return libroRepositorio.findAll();
    }

    // Obtener un libro por ID
    public Optional<Libro> obtenerLibroPorId(Integer idLibro) {
        return libroRepositorio.findById(idLibro);
    }

    // Crear un nuevo libro
    public Libro crearLibro(Libro libro) {
        return libroRepositorio.save(libro);
    }

    // Actualizar un libro existente
    public Optional<Libro> actualizarLibro(Integer idLibro, Libro formulario) {
        return libroRepositorio.findById(idLibro).map(libroExistente -> {
            libroExistente.setAsin(formulario.getAsin());
            libroExistente.setTituloLibro(formulario.getTituloLibro());
            libroExistente.setNombreEditorial(formulario.getNombreEditorial());
            libroExistente.setLugarProsedenciaLibro(formulario.getLugarProsedenciaLibro());
            libroExistente.setCantidadPaginas(formulario.getCantidadPaginas());
            libroExistente.setDescripcion(formulario.getDescripcion());
            libroExistente.setIdUrlImagenPortada(formulario.getIdUrlImagenPortada());
            libroExistente.setEstaActivo(formulario.getEstaActivo());
            libroExistente.setFechaCreacion(formulario.getFechaCreacion());
            libroExistente.setCategoriaLibro(formulario.getCategoriaLibro());
            libroExistente.setTipoLibro(formulario.getTipoLibro());
            return libroRepositorio.save(libroExistente);
        });
    }

    // Eliminar un libro
    public boolean eliminarLibro(Integer idLibro) {
        Optional<Libro> libro = libroRepositorio.findById(idLibro);
        if (libro.isPresent()) {
            libroRepositorio.delete(libro.get());
            return true;
        }
        return false;
    }
}
