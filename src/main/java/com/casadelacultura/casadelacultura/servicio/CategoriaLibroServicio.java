package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.CategoriaLibro;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.CategoriaLibroRepositorio;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoriaLibroServicio {
    private final CategoriaLibroRepositorio categoriaLibroRepositorio;

    // Obtener todas las categorías de libros
    public Iterable<CategoriaLibro> listarCategorias() {
        return categoriaLibroRepositorio.findAll();
    }

    // Obtener una categoría de libro por ID
    public CategoriaLibro obtenerCategoriaPorId(Long idCategoriaLibro) {
        return categoriaLibroRepositorio.findById(idCategoriaLibro)
                .orElseThrow(() -> new GlobalExceptionNoEncontrada(
                        "No se encontro la categoria del libro con el ID " + idCategoriaLibro));
    }

    // Crear una nueva categoría de libro
    public CategoriaLibro crearCategoria(CategoriaLibro categoriaLibro) {
        if (categoriaLibroRepositorio.existsByNombreCategoriaIgnoreCase(categoriaLibro.getNombreCategoria())) {
            throw new GlobalExceptionNoEncontrada("Ya existe la categoria con el nombre: "+ categoriaLibro.getNombreCategoria());
            
        }
        return categoriaLibroRepositorio.save(categoriaLibro);
    }

    // Actualizar una categoría de libro existente
    public CategoriaLibro actualizarCategoria(Long idCategoriaLibro, CategoriaLibro formulario) {
        CategoriaLibro categoriaLibroFromDB = obtenerCategoriaPorId(idCategoriaLibro);
        if (categoriaLibroRepositorio.existsByNombreCategoriaIgnoreCaseAndIdCategoriaLibroNot(formulario.getNombreCategoria(), idCategoriaLibro)) {
            throw new GlobalExceptionNoEncontrada("Ya existe la categoria con el nombre: "+ formulario.getNombreCategoria());
        }
        categoriaLibroFromDB.setNombreCategoria(formulario.getNombreCategoria());
        categoriaLibroFromDB.setDescripcionCategoria(formulario.getDescripcionCategoria());
        return categoriaLibroRepositorio.save(categoriaLibroFromDB);
    }

    // Eliminar una categoría de libro
    public void eliminarCategoria(Long idCategoriaLibro) {
        CategoriaLibro categoriaLibroFromDB = obtenerCategoriaPorId(idCategoriaLibro);
        categoriaLibroRepositorio.delete(categoriaLibroFromDB);
    }
}