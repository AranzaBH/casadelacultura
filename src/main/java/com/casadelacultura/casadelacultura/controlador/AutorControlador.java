package com.casadelacultura.casadelacultura.controlador;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Autor;
import com.casadelacultura.casadelacultura.servicio.AutorServicio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
// Clase controlador JSON para colecciones de objetos Autor
@RestController
// Rutas API
@RequestMapping("/api/autor")
@CrossOrigin("*")
public class AutorControlador {
    private final AutorServicio autorServicio;

    @GetMapping
    public Iterable<Autor> list() {
        return autorServicio.listarAutores();
    }

    // Método para obtener un solo autor por su id
    @GetMapping("{idAutor}")
    public Autor obtenerAutorPorId(@PathVariable Long idAutor) {
        return autorServicio.obtenerAutorPorId(idAutor);
    }

    // Creación de un nuevo autor
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Autor createAutor(@Valid @RequestBody Autor autor) {
        return autorServicio.crearAutor(autor);
    }

    // Actualización de un autor
    @PutMapping("{idAutor}")
    public Autor actualizarAutor(@PathVariable Long idAutor, @RequestBody @Valid Autor formulario) {
        return autorServicio.actualizarAutor(idAutor, formulario);
    }

    // Eliminar un autor
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idAutor}")
    public void delete(@PathVariable Long idAutor) {
        autorServicio.eliminarAutor(idAutor);
    }
}
