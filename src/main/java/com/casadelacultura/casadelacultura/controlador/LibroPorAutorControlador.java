package com.casadelacultura.casadelacultura.controlador;

import javax.validation.*;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.LibrosPorAutor;
import com.casadelacultura.casadelacultura.servicio.LibroPorAutorServicio;

import lombok.AllArgsConstructor;

// Controlador para manejar las operaciones CRUD de LibrosPorAutor
@AllArgsConstructor
@RestController
@RequestMapping("/api/librosporautor")
@CrossOrigin("*")
public class LibroPorAutorControlador {
    private final LibroPorAutorServicio libroPorAutorServicio;

    // Obtener todos los libros por autor
    @GetMapping
    public Iterable<LibrosPorAutor> list() {
        return libroPorAutorServicio.listarLibrosPorAutor();
    }

    // Obtener una relación libro-autor por ID
    @GetMapping("{idLibroPorAutor}")
    public LibrosPorAutor get(@PathVariable Long idLibroPorAutor) {
        return libroPorAutorServicio.obtenerLibroPorAutorPorId(idLibroPorAutor);

    }

    // Crear una nueva relación libro-autor
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public LibrosPorAutor create(@Valid @RequestBody LibrosPorAutor librosPorAutor) {
        return libroPorAutorServicio.crearLibroPorAutor(librosPorAutor);
    }

    // Actualizar una relación libro-autor existente
    @PutMapping("{idLibroPorAutor}")
    public LibrosPorAutor update(@PathVariable Long idLibroPorAutor, @RequestBody @Valid LibrosPorAutor formulario) {
        return libroPorAutorServicio.actualizarLibroPorAutor(idLibroPorAutor, formulario);
    }

    // Eliminar una relación libro-autor
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idLibroPorAutor}")
    public void delete(@PathVariable Long idLibroPorAutor) {
        libroPorAutorServicio.eliminarLibroPorAutor(idLibroPorAutor);
    }
}
