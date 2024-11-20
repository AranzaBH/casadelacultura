
package com.casadelacultura.casadelacultura.controlador;

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
    @GetMapping("{idAutor}")
    public ObrasPorAutor get(@PathVariable Long idAutor) {
        return obrasPorAutorServicio.obtenerRelacionPorId(idAutor);
    }

    // Crear una nueva relación entre obra y autor
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ObrasPorAutor create(@RequestBody ObrasPorAutor obrasPorAutor) {
        return obrasPorAutorServicio.crearRelacion(obrasPorAutor);
    }

    // Actualizar una relación existente
    @PutMapping("{idAutor}")
    public ObrasPorAutor update(@PathVariable Long idAutor, @RequestBody ObrasPorAutor formulario) {
        return obrasPorAutorServicio.actualizarRelacion(idAutor, formulario);
    }

    // Eliminar una relación
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idAutor}")
    public void delete(@PathVariable Long idAutor) {
        obrasPorAutorServicio.eliminarRelacion(idAutor);
    }
}
