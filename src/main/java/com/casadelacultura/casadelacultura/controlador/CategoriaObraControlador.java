package com.casadelacultura.casadelacultura.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.CategoriaObra;
import com.casadelacultura.casadelacultura.servicio.CategoriaObraServicio;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("/api/categoriaobra")
@CrossOrigin("*")
public class CategoriaObraControlador {
    private final CategoriaObraServicio categoriaObraServicio;

    // Obtener todas las categorías de obras
    @GetMapping
    public Iterable<CategoriaObra> list() {
        return categoriaObraServicio.listarCategorias();
    }

    // Obtener una categoría de obra por ID
    @GetMapping("{idCategoriaObra}")
    public CategoriaObra obtenerCategoriaObraId(@PathVariable Long idCategoriaObra) {
        return categoriaObraServicio.obtenerCategoriaPorId(idCategoriaObra);
    }

    // Crear una nueva categoría de obra
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoriaObra create(@RequestBody CategoriaObra categoriaObra) {
        return categoriaObraServicio.crearCategoria(categoriaObra);
    }

    // Actualizar una categoría de obra existente
    @PutMapping("{idCategoriaObra}")
    public CategoriaObra update(@PathVariable Long idCategoriaObra, @RequestBody CategoriaObra formulario) {
        return categoriaObraServicio.actualizarCategoria(idCategoriaObra, formulario);
    }

    // Eliminar una categoría de obra
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idCategoriaObra}")
    public void delete(@PathVariable Long idCategoriaObra) {
        categoriaObraServicio.eliminarCategoria(idCategoriaObra);
    }
}
