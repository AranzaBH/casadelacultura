package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.CategoriaObra;
import com.casadelacultura.casadelacultura.repositorio.CategoriaObraRepositorio;

import java.util.Optional;

// Controlador para manejar las operaciones CRUD de CategoriaObra
@RestController
@RequestMapping("/api/categoriaObra")
@CrossOrigin("*")
public class CategoriaObraControlador {

    @Autowired
    private CategoriaObraRepositorio categoriaObraRepositorio;

    // Obtener todas las categorías de obras
    @GetMapping
    public ResponseEntity<Iterable<CategoriaObra>> list() {
        return ResponseEntity.ok(categoriaObraRepositorio.findAll());
    }

    // Obtener una categoría de obra por ID
    @GetMapping("{idCategoriaObra}")
    public ResponseEntity<CategoriaObra> get(@PathVariable Long idCategoriaObra) {
        Optional<CategoriaObra> categoria = categoriaObraRepositorio.findById(idCategoriaObra);
        return categoria.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva categoría de obra
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoriaObra create(@RequestBody CategoriaObra categoriaObra) {
        return categoriaObraRepositorio.save(categoriaObra);
    }

    // Actualizar una categoría de obra existente
    @PutMapping("{idCategoriaObra}")
    public ResponseEntity<CategoriaObra> update(@PathVariable Long idCategoriaObra, @RequestBody CategoriaObra formulario) {
        Optional<CategoriaObra> optionalCategoria = categoriaObraRepositorio.findById(idCategoriaObra);
        if (optionalCategoria.isPresent()) {
            CategoriaObra categoriaFromDB = optionalCategoria.get();
            categoriaFromDB.setNombreCategoria(formulario.getNombreCategoria());
            categoriaFromDB.setDescripcionCategoria(formulario.getDescripcionCategoria());
            return ResponseEntity.ok(categoriaObraRepositorio.save(categoriaFromDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Categoría no encontrada
    }

    // Eliminar una categoría de obra
    @DeleteMapping("{idCategoriaObra}")
    public ResponseEntity<Void> delete(@PathVariable Long idCategoriaObra) {
        Optional<CategoriaObra> optionalCategoria = categoriaObraRepositorio.findById(idCategoriaObra);
        if (optionalCategoria.isPresent()) {
            categoriaObraRepositorio.delete(optionalCategoria.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Eliminación exitosa
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Categoría no encontrada
    }
}
