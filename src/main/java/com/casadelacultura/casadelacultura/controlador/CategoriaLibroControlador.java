package com.casadelacultura.casadelacultura.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.CategoriaLibro;
import com.casadelacultura.casadelacultura.servicio.CategoriaLibroServicio;

import lombok.AllArgsConstructor;

// Controlador para manejar las operaciones CRUD de CategoriaLibro
@AllArgsConstructor
@RestController
@RequestMapping("/api/categorialibro")
@CrossOrigin("*")
public class CategoriaLibroControlador {
    private final CategoriaLibroServicio categoriaLibroServicio;

    // Obtener todas las categorías de libros
    @GetMapping
    public Iterable<CategoriaLibro> list() {
        return categoriaLibroServicio.listarCategorias();
    }

    // Obtener una categoría de libro por ID
    @GetMapping("{idCategoriaLibro}")
    public CategoriaLibro getCategoriaLibroId(@PathVariable Long idCategoriaLibro) {
        return categoriaLibroServicio.obtenerCategoriaPorId(idCategoriaLibro);
    }

    // Crear una nueva categoría de libro
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoriaLibro create(@RequestBody CategoriaLibro categoriaLibro) {
        return categoriaLibroServicio.crearCategoria(categoriaLibro);
    }

    // Actualizar una categoría de libro existente
    @PutMapping("{idCategoriaLibro}")
    public CategoriaLibro update(@PathVariable Long idCategoriaLibro, @RequestBody CategoriaLibro formulario) {
        return categoriaLibroServicio.actualizarCategoria(idCategoriaLibro, formulario);
    }

    // Eliminar una categoría de libro
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idCategoriaLibro}")
    public void delete(@PathVariable Long idCategoriaLibro) {
        categoriaLibroServicio.eliminarCategoria(idCategoriaLibro);
    }
}
