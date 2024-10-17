package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.ObrasFonograficasPorAutor;
import com.casadelacultura.casadelacultura.repositorio.ObrasFonograficasPorAutorRepositorio;

import java.util.Optional;

// Controlador para manejar las operaciones CRUD de ObrasFonograficasPorAutor
@RestController
@RequestMapping("/api/obrasFonograficasPorAutor")
public class ObrasFonograficasPorAutorControlador {

    @Autowired
    private ObrasFonograficasPorAutorRepositorio obrasFonograficasPorAutorRepositorio;

    // Obtener todas las relaciones de obras fonográficas por autor
    @GetMapping
    public ResponseEntity<Iterable<ObrasFonograficasPorAutor>> list() {
        return ResponseEntity.ok(obrasFonograficasPorAutorRepositorio.findAll());
    }

    // Obtener una relación por ID de autor
    @GetMapping("{idAutor}")
    public ResponseEntity<ObrasFonograficasPorAutor> get(@PathVariable Integer idAutor) {
        Optional<ObrasFonograficasPorAutor> obrasPorAutor = obrasFonograficasPorAutorRepositorio.findById(idAutor);
        return obrasPorAutor.map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva relación entre obra fonográfica y autor
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ObrasFonograficasPorAutor create(@RequestBody ObrasFonograficasPorAutor obrasFonograficasPorAutor) {
        return obrasFonograficasPorAutorRepositorio.save(obrasFonograficasPorAutor);
    }

    // Actualizar una relación existente
    @PutMapping("{idAutor}")
    public ResponseEntity<ObrasFonograficasPorAutor> update(@PathVariable Integer idAutor, @RequestBody ObrasFonograficasPorAutor formulario) {
        Optional<ObrasFonograficasPorAutor> optionalObrasPorAutor = obrasFonograficasPorAutorRepositorio.findById(idAutor);
        if (optionalObrasPorAutor.isPresent()) {
            ObrasFonograficasPorAutor obrasPorAutorFromDB = optionalObrasPorAutor.get();
            obrasPorAutorFromDB.setAutor(formulario.getAutor());
            obrasPorAutorFromDB.setObrasFonograficas(formulario.getObrasFonograficas());
            return ResponseEntity.ok(obrasFonograficasPorAutorRepositorio.save(obrasPorAutorFromDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Relación no encontrada
    }

    // Eliminar una relación
    @DeleteMapping("{idAutor}")
    public ResponseEntity<Void> delete(@PathVariable Integer idAutor) {
        Optional<ObrasFonograficasPorAutor> optionalObrasPorAutor = obrasFonograficasPorAutorRepositorio.findById(idAutor);
        if (optionalObrasPorAutor.isPresent()) {
            obrasFonograficasPorAutorRepositorio.delete(optionalObrasPorAutor.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Eliminación exitosa
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Relación no encontrada
    }
}
