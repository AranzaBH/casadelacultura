package com.casadelacultura.casadelacultura.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.ObrasFonograficasPorAutor;
import com.casadelacultura.casadelacultura.servicio.ObrasFonograficasPorAutorServicio;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/obrasfonograficasporautor")
@CrossOrigin("*")
public class ObrasFonograficasPorAutorControlador {
    private final ObrasFonograficasPorAutorServicio obrasFonograficasPorAutorServicio;

    // Obtener todas las relaciones de obras fonográficas por autor
    @GetMapping
    public Iterable<ObrasFonograficasPorAutor> list() {
        return obrasFonograficasPorAutorServicio.listarObrasFonograficasPorAutor();
    }

    // Obtener una relación por ID de autor
    @GetMapping("{idObrasFonograficasPorAutor}")
    public ObrasFonograficasPorAutor get(@PathVariable Long idObrasFonograficasPorAutor) {
        return obrasFonograficasPorAutorServicio.obtenerRelacionPorId(idObrasFonograficasPorAutor);
    }

    // Crear una nueva relación entre obra fonográfica y autor
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ObrasFonograficasPorAutor create(@RequestBody ObrasFonograficasPorAutor obrasFonograficasPorAutor) {
        return obrasFonograficasPorAutorServicio.crearRelacion(obrasFonograficasPorAutor);
    }

    // Actualizar una relación existente
    @PutMapping("{idObrasFonograficasPorAutor}")
    public ObrasFonograficasPorAutor update(@PathVariable Long idObrasFonograficasPorAutor,
            @RequestBody ObrasFonograficasPorAutor formulario) {
        return obrasFonograficasPorAutorServicio.actualizarRelacion(idObrasFonograficasPorAutor, formulario);
    }

    // Eliminar una relación
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idObrasFonograficasPorAutor}")
    public void delete(@PathVariable Long idObrasFonograficasPorAutor) {
        obrasFonograficasPorAutorServicio.eliminarRelacion(idObrasFonograficasPorAutor);
    }
}
