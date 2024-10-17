package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.CategoriaLibro;
import com.casadelacultura.casadelacultura.repositorio.CategoriaLibroRepositorio;

import java.util.Optional;

@Service
public class CategoriaLibroServicio {

    @Autowired
    private final CategoriaLibroRepositorio categoriaLibroRepositorio;

    public CategoriaLibroServicio(CategoriaLibroRepositorio categoriaLibroRepositorio) {
        this.categoriaLibroRepositorio = categoriaLibroRepositorio;
    }

    // Obtener todas las categorías de libros
    public Iterable<CategoriaLibro> listarCategorias() {
        return categoriaLibroRepositorio.findAll();
    }

    // Obtener una categoría de libro por ID
    public Optional<CategoriaLibro> obtenerCategoriaPorId(Integer idCategoriaLibro) {
        return categoriaLibroRepositorio.findById(idCategoriaLibro);
    }

    // Crear una nueva categoría de libro
    public CategoriaLibro crearCategoria(CategoriaLibro categoriaLibro) {
        return categoriaLibroRepositorio.save(categoriaLibro);
    }

    // Actualizar una categoría de libro existente
    public Optional<CategoriaLibro> actualizarCategoria(Integer idCategoriaLibro, CategoriaLibro formulario) {
        return categoriaLibroRepositorio.findById(idCategoriaLibro).map(categoriaExistente -> {
            categoriaExistente.setNombreCategoria(formulario.getNombreCategoria());
            categoriaExistente.setDescripcionCategoria(formulario.getDescripcionCategoria());
            return categoriaLibroRepositorio.save(categoriaExistente);
        });
    }

    // Eliminar una categoría de libro
    public boolean eliminarCategoria(Integer idCategoriaLibro) {
        Optional<CategoriaLibro> categoria = categoriaLibroRepositorio.findById(idCategoriaLibro);
        if (categoria.isPresent()) {
            categoriaLibroRepositorio.delete(categoria.get());
            return true;
        }
        return false;
    }
}
