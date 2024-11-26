package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.CategoriaLibro;
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
        return categoriaLibroRepositorio.findById(idCategoriaLibro).orElse(null);
    }

    // Crear una nueva categoría de libro
    public CategoriaLibro crearCategoria(CategoriaLibro categoriaLibro) {
        return categoriaLibroRepositorio.save(categoriaLibro);
    }

    // Actualizar una categoría de libro existente
    public CategoriaLibro actualizarCategoria(Long idCategoriaLibro, CategoriaLibro formulario) {
        CategoriaLibro categoriaLibroFromDB = obtenerCategoriaPorId(idCategoriaLibro);
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
