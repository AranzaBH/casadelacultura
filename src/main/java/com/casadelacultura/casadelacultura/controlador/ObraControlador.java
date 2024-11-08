package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Obra;
import com.casadelacultura.casadelacultura.repositorio.ObraRepositorio;

import java.util.Optional;

// Controlador para manejar las operaciones CRUD de Obra
@RestController
@RequestMapping("/api/obra")
public class ObraControlador {

    @Autowired
    private ObraRepositorio obraRepositorio;

    // Obtener todas las obras
    @GetMapping
    public ResponseEntity<Iterable<Obra>> list() {
        return ResponseEntity.ok(obraRepositorio.findAll());
    }

    // Obtener una obra por ID
    @GetMapping("{idObra}")
    public ResponseEntity<Obra> get(@PathVariable Long idObra) {
        Optional<Obra> obra = obraRepositorio.findById(idObra);
        return obra.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva obra
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Obra create(@RequestBody Obra obra) {
        return obraRepositorio.save(obra);
    }

    // Actualizar una obra existente
    @PutMapping("{idObra}")
    public ResponseEntity<Obra> update(@PathVariable Long idObra, @RequestBody Obra formulario) {
        Optional<Obra> optionalObra = obraRepositorio.findById(idObra);
        if (optionalObra.isPresent()) {
            Obra obraFromDB = optionalObra.get();
            obraFromDB.setNombreObra(formulario.getNombreObra());
            obraFromDB.setEstadoActivo(formulario.isEstadoActivo());
            obraFromDB.setFechaCreacion(formulario.getFechaCreacion());
            obraFromDB.setDimension(formulario.getDimension());
            obraFromDB.setIdUrlImagenPortada(formulario.getIdUrlImagenPortada());
            obraFromDB.setNombreUbicacionCreacion(formulario.getNombreUbicacionCreacion());
            obraFromDB.setTecnica(formulario.getTecnica());
            obraFromDB.setMaterial(formulario.getMaterial());
            obraFromDB.setCategoriaObra(formulario.getCategoriaObra());
            return ResponseEntity.ok(obraRepositorio.save(obraFromDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Obra no encontrada
    }

    // Eliminar una obra
    @DeleteMapping("{idObra}")
    public ResponseEntity<Void> delete(@PathVariable Long idObra) {
        Optional<Obra> optionalObra = obraRepositorio.findById(idObra);
        if (optionalObra.isPresent()) {
            obraRepositorio.delete(optionalObra.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Eliminación exitosa
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Obra no encontrada
    }
}
