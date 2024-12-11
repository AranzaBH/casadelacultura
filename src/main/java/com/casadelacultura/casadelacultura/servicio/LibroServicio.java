package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;

import com.casadelacultura.casadelacultura.entity.CategoriaLibro;
import com.casadelacultura.casadelacultura.entity.Libro;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.LibroRepositorio;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class LibroServicio {
    private final LibroRepositorio libroRepositorio;
    private final CategoriaLibroServicio categoriaLibroServicio;

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
        //Valida si ya existe la categoria
        CategoriaLibro categoriaLibro = categoriaLibroServicio.obtenerCategoriaPorId(libro.getCategoriaLibro().getIdCategoriaLibro());
        libro.setCategoriaLibro(categoriaLibro);
        //Define la fecha automaticamente
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


        //Valida si ya existe la categoria
        CategoriaLibro categoriaLibro = categoriaLibroServicio.obtenerCategoriaPorId(formulario.getCategoriaLibro().getIdCategoriaLibro());
        formulario.setCategoriaLibro(categoriaLibro);

        

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
