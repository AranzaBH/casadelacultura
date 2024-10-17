package com.casadelacultura.casadelacultura.controlador;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Autor;
import com.casadelacultura.casadelacultura.repositorio.AutorRepositorio;

// Clase controlador JSON para colecciones de objetos Autor
@RestController
// Rutas API
@RequestMapping("/api/autor")
public class AutorControlador {

    @Autowired
    private AutorRepositorio autorRepositorio;

    @GetMapping
    public ResponseEntity<Iterable<Autor>> list() {
        return ResponseEntity.ok(autorRepositorio.findAll());
    }

    // Método para obtener un solo autor por su id
    @GetMapping("{idAutor}")
    public ResponseEntity<Autor> get(@PathVariable Integer idAutor) {
        Optional<Autor> autor = autorRepositorio.findById(idAutor);
        return autor.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Creación de un nuevo autor
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Autor create(@RequestBody Autor autor) {
        return autorRepositorio.save(autor);
    }

    // Actualización de un autor
    @PutMapping("{idAutor}")
    public ResponseEntity<Autor> update(@PathVariable Integer idAutor, @RequestBody Autor formulario) {
        Optional<Autor> optionalAutor = autorRepositorio.findById(idAutor);
        if (optionalAutor.isPresent()) {
            Autor autorFromDB = optionalAutor.get();
            autorFromDB.setNombreAutor(formulario.getNombreAutor());
            autorFromDB.setApellidoPaterno(formulario.getApellidoPaterno());
            autorFromDB.setApellidoMaterno(formulario.getApellidoMaterno());
            autorFromDB.setFechaNacimiento(formulario.getFechaNacimiento());
            autorFromDB.setFechaFallecimiento(formulario.getFechaFallecimiento());
            return ResponseEntity.ok(autorRepositorio.save(autorFromDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Autor no encontrado
    }

    // Eliminar un autor
    @DeleteMapping("{idAutor}")
    public ResponseEntity<Void> delete(@PathVariable Integer idAutor) {
        Optional<Autor> optionalAutor = autorRepositorio.findById(idAutor);
        if (optionalAutor.isPresent()) {
            autorRepositorio.delete(optionalAutor.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Eliminación exitosa
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Autor no encontrado
    }
}
