package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.CategoriaObra;
import com.casadelacultura.casadelacultura.repositorio.CategoriaObraRepositorio;

import java.util.Optional;

@Service
public class CategoriaObraServicio {

    @Autowired
    private final CategoriaObraRepositorio categoriaObraRepositorio;

    public CategoriaObraServicio(CategoriaObraRepositorio categoriaObraRepositorio) {
        this.categoriaObraRepositorio = categoriaObraRepositorio;
    }

    // Obtener todas las categorías de obras
    public Iterable<CategoriaObra> listarCategorias() {
        return categoriaObraRepositorio.findAll();
    }

    // Obtener una categoría de obra por ID
    public Optional<CategoriaObra> obtenerCategoriaPorId(Integer idCategoriaObra) {
        return categoriaObraRepositorio.findById(idCategoriaObra);
    }

    // Crear una nueva categoría de obra
    public CategoriaObra crearCategoria(CategoriaObra categoriaObra) {
        return categoriaObraRepositorio.save(categoriaObra);
    }

    // Actualizar una categoría de obra existente
    public Optional<CategoriaObra> actualizarCategoria(Integer idCategoriaObra, CategoriaObra formulario) {
        return categoriaObraRepositorio.findById(idCategoriaObra).map(categoriaExistente -> {
            categoriaExistente.setNombreCategoria(formulario.getNombreCategoria());
            categoriaExistente.setDescripcionCategoria(formulario.getDescripcionCategoria());
            return categoriaObraRepositorio.save(categoriaExistente);
        });
    }

    // Eliminar una categoría de obra
    public boolean eliminarCategoria(Integer idCategoriaObra) {
        Optional<CategoriaObra> categoria = categoriaObraRepositorio.findById(idCategoriaObra);
        if (categoria.isPresent()) {
            categoriaObraRepositorio.delete(categoria.get());
            return true;
        }
        return false;
    }
}
