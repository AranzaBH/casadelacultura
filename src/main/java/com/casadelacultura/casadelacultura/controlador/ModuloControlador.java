package com.casadelacultura.casadelacultura.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Modulo;
import com.casadelacultura.casadelacultura.servicio.ModuloServicio;

import lombok.AllArgsConstructor;

/**
 * Controlador REST para manejar las solicitudes HTTP relacionadas con la entidad Modulo.
 * Este controlador proporciona las operaciones CRUD.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/modulo") // Ruta base para las operaciones de Modulo.
public class ModuloControlador {

    // Servicio que contiene la lógica de negocio para la entidad Modulo.
    private final ModuloServicio moduloServicio;

    /**
     * Obtiene todos los módulos almacenados.
     * 
     * @return Lista de todos los módulos.
     */
    @GetMapping()
    public Iterable<Modulo> list() {
        return moduloServicio.findAll();
    }

    /**
     * Obtiene un módulo por su ID.
     * 
     * @param idModulo ID del módulo a buscar.
     * @return El módulo encontrado o null si no existe.
     */
    @GetMapping("/{idModulo}")
    public Modulo get(@PathVariable Integer idModulo) {
        return moduloServicio.findById(idModulo);
    }

    /**
     * Crea un nuevo módulo.
     * 
     * @param modulo Datos del nuevo módulo en formato JSON.
     * @return El módulo creado y guardado en la base de datos.
     */
    @ResponseStatus(HttpStatus.CREATED) // Código de estado 201 si se crea correctamente.
    @PostMapping
    public Modulo create(@RequestBody Modulo modulo) {
        return moduloServicio.create(modulo);
    }

    /**
     * Actualiza un módulo existente.
     * 
     * @param idModulo ID del módulo a actualizar.
     * @param formulario Datos nuevos del módulo.
     * @return El módulo actualizado.
     */
    @PutMapping("/{idModulo}")
    public Modulo update(@PathVariable Integer idModulo, @RequestBody Modulo formulario) {
        return moduloServicio.update(idModulo, formulario);
    }

    /**
     * Elimina un módulo por su ID.
     * 
     * @param idModulo ID del módulo a eliminar.
     */
    @ResponseStatus(HttpStatus.NO_CONTENT) // Código de estado 204 si se elimina correctamente.
    @DeleteMapping("/{idModulo}")
    public void delete(@PathVariable Integer idModulo) {
        moduloServicio.delete(idModulo);
    }
}
