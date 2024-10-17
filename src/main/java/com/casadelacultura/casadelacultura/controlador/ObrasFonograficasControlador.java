package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.ObrasFonograficas;
import com.casadelacultura.casadelacultura.repositorio.ObrasFonograficasRepositorio;

import java.util.Optional;

// Controlador para manejar las operaciones CRUD de ObrasFonograficas
@RestController
@RequestMapping("/api/obrasFonograficas")
public class ObrasFonograficasControlador {

    @Autowired
    private ObrasFonograficasRepositorio obrasFonograficasRepositorio;

    // Obtener todas las obras fonográficas
    @GetMapping
    public ResponseEntity<Iterable<ObrasFonograficas>> list() {
        return ResponseEntity.ok(obrasFonograficasRepositorio.findAll());
    }

    // Obtener una obra fonográfica por ID
    @GetMapping("{idObrasFonograficas}")
    public ResponseEntity<ObrasFonograficas> get(@PathVariable Integer idObrasFonograficas) {
        Optional<ObrasFonograficas> obraFonografica = obrasFonograficasRepositorio.findById(idObrasFonograficas);
        return obraFonografica.map(ResponseEntity::ok)
                              .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva obra fonográfica
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ObrasFonograficas create(@RequestBody ObrasFonograficas obrasFonograficas) {
        return obrasFonograficasRepositorio.save(obrasFonograficas);
    }

    // Actualizar una obra fonográfica existente
    @PutMapping("{idObrasFonograficas}")
    public ResponseEntity<ObrasFonograficas> update(@PathVariable Integer idObrasFonograficas, @RequestBody ObrasFonograficas formulario) {
        Optional<ObrasFonograficas> optionalObraFonografica = obrasFonograficasRepositorio.findById(idObrasFonograficas);
        if (optionalObraFonografica.isPresent()) {
            ObrasFonograficas obraFonograficaFromDB = optionalObraFonografica.get();
            obraFonograficaFromDB.setTituloObraFonografica(formulario.getTituloObraFonografica());
            obraFonograficaFromDB.setDuracion(formulario.getDuracion());
            obraFonograficaFromDB.setFechaLanzamiento(formulario.getFechaLanzamiento());
            obraFonograficaFromDB.setDescripcion(formulario.getDescripcion());
            obraFonograficaFromDB.setIdUrlImagenPortada(formulario.getIdUrlImagenPortada());
            obraFonograficaFromDB.setActivo(formulario.getActivo());
            return ResponseEntity.ok(obrasFonograficasRepositorio.save(obraFonograficaFromDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Obra fonográfica no encontrada
    }

    // Eliminar una obra fonográfica
    @DeleteMapping("{idObrasFonograficas}")
    public ResponseEntity<Void> delete(@PathVariable Integer idObrasFonograficas) {
        Optional<ObrasFonograficas> optionalObraFonografica = obrasFonograficasRepositorio.findById(idObrasFonograficas);
        if (optionalObraFonografica.isPresent()) {
            obrasFonograficasRepositorio.delete(optionalObraFonografica.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Eliminación exitosa
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Obra fonográfica no encontrada
    }
}
