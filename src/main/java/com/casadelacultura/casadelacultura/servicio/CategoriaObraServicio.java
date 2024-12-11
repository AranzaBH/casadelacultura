package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.CategoriaObra;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.CategoriaObraRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoriaObraServicio {
    @Autowired
    private final CategoriaObraRepositorio categoriaObraRepositorio;

    // Obtener todas las categorías de obras
    public Iterable<CategoriaObra> listarCategorias() {
        return categoriaObraRepositorio.findAll();
    }

    // Obtener una categoría de obra por ID
    public CategoriaObra obtenerCategoriaPorId(Long idCategoriaObra) {
        return categoriaObraRepositorio.findById(idCategoriaObra)
                .orElseThrow(() -> new GlobalExceptionNoEncontrada(
                        "No se encontro la Categoria de la Obra con el ID " + idCategoriaObra));
    }

    // Crear una nueva categoría de obra
    public CategoriaObra crearCategoria(CategoriaObra categoriaObra) {
        if (categoriaObraRepositorio.existsByNombreCategoriaIgnoreCase(categoriaObra.getNombreCategoria())) {
            throw new GlobalExceptionNoEncontrada("Ya existe lla Categoria de obras con el nombre: "+ categoriaObra.getNombreCategoria());
        }
        return categoriaObraRepositorio.save(categoriaObra);
    }

    // Actualizar una categoría de obra existente
    public CategoriaObra actualizarCategoria(Long idCategoriaObra, CategoriaObra formulario) {
        CategoriaObra categoriaObraFromDB = obtenerCategoriaPorId(idCategoriaObra);
        if (categoriaObraRepositorio.existsByNombreCategoriaIgnoreCaseAndIdCategoriaObraNot(formulario.getNombreCategoria(), idCategoriaObra)) {
            throw new GlobalExceptionNoEncontrada("Ya existe la tecnica con el nombre: "+ formulario.getNombreCategoria());
        }
        categoriaObraFromDB.setNombreCategoria(formulario.getNombreCategoria());
        categoriaObraFromDB.setDescripcionCategoria(formulario.getDescripcionCategoria());
        return categoriaObraRepositorio.save(categoriaObraFromDB);
        
    }

    // Eliminar una categoría de obra
    public void eliminarCategoria(Long idCategoriaObra) {
        CategoriaObra categoriaObraFromDB = obtenerCategoriaPorId(idCategoriaObra);
        categoriaObraRepositorio.delete(categoriaObraFromDB);
    }
}
