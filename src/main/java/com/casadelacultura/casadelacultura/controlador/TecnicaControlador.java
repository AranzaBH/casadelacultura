package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Tecnica;
import com.casadelacultura.casadelacultura.repositorio.TecnicaRepositorio;

import java.util.Optional;

// Controlador para manejar las operaciones CRUD de Tecnica
@RestController
@RequestMapping("/api/tecnica")
public class TecnicaControlador {

    @Autowired
    private TecnicaRepositorio tecnicaRepositorio;

    // Obtener todas las técnicas
    @GetMapping
    public ResponseEntity<Iterable<Tecnica>> list() {
        return ResponseEntity.ok(tecnicaRepositorio.findAll());
    }

    // Obtener una técnica por su ID
    @GetMapping("{idTecnica}")
    public ResponseEntity<Tecnica> get(@PathVariable Long idTecnica) {
        Optional<Tecnica> tecnica = tecnicaRepositorio.findById(idTecnica);
        return tecnica.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva técnica
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Tecnica create(@RequestBody Tecnica tecnica) {
        return tecnicaRepositorio.save(tecnica);
    }

    // Actualizar una técnica existente
    @PutMapping("{idTecnica}")
    public ResponseEntity<Tecnica> update(@PathVariable Long idTecnica, @RequestBody Tecnica formulario) {
        Optional<Tecnica> optionalTecnica = tecnicaRepositorio.findById(idTecnica);
        if (optionalTecnica.isPresent()) {
            Tecnica tecnicaFromDB = optionalTecnica.get();
            tecnicaFromDB.setNombreTecnica(formulario.getNombreTecnica());
            tecnicaFromDB.setDescripcionTecnica(formulario.getDescripcionTecnica());
            return ResponseEntity.ok(tecnicaRepositorio.save(tecnicaFromDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Técnica no encontrada
    }

    // Eliminar una técnica
    @DeleteMapping("{idTecnica}")
    public ResponseEntity<Void> delete(@PathVariable Long idTecnica) {
        Optional<Tecnica> optionalTecnica = tecnicaRepositorio.findById(idTecnica);
        if (optionalTecnica.isPresent()) {
            tecnicaRepositorio.delete(optionalTecnica.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Eliminación exitosa
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Técnica no encontrada
    }
}
