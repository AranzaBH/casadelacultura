package com.casadelacultura.casadelacultura.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Cuestionario;
import com.casadelacultura.casadelacultura.servicio.CuestionarioServicio;

import lombok.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cuestionario")
public class CuestionarioControlador {
    private final CuestionarioServicio cuestionarioServicio;

    // Obtener todos los cuestionarios
    @GetMapping
    public Iterable<Cuestionario> obtenerTodosLosCuestionarios() {
        return cuestionarioServicio.listarCuestionarios();
    }

    // Obtener un cuestionario por su ID
    @GetMapping("{idCuestionario}")
    public Cuestionario obtenerCuestionarioPorId(@PathVariable Long idCuestionario) {
        return cuestionarioServicio.obtenerCuestionarioPorId(idCuestionario);
    }

    // Crear un nuevo cuestionario
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cuestionario crearCuestionario(@RequestBody Cuestionario cuestionario) {
        return cuestionarioServicio.crearCuestionario(cuestionario);
    }

    // Actualizar un cuestionario existente
    @PutMapping("{idCuestionario}")
    public Cuestionario actualizarCuestionario(@PathVariable Long idCuestionario, @RequestBody Cuestionario formulario) {
        return cuestionarioServicio.actualizarCuestionario(idCuestionario, formulario);
    }

    // Eliminar un cuestionario por su ID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idCuestionario}")
    public void eliminarCuestionario(@PathVariable Long idCuestionario) {
        cuestionarioServicio.eliminarCuestionario(idCuestionario);
    }
}
