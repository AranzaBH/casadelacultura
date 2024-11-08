package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Convocatorias;
import com.casadelacultura.casadelacultura.repositorio.ConvocatoriasRepositorio;

import java.util.Optional;

// Controlador para manejar las operaciones CRUD de Convocatorias
@RestController
@RequestMapping("/api/convocatorias")
public class ConvocatoriasControlador {

    @Autowired
    private ConvocatoriasRepositorio convocatoriasRepositorio;

    // Obtener todas las convocatorias
    @GetMapping
    public ResponseEntity<Iterable<Convocatorias>> list() {
        return ResponseEntity.ok(convocatoriasRepositorio.findAll());
    }

    // Obtener una convocatoria por ID
    @GetMapping("{idConvocatorias}")
    public ResponseEntity<Convocatorias> get(@PathVariable Long idConvocatorias) {
        Optional<Convocatorias> convocatoria = convocatoriasRepositorio.findById(idConvocatorias);
        return convocatoria.map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva convocatoria
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Convocatorias create(@RequestBody Convocatorias convocatoria) {
        return convocatoriasRepositorio.save(convocatoria);
    }

    // Actualizar una convocatoria existente
    @PutMapping("{idConvocatorias}")
    public ResponseEntity<Convocatorias> update(@PathVariable Long idConvocatorias, @RequestBody Convocatorias formulario) {
        Optional<Convocatorias> optionalConvocatoria = convocatoriasRepositorio.findById(idConvocatorias);
        if (optionalConvocatoria.isPresent()) {
            Convocatorias convocatoriaFromDB = optionalConvocatoria.get();
            convocatoriaFromDB.setFechaInicioInscripcion(formulario.getFechaInicioInscripcion());
            convocatoriaFromDB.setFechaFinInscripcion(formulario.getFechaFinInscripcion());
            convocatoriaFromDB.setEstaActiva(formulario.isEstaActiva());
            //convocatoriaFromDB.setTaller(formulario.getTaller());
            return ResponseEntity.ok(convocatoriasRepositorio.save(convocatoriaFromDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Convocatoria no encontrada
    }

    // Eliminar una convocatoria
    @DeleteMapping("{idConvocatorias}")
    public ResponseEntity<Void> delete(@PathVariable Long idConvocatorias) {
        Optional<Convocatorias> optionalConvocatoria = convocatoriasRepositorio.findById(idConvocatorias);
        if (optionalConvocatoria.isPresent()) {
            convocatoriasRepositorio.delete(optionalConvocatoria.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Eliminación exitosa
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Convocatoria no encontrada
    }
}
