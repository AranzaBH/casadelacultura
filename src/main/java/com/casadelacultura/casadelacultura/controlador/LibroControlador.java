package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Libro;
import com.casadelacultura.casadelacultura.repositorio.LibroRepositorio;

import java.util.Optional;

// Controlador para manejar las operaciones CRUD de Libro
@RestController
@RequestMapping("/api/libro")
public class LibroControlador {

    @Autowired
    private LibroRepositorio libroRepositorio;

    // Obtener todos los libros
    @GetMapping
    public ResponseEntity<Iterable<Libro>> list() {
        return ResponseEntity.ok(libroRepositorio.findAll());
    }

    // Obtener un libro por ID
    @GetMapping("{idLibro}")
    public ResponseEntity<Libro> get(@PathVariable Long idLibro) {
        Optional<Libro> libro = libroRepositorio.findById(idLibro);
        return libro.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo libro
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Libro create(@RequestBody Libro libro) {
        return libroRepositorio.save(libro);
    }

    // Actualizar un libro existente
    @PutMapping("{idLibro}")
    public ResponseEntity<Libro> update(@PathVariable Long idLibro, @RequestBody Libro formulario) {
        Optional<Libro> optionalLibro = libroRepositorio.findById(idLibro);
        if (optionalLibro.isPresent()) {
            Libro libroFromDB = optionalLibro.get();
            libroFromDB.setAsin(formulario.getAsin());
            libroFromDB.setTituloLibro(formulario.getTituloLibro());
            libroFromDB.setNombreEditorial(formulario.getNombreEditorial());
            libroFromDB.setLugarProsedenciaLibro(formulario.getLugarProsedenciaLibro());
            libroFromDB.setCantidadPaginas(formulario.getCantidadPaginas());
            libroFromDB.setDescripcion(formulario.getDescripcion());
            libroFromDB.setIdUrlImagenPortada(formulario.getIdUrlImagenPortada());
            libroFromDB.setEstaActivo(formulario.getEstaActivo());
            libroFromDB.setFechaCreacion(formulario.getFechaCreacion());
            libroFromDB.setCategoriaLibro(formulario.getCategoriaLibro());
            libroFromDB.setTipoLibro(formulario.getTipoLibro());
            return ResponseEntity.ok(libroRepositorio.save(libroFromDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Libro no encontrado
    }

    // Eliminar un libro
    @DeleteMapping("{idLibro}")
    public ResponseEntity<Void> delete(@PathVariable Long idLibro) {
        Optional<Libro> optionalLibro = libroRepositorio.findById(idLibro);
        if (optionalLibro.isPresent()) {
            libroRepositorio.delete(optionalLibro.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Eliminación exitosa
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Libro no encontrado
    }
}
