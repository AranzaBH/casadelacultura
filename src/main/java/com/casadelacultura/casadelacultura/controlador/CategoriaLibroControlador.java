package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.CategoriaLibro;
import com.casadelacultura.casadelacultura.repositorio.CategoriaLibroRepositorio;

import java.util.Optional;

// Controlador para manejar las operaciones CRUD de CategoriaLibro
@RestController
@RequestMapping("/api/categoriaLibro")
public class CategoriaLibroControlador {

    @Autowired
    private CategoriaLibroRepositorio categoriaLibroRepositorio;

    // Obtener todas las categorías de libros
    @GetMapping
    public ResponseEntity<Iterable<CategoriaLibro>> list() {
        return ResponseEntity.ok(categoriaLibroRepositorio.findAll());
    }

    // Obtener una categoría de libro por ID
    @GetMapping("{idCategoriaLibro}")
    public ResponseEntity<CategoriaLibro> get(@PathVariable Long idCategoriaLibro) {
        Optional<CategoriaLibro> categoria = categoriaLibroRepositorio.findById(idCategoriaLibro);
        return categoria.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva categoría de libro
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoriaLibro create(@RequestBody CategoriaLibro categoriaLibro) {
        return categoriaLibroRepositorio.save(categoriaLibro);
    }

    // Actualizar una categoría de libro existente
    @PutMapping("{idCategoriaLibro}")
    public ResponseEntity<CategoriaLibro> update(@PathVariable Long idCategoriaLibro, @RequestBody CategoriaLibro formulario) {
        Optional<CategoriaLibro> optionalCategoria = categoriaLibroRepositorio.findById(idCategoriaLibro);
        if (optionalCategoria.isPresent()) {
            CategoriaLibro categoriaFromDB = optionalCategoria.get();
            categoriaFromDB.setNombreCategoria(formulario.getNombreCategoria());
            categoriaFromDB.setDescripcionCategoria(formulario.getDescripcionCategoria());
            return ResponseEntity.ok(categoriaLibroRepositorio.save(categoriaFromDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Categoría no encontrada
    }

    // Eliminar una categoría de libro
    @DeleteMapping("{idCategoriaLibro}")
    public ResponseEntity<Void> delete(@PathVariable Long idCategoriaLibro) {
        Optional<CategoriaLibro> optionalCategoria = categoriaLibroRepositorio.findById(idCategoriaLibro);
        if (optionalCategoria.isPresent()) {
            categoriaLibroRepositorio.delete(optionalCategoria.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Eliminación exitosa
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Categoría no encontrada
    }
}
