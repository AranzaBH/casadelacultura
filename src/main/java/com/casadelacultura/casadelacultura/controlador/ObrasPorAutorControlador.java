
package com.casadelacultura.casadelacultura.controlador;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.ObrasPorAutor;
import com.casadelacultura.casadelacultura.servicio.ObrasPorAutorServicio;

import lombok.AllArgsConstructor;

// Controlador para manejar las operaciones CRUD de ObrasPorAutor
@AllArgsConstructor
@RestController
@RequestMapping("/api/obrasporautor")
@CrossOrigin("*")
public class ObrasPorAutorControlador {
    private final ObrasPorAutorServicio obrasPorAutorServicio;

    // Obtener todas las relaciones de obras por autor
    @GetMapping
    public Iterable<ObrasPorAutor> list() {
        return obrasPorAutorServicio.listarObrasPorAutor();
    }

    // Obtener una relación por ID de autor
    @GetMapping("{idObrasPorAutor}")
    public ObrasPorAutor get(@PathVariable Long idObrasPorAutor) {
        return obrasPorAutorServicio.obtenerRelacionPorId(idObrasPorAutor);
    }

    // Crear una nueva relación entre obra y autor
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ObrasPorAutor create(@Valid @RequestBody ObrasPorAutor obrasPorAutor) {
        return obrasPorAutorServicio.crearRelacion(obrasPorAutor);
    }

    // Actualizar una relación existente
    @PutMapping("{idObrasPorAutor}")
    public ObrasPorAutor update(@PathVariable Long idObrasPorAutor, @RequestBody @Valid ObrasPorAutor formulario) {
        return obrasPorAutorServicio.actualizarRelacion(idObrasPorAutor, formulario);
    }

    // Eliminar una relación
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idObrasPorAutor}")
    public void delete(@PathVariable Long idObrasPorAutor) {
        obrasPorAutorServicio.eliminarRelacion(idObrasPorAutor);
    }
}
