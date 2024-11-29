package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;

import com.casadelacultura.casadelacultura.entity.Autor;
import com.casadelacultura.casadelacultura.entity.Libro;
import com.casadelacultura.casadelacultura.entity.LibrosPorAutor;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.AutorRepositorio;
import com.casadelacultura.casadelacultura.repositorio.LibroRepositorio;
import com.casadelacultura.casadelacultura.repositorio.LibrosPorAutorRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LibroPorAutorServicio {
    private final LibrosPorAutorRepositorio librosPorAutorRepositorio;
    private final AutorRepositorio autorRepositorio;
    private final LibroRepositorio libroRepositorio;

    // Obtener todos los libros por autor
    public Iterable<LibrosPorAutor> listarLibrosPorAutor() {
        return librosPorAutorRepositorio.findAll();
    }

    // Obtener una relación libro-autor por ID
    public LibrosPorAutor obtenerLibroPorAutorPorId(Long idLibroPorAutor) {
        return librosPorAutorRepositorio.findById(idLibroPorAutor)
                .orElseThrow(() -> new GlobalExceptionNoEncontrada(
                        "No se encontro la relacion libro por autor con el ID " + idLibroPorAutor));
    }

    // Crear una nueva relación libro-autor
    public LibrosPorAutor crearLibroPorAutor(LibrosPorAutor librosPorAutor) {
        // Valida si la relacion libor autor ya existe
        if (librosPorAutorRepositorio.existsByAutor_IdAutorAndLibro_IdLibro(
                librosPorAutor.getAutor().getIdAutor(), librosPorAutor.getLibro().getIdLibro())) {
            throw new GlobalExceptionNoEncontrada(
                    "Ya esite la relación para el Autor con ID " + librosPorAutor.getAutor().getIdAutor() +
                            " y el libro con ID " + librosPorAutor.getLibro().getIdLibro());

        }
        // Validar la existencia del Autor
        if (librosPorAutor.getAutor() == null
                || !autorRepositorio.existsById(librosPorAutor.getAutor().getIdAutor())) {
            throw new GlobalExceptionNoEncontrada(
                    "Autor con ID " + librosPorAutor.getAutor().getIdAutor() + " no encontrado.");
        }
        // Validar la existencia del Libro
        if (librosPorAutor.getLibro() == null
                || !libroRepositorio.existsById(librosPorAutor.getLibro().getIdLibro())) {
            throw new GlobalExceptionNoEncontrada(
                    "Libro con ID " + librosPorAutor.getLibro().getIdLibro() + " no encontrado.");
        }
        return librosPorAutorRepositorio.save(librosPorAutor);
    }

    // Actualizar una relación libro-autor existente
    public LibrosPorAutor actualizarLibroPorAutor(Long idLibroPorAutor, LibrosPorAutor formulario) {
        LibrosPorAutor librosPorAutorFrom = obtenerLibroPorAutorPorId(idLibroPorAutor);
        // Verifica si ya existe la relación
        if (librosPorAutorRepositorio.existsByAutor_IdAutorAndLibro_IdLibroAndIdLibroPorAutor(
                formulario.getAutor().getIdAutor(),
                formulario.getLibro().getIdLibro(),
                idLibroPorAutor)) {
            // Obtener el autor correspondiente
            Autor autor = autorRepositorio.findById(formulario.getAutor().getIdAutor())
                    .orElseThrow(() -> new GlobalExceptionNoEncontrada("Autor no encontrada"));
            Libro libro = libroRepositorio.findById(formulario.getLibro().getIdLibro())
                    .orElseThrow(() -> new GlobalExceptionNoEncontrada("Libro no encontrada"));
            throw new GlobalExceptionNoEncontrada(
                    "Ya existe una relacion para el libro con el titulo: " + libro.getTituloLibro() +
                            " (ID: " + formulario.getLibro().getIdLibro() +
                            " ) y el autor  con el nombre " + autor.getNombreAutor() + " " + autor.getApellidoPaterno()
                            + " " + autor.getApellidoMaterno() +
                            " (ID: " + formulario.getAutor().getIdAutor() + " )");

        }

        // Validar la existencia del Autor
        if (formulario.getAutor() == null
                || !autorRepositorio.existsById(formulario.getAutor().getIdAutor())) {
            throw new GlobalExceptionNoEncontrada(
                    "Autor con ID " + formulario.getAutor().getIdAutor() + " no encontrado.");
        }

        // Validar la existencia del Libro
        if (formulario.getLibro() == null
                || !libroRepositorio.existsById(formulario.getLibro().getIdLibro())) {
            throw new GlobalExceptionNoEncontrada(
                    "Libro con ID " + formulario.getLibro().getIdLibro() + " no encontrado.");
        }
        librosPorAutorFrom.setAutor(formulario.getAutor());
        librosPorAutorFrom.setLibro(formulario.getLibro());
        return librosPorAutorRepositorio.save(librosPorAutorFrom);

    }

    // Eliminar una relación libro-autor
    public void eliminarLibroPorAutor(Long idLibroPorAutor) {
        LibrosPorAutor librosPorAutorFrom = obtenerLibroPorAutorPorId(idLibroPorAutor);
        librosPorAutorRepositorio.delete(librosPorAutorFrom);
    }
}
