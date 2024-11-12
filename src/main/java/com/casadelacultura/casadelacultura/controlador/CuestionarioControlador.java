package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.casadelacultura.casadelacultura.entity.Cuestionario;
import com.casadelacultura.casadelacultura.servicio.CuestionarioServicio;

import java.util.Optional;

@RestController
@RequestMapping("/cuestionarios")
public class CuestionarioControlador {

    @Autowired
    private CuestionarioServicio cuestionarioServicio;

    // Obtener todos los cuestionarios
    @GetMapping
    public ResponseEntity<Iterable<Cuestionario>> obtenerTodosLosCuestionarios() {
        Iterable<Cuestionario> cuestionarios = cuestionarioServicio.listarCuestionarios();
        return new ResponseEntity<>(cuestionarios, HttpStatus.OK);
    }

    // Obtener un cuestionario por su ID
    @GetMapping("/{idCuestionario}")
    public ResponseEntity<Cuestionario> obtenerCuestionarioPorId(@PathVariable Long idCuestionario) {
        Optional<Cuestionario> cuestionario = cuestionarioServicio.obtenerCuestionarioPorId(idCuestionario);
        return cuestionario.map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo cuestionario
    @PostMapping
    public ResponseEntity<Cuestionario> crearCuestionario(@RequestBody Cuestionario cuestionario) {
        Cuestionario nuevoCuestionario = cuestionarioServicio.crearCuestionario(cuestionario);
        return new ResponseEntity<>(nuevoCuestionario, HttpStatus.CREATED);
    }

    // Actualizar un cuestionario existente
    @PutMapping("/{idCuestionario}")
    public ResponseEntity<Cuestionario> actualizarCuestionario(@PathVariable Long idCuestionario, @RequestBody Cuestionario formulario) {
        Optional<Cuestionario> cuestionarioActualizado = cuestionarioServicio.actualizarCuestionario(idCuestionario, formulario);
        return cuestionarioActualizado.map(ResponseEntity::ok)
                                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar un cuestionario por su ID
    @DeleteMapping("/{idCuestionario}")
    public ResponseEntity<Void> eliminarCuestionario(@PathVariable Long idCuestionario) {
        boolean eliminado = cuestionarioServicio.eliminarCuestionario(idCuestionario);
        return eliminado ? ResponseEntity.noContent().build()
                         : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
