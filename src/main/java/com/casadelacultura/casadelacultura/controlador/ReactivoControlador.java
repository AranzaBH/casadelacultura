package com.casadelacultura.casadelacultura.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.casadelacultura.casadelacultura.entity.Reactivo;
import com.casadelacultura.casadelacultura.servicio.ReactivoServicio;

import lombok.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/reactivos")
public class ReactivoControlador {
    private final ReactivoServicio reactivoServicio;

    // Obtener todos los reactivos
    @GetMapping
    public Iterable<Reactivo> obtenerTodosLosReactivos() {
        return reactivoServicio.listarReactivos();
    }

    // Obtener un reactivo por su ID
    @GetMapping("{idReactivo}")
    public Reactivo obtenerReactivoPorId(@PathVariable Long idReactivo) {
        return reactivoServicio.obtenerReactivoPorId(idReactivo);
    }

    // Crear un nuevo reactivo
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Reactivo crearReactivo(@RequestBody Reactivo reactivo) {
        return reactivoServicio.crearReactivo(reactivo);
    }

    // Actualizar un reactivo existente
    @PutMapping("{idReactivo}")
    public Reactivo actualizarReactivo(@PathVariable Long idReactivo, @RequestBody Reactivo formulario) {
        return reactivoServicio.actualizarReactivo(idReactivo, formulario);
    }

    // Eliminar un reactivo por su ID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idReactivo}")
    public void eliminarReactivo(@PathVariable Long idReactivo) {
        reactivoServicio.eliminarReactivo(idReactivo);
    }
}
