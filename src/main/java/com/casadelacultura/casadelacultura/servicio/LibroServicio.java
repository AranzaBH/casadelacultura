package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;

import com.casadelacultura.casadelacultura.entity.Libro;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.CategoriaLibroRepositorio;
import com.casadelacultura.casadelacultura.repositorio.LibroRepositorio;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class LibroServicio {
    private final LibroRepositorio libroRepositorio;
    private final CategoriaLibroRepositorio categoriaLibroRepositorio;

    // Obtener todos los libros
    public Iterable<Libro> listarLibros() {
        return libroRepositorio.findAll();
    }

    // Obtener un libro por ID
    public Libro obtenerLibroPorId(Long idLibro) {
        return libroRepositorio.findById(idLibro)
                .orElseThrow(() -> new GlobalExceptionNoEncontrada("No se encontro libro con el ID: " + idLibro));
    }

    // Crear un nuevo libro
    // Crear un nuevo libro
    public Libro crearLibro(Libro libro) {
        validarFechas(libro);
        // Validar si ya existe un libro con los mismos datos
        if (libroRepositorio.existsByTituloLibroAndAsinAndNombreEditorialAndEdicionlibroAndCantidadPaginas(
                libro.getTituloLibro(), libro.getAsin(), libro.getNombreEditorial(),
                libro.getEdicionlibro(), libro.getCantidadPaginas())) {
            throw new GlobalExceptionNoEncontrada("Ya existe un libro con el título: " + libro.getTituloLibro()
                    + ", ASIN: " + libro.getAsin() + ", editorial: " + libro.getNombreEditorial()
                    + ", edición: " + libro.getEdicionlibro() + ", y cantidad de páginas: "
                    + libro.getCantidadPaginas());
        }

        // Validar si la categoría del libro existe
        if (libro.getCategoriaLibro() == null
                || !categoriaLibroRepositorio.existsById(libro.getCategoriaLibro().getIdCategoriaLibro())) {
            throw new GlobalExceptionNoEncontrada(
                    "Categoria Libro con ID " + libro.getCategoriaLibro().getIdCategoriaLibro() + " no encontrado.");
        }

        // Configurar la fecha de creación automáticamente
        libro.setFechaCreacion(LocalDateTime.now());

        return libroRepositorio.save(libro);
    }

    // Actualizar un libro existente
    public Libro actualizarLibro(Long idLibro, Libro formulario) {
        Libro libroFromDB = obtenerLibroPorId(idLibro);
        validarFechas(formulario);
        // Validar si ya existe un libro con los mismos datos, excluyendo el actual
        if (libroRepositorio.existsByTituloLibroAndAsinAndNombreEditorialAndEdicionlibroAndCantidadPaginasAndIdLibroNot(
                formulario.getTituloLibro(), formulario.getAsin(), formulario.getNombreEditorial(),
                formulario.getEdicionlibro(), formulario.getCantidadPaginas(), idLibro)) {
            throw new GlobalExceptionNoEncontrada("Ya existe un libro con el título: " + formulario.getTituloLibro()
                    + ", ASIN: " + formulario.getAsin() + ", editorial: " + formulario.getNombreEditorial()
                    + ", edición: " + formulario.getEdicionlibro() + ", y cantidad de páginas: "
                    + formulario.getCantidadPaginas());
        }

        // Validar la existencia del Libro
        if (formulario.getCategoriaLibro() == null || !categoriaLibroRepositorio.existsById(formulario.getCategoriaLibro().getIdCategoriaLibro())) {
            throw new GlobalExceptionNoEncontrada(
                    "Libro con ID " + formulario.getCategoriaLibro().getIdCategoriaLibro() + " no encontrado.");
        }

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

    public void validarFechas(Libro libro) {
        LocalDate hoy = LocalDate.now();
        // Validar si la fecha de nacimiento es mayor a la fecha de hoy
        if (libro.getFechaLibro() != null && libro.getFechaLibro().isAfter(hoy)) {
            throw new GlobalExceptionNoEncontrada("La fecha de publicacion del libro no puede ser mayor al día de hoy.");
        }
    }

    
}
