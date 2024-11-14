package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Taller;
import com.casadelacultura.casadelacultura.repositorio.TallerRepositorio;
import com.casadelacultura.casadelacultura.servicio.S3Service;

import java.util.Optional;

// Controlador para manejar las operaciones CRUD de Taller
@RestController
@RequestMapping("/api/taller")
public class TallerControlador {

    @Autowired
    private TallerRepositorio tallerRepositorio;

    @Autowired
    private S3Service s3Service;

    // Obtener todos los talleres
    @GetMapping
    public ResponseEntity<Iterable<Taller>> list() {
        return ResponseEntity.ok(tallerRepositorio.findAll());
    }

    // Obtener un taller por su ID
    @GetMapping("{idTaller}")
    public ResponseEntity<Taller> get(@PathVariable Long idTaller) {
        Optional<Taller> taller = tallerRepositorio.findById(idTaller);
        return taller.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo taller
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Taller create(@RequestBody Taller taller) {
        return tallerRepositorio.save(taller);
    }

    // Actualizar un taller existente
    @PutMapping("{idTaller}")
    public ResponseEntity<Taller> update(@PathVariable Long idTaller, @RequestBody Taller formulario) {
        Optional<Taller> optionalTaller = tallerRepositorio.findById(idTaller);
        if (optionalTaller.isPresent()) {
            Taller tallerFromDB = optionalTaller.get();
            tallerFromDB.setTituloTaller(formulario.getTituloTaller());
            tallerFromDB.setClave(formulario.getClave());
            tallerFromDB.setDescripcion(formulario.getDescripcion());
            tallerFromDB.setFechaInico(formulario.getFechaInico());
            tallerFromDB.setFechaFinal(formulario.getFechaFinal());
            tallerFromDB.setFechaCreacion(formulario.getFechaCreacion());
            tallerFromDB.setTipoTaller(formulario.getTipoTaller());
            tallerFromDB.setUrlImagenPortadaTaller(formulario.getUrlImagenPortadaTaller());
            tallerFromDB.setEstaActivo(formulario.isEstaActivo());
            return ResponseEntity.ok(tallerRepositorio.save(tallerFromDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Taller no encontrado
    }

    // Eliminar un taller
    @DeleteMapping("{idTaller}")
    public ResponseEntity<Void> delete(@PathVariable Long idTaller) {
        Optional<Taller> optionalTaller = tallerRepositorio.findById(idTaller);
        if (optionalTaller.isPresent()) {
            tallerRepositorio.delete(optionalTaller.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Eliminación exitosa
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Taller no encontrado
    }
}
