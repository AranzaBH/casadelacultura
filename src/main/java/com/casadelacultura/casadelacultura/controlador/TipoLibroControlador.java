package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.TipoLibro;
import com.casadelacultura.casadelacultura.repositorio.TipoLibroRepositorio;

import java.util.Optional;

// Controlador para manejar las operaciones CRUD de TipoLibro
@RestController
@RequestMapping("/api/tipoLibro")
public class TipoLibroControlador {

    @Autowired
    private TipoLibroRepositorio tipoLibroRepositorio;

    // Obtener todos los tipos de libro
    @GetMapping
    public ResponseEntity<Iterable<TipoLibro>> list() {
        return ResponseEntity.ok(tipoLibroRepositorio.findAll());
    }

    // Obtener un tipo de libro por su ID
    @GetMapping("{idTipoLibro}")
    public ResponseEntity<TipoLibro> get(@PathVariable Long idTipoLibro) {
        Optional<TipoLibro> tipoLibro = tipoLibroRepositorio.findById(idTipoLibro);
        return tipoLibro.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo tipo de libro
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TipoLibro create(@RequestBody TipoLibro tipoLibro) {
        return tipoLibroRepositorio.save(tipoLibro);
    }

    // Actualizar un tipo de libro existente
    @PutMapping("{idTipoLibro}")
    public ResponseEntity<TipoLibro> update(@PathVariable Long idTipoLibro, @RequestBody TipoLibro formulario) {
        Optional<TipoLibro> optionalTipoLibro = tipoLibroRepositorio.findById(idTipoLibro);
        if (optionalTipoLibro.isPresent()) {
            TipoLibro tipoLibroFromDB = optionalTipoLibro.get();
            tipoLibroFromDB.setNombreTipoLibro(formulario.getNombreTipoLibro());
            tipoLibroFromDB.setDescripcion(formulario.getDescripcion());
            return ResponseEntity.ok(tipoLibroRepositorio.save(tipoLibroFromDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Tipo de libro no encontrado
    }

    // Eliminar un tipo de libro
    @DeleteMapping("{idTipoLibro}")
    public ResponseEntity<Void> delete(@PathVariable Long idTipoLibro) {
        Optional<TipoLibro> optionalTipoLibro = tipoLibroRepositorio.findById(idTipoLibro);
        if (optionalTipoLibro.isPresent()) {
            tipoLibroRepositorio.delete(optionalTipoLibro.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Eliminación exitosa
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Tipo de libro no encontrado
    }
}
