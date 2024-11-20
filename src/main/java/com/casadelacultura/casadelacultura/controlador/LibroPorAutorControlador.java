package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.LibrosPorAutor;
import com.casadelacultura.casadelacultura.repositorio.LibrosPorAutorRepositorio;

import java.util.Optional;

// Controlador para manejar las operaciones CRUD de LibrosPorAutor
@RestController
@RequestMapping("/api/librosPorAutor")
@CrossOrigin("*")
public class LibroPorAutorControlador {

    @Autowired
    private LibrosPorAutorRepositorio librosPorAutorRepositorio;

    // Obtener todos los libros por autor
    @GetMapping
    public ResponseEntity<Iterable<LibrosPorAutor>> list() {
        return ResponseEntity.ok(librosPorAutorRepositorio.findAll());
    }

    // Obtener una relación libro-autor por ID
    @GetMapping("{idAutor}")
    public ResponseEntity<LibrosPorAutor> get(@PathVariable Long idAutor) {
        Optional<LibrosPorAutor> libroPorAutor = librosPorAutorRepositorio.findById(idAutor);
        return libroPorAutor.map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva relación libro-autor
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public LibrosPorAutor create(@RequestBody LibrosPorAutor librosPorAutor) {
        return librosPorAutorRepositorio.save(librosPorAutor);
    }

    // Actualizar una relación libro-autor existente
    @PutMapping("{idAutor}")
    public ResponseEntity<LibrosPorAutor> update(@PathVariable Long idAutor, @RequestBody LibrosPorAutor formulario) {
        Optional<LibrosPorAutor> optionalLibroPorAutor = librosPorAutorRepositorio.findById(idAutor);
        if (optionalLibroPorAutor.isPresent()) {
            LibrosPorAutor libroPorAutorFromDB = optionalLibroPorAutor.get();
            libroPorAutorFromDB.setAutor(formulario.getAutor());
            libroPorAutorFromDB.setLibro(formulario.getLibro());
            return ResponseEntity.ok(librosPorAutorRepositorio.save(libroPorAutorFromDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Relación no encontrada
    }

    // Eliminar una relación libro-autor
    @DeleteMapping("{idAutor}")
    public ResponseEntity<Void> delete(@PathVariable Long idAutor) {
        Optional<LibrosPorAutor> optionalLibroPorAutor = librosPorAutorRepositorio.findById(idAutor);
        if (optionalLibroPorAutor.isPresent()) {
            librosPorAutorRepositorio.delete(optionalLibroPorAutor.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Eliminación exitosa
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Relación no encontrada
    }
}
