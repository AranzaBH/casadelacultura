package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;

import com.casadelacultura.casadelacultura.entity.Libro;
import com.casadelacultura.casadelacultura.entity.LibrosImagenes;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.ImagenesRepositorio;
import com.casadelacultura.casadelacultura.repositorio.LibroRepositorio;
import com.casadelacultura.casadelacultura.repositorio.LibrosImagenesRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LibrosImagenesServicio {
    private final LibrosImagenesRepositorio librosImagenesRepositorio;
    private final LibroRepositorio libroRepositorio;
    private final ImagenesRepositorio imagenesRepositorio;

    public Iterable<LibrosImagenes> listarLibrosImagenes() {
        return librosImagenesRepositorio.findAll();
    }

    public LibrosImagenes obtenerRelacionPorId(Long idLibrosImagenes) {
        return librosImagenesRepositorio.findById(idLibrosImagenes).orElseThrow(() -> new GlobalExceptionNoEncontrada(
                "No se encontro la relacion libros imagenes con el ID " + idLibrosImagenes));
    }

    public LibrosImagenes crearRealicionLibroImagen(LibrosImagenes librosImagenes) {

        // Validar si la relación ya existe
        if (librosImagenesRepositorio.existsByLibro_IdLibroAndImagenes_IdImagen(
                librosImagenes.getLibro().getIdLibro(), librosImagenes.getImagenes().getIdImagen())) {
            throw new GlobalExceptionNoEncontrada(
                    "Ya existe una relación para el libro con ID " + librosImagenes.getLibro().getIdLibro() +
                            " y la imagen con ID " + librosImagenes.getImagenes().getIdImagen());
        }
        // Valida si existe la imagen
        if (librosImagenes.getImagenes() == null
                || !imagenesRepositorio.existsById(librosImagenes.getImagenes().getIdImagen())) {
            throw new GlobalExceptionNoEncontrada(
                    "Imagene con ID " + librosImagenes.getImagenes().getIdImagen() + " no encontrado.");
        }

        // Validar la existencia del Libro
        if (librosImagenes.getLibro() == null || !libroRepositorio.existsById(librosImagenes.getLibro().getIdLibro())) {
            throw new GlobalExceptionNoEncontrada(
                    "Libro con ID " + librosImagenes.getLibro().getIdLibro() + " no encontrado.");
        }
        return librosImagenesRepositorio.save(librosImagenes);
    }

    // Actualizar
    public LibrosImagenes actualizarRelacionLiborImagen(Long idLibrosImagenes, LibrosImagenes formulario) {
        LibrosImagenes librosImagenesFrom = obtenerRelacionPorId(idLibrosImagenes);

        // Validar si la nueva relación ya existe, excluyendo la actual
        // Validar si la nueva relación ya existe, excluyendo la actual
        if (librosImagenesRepositorio.existsByLibro_IdLibroAndImagenes_IdImagenAndIdLibrosImagenesNot(
                formulario.getLibro().getIdLibro(),
                formulario.getImagenes().getIdImagen(),
                idLibrosImagenes)) {
           // Obtener el libro correspondiente al ID
        Libro libro = libroRepositorio.findById(formulario.getLibro().getIdLibro())
                .orElseThrow(() -> new GlobalExceptionNoEncontrada("Libro no encontrado"));

        // Lanzar la excepción con el nombre del libro en el mensaje
        throw new GlobalExceptionNoEncontrada(
                "Ya existe una relación para el libro con título: " + libro.getTituloLibro() +
                        " (ID: " + formulario.getLibro().getIdLibro() +
                        ") y la imagen con ID: " + formulario.getImagenes().getIdImagen());
    }
        

        // Validar la existencia del Libro
        if (formulario.getLibro() == null || !libroRepositorio.existsById(formulario.getLibro().getIdLibro())) {
            throw new GlobalExceptionNoEncontrada(
                    "Libro con ID " + formulario.getLibro().getIdLibro() + " no encontrado.");
        }

        // Valida si exsite la imagen
        if (formulario.getImagenes() == null
                || !imagenesRepositorio.existsById(formulario.getImagenes().getIdImagen())) {
            throw new GlobalExceptionNoEncontrada(
                    "Imagen con ID " + formulario.getImagenes().getIdImagen() + " no encontrado.");

        }
        librosImagenesFrom.setLibro(formulario.getLibro());
        librosImagenesFrom.setImagenes(formulario.getImagenes());
        return librosImagenesRepositorio.save(librosImagenesFrom);

    }

    // Eliminar una relacion
    public void eliminarRelacionImagenLibro(Long idLibrosImagenes) {
        LibrosImagenes librosImagenesFrom = obtenerRelacionPorId(idLibrosImagenes);
        librosImagenesRepositorio.delete(librosImagenesFrom);
    }
}
