package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Libro;
import com.casadelacultura.casadelacultura.excepciones.LibroNoEncontradoException;
import com.casadelacultura.casadelacultura.repositorio.LibroRepositorio;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class LibroServicio {
    private final LibroRepositorio libroRepositorio;

    // Obtener todos los libros
    public Iterable<Libro> listarLibros() {
        return libroRepositorio.findAll();
    }

    // Obtener un libro por ID
    public Libro obtenerLibroPorId(Long idLibro) {
        return libroRepositorio.findById(idLibro).orElseThrow(() -> new LibroNoEncontradoException("No se encontro libro con el ID: "+ idLibro));
    }

    // Crear un nuevo libro
    public Libro crearLibro(Libro libro) {
        libro.setFechaCreacion(LocalDateTime.now());
        return libroRepositorio.save(libro);
    }

    // Actualizar un libro existente
    public Libro actualizarLibro(Long idLibro, Libro formulario) {
        Libro libroFromDB = obtenerLibroPorId(idLibro);
        libroFromDB.setAsin(formulario.getAsin());
        libroFromDB.setTituloLibro(formulario.getTituloLibro());
        libroFromDB.setNombreEditorial(formulario.getNombreEditorial());
        libroFromDB.setEdicionlibro(formulario.getEdicionlibro());
        libroFromDB.setLugarProsedenciaLibro(formulario.getLugarProsedenciaLibro());
        libroFromDB.setCantidadPaginas(formulario.getCantidadPaginas());
        libroFromDB.setDescripcion(formulario.getDescripcion());
        libroFromDB.setImagenPath(formulario.getImagenPath());
        libroFromDB.setEstaActivo(formulario.getEstaActivo());
        libroFromDB.setFechaLibro(formulario.getFechaLibro());
        libroFromDB.setCategoriaLibro(formulario.getCategoriaLibro());
        return libroRepositorio.save(libroFromDB);
    }

    // Eliminar un libro
    public void eliminarLibro(Long idLibro) {
        Libro libroFromDB = obtenerLibroPorId(idLibro);
        libroRepositorio.delete(libroFromDB);
    }
}
