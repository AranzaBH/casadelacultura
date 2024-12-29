package com.casadelacultura.casadelacultura.controlador;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Tecnica;
import com.casadelacultura.casadelacultura.servicio.TecnicaServicio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController // Marca la clase como un controlador REST que gestiona respuestas en formato JSON.
@RequestMapping("/api/tecnica") // Define la ruta base para acceder a este controlador.
@CrossOrigin("*")
public class TecnicaControlador {
    private final TecnicaServicio tecnicaServicio;

    // Obtener todas las técnicas
    @GetMapping
    public Iterable<Tecnica> listarTecnica() {
        return tecnicaServicio.obtenerTodasTecnicas();
    }

    // Obtener una técnica por su ID
    @GetMapping("{idTecnica}")
    public Tecnica motrarTecnicaPorId(@PathVariable Long idTecnica) {
        return tecnicaServicio.obtenerTecnicaPorId(idTecnica);
    }

    // Crear una nueva técnica
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Tecnica create(@Valid @RequestBody Tecnica tecnica) {
        return tecnicaServicio.crearTecnica(tecnica);
    }

    // Actualizar una técnica existente
    @PutMapping("{idTecnica}")
    public Tecnica actualizarTecnica(@PathVariable Long idTecnica, @RequestBody Tecnica formulario) {
        return tecnicaServicio.actualizarTecnica(idTecnica, formulario);
    }

    // Eliminar una técnica
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idTecnica}")
    public ResponseEntity<String> delete(@PathVariable Long idTecnica) {
        String mensaje = tecnicaServicio.eliminarTecnica(idTecnica);
        return ResponseEntity.ok(mensaje);
    }
}
