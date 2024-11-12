
package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.ObrasPorAutor;
import com.casadelacultura.casadelacultura.repositorio.ObrasPorAutorRepositorio;

import java.util.Optional;

// Controlador para manejar las operaciones CRUD de ObrasPorAutor
@RestController
@RequestMapping("/api/obrasPorAutor")
public class ObrasPorAutorControlador {

    @Autowired
    private ObrasPorAutorRepositorio obrasPorAutorRepositorio;

    // Obtener todas las relaciones de obras por autor
    @GetMapping
    public ResponseEntity<Iterable<ObrasPorAutor>> list() {
        return ResponseEntity.ok(obrasPorAutorRepositorio.findAll());
    }

    // Obtener una relación por ID de autor
    @GetMapping("{idAutor}")
    public ResponseEntity<ObrasPorAutor> get(@PathVariable Long idAutor) {
        Optional<ObrasPorAutor> obrasPorAutor = obrasPorAutorRepositorio.findById(idAutor);
        return obrasPorAutor.map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva relación entre obra y autor
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ObrasPorAutor create(@RequestBody ObrasPorAutor obrasPorAutor) {
        return obrasPorAutorRepositorio.save(obrasPorAutor);
    }

    // Actualizar una relación existente
    @PutMapping("{idAutor}")
    public ResponseEntity<ObrasPorAutor> update(@PathVariable Long idAutor, @RequestBody ObrasPorAutor formulario) {
        Optional<ObrasPorAutor> optionalObrasPorAutor = obrasPorAutorRepositorio.findById(idAutor);
        if (optionalObrasPorAutor.isPresent()) {
            ObrasPorAutor obrasPorAutorFromDB = optionalObrasPorAutor.get();
            obrasPorAutorFromDB.setAutor(formulario.getAutor());
            obrasPorAutorFromDB.setObra(formulario.getObra());
            return ResponseEntity.ok(obrasPorAutorRepositorio.save(obrasPorAutorFromDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Relación no encontrada
    }

    // Eliminar una relación
    @DeleteMapping("{idAutor}")
    public ResponseEntity<Void> delete(@PathVariable Long idAutor) {
        Optional<ObrasPorAutor> optionalObrasPorAutor = obrasPorAutorRepositorio.findById(idAutor);
        if (optionalObrasPorAutor.isPresent()) {
            obrasPorAutorRepositorio.delete(optionalObrasPorAutor.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Eliminación exitosa
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Relación no encontrada
    }
}
