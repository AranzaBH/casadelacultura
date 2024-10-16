package com.casadelacultura.casadelacultura.controlador;

import org.springframework.http.HttpStatus;
import com.casadelacultura.casadelacultura.entity.Rol;
import com.casadelacultura.casadelacultura.servicio.RolServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para manejar las solicitudes HTTP relacionadas con la entidad Rol.
 * Este controlador se encarga de las operaciones CRUD mediante los métodos expuestos.
 */
@AllArgsConstructor
@RestController // Marca la clase como un controlador REST que gestiona respuestas en formato JSON.
@RequestMapping("/api/rol") // Define la ruta base para acceder a este controlador.
public class RolControlador {
    
    // Servicio que contiene la lógica de negocio para la entidad Rol.
    private final RolServicio rolServicio;

    /**
     * Obtiene todos los roles.
     * @return Una lista de todas las entidades Rol almacenadas en la base de datos.
     */
    @GetMapping()
    public Iterable<Rol> list() {
        return rolServicio.findAll();
    }

    /**
     * Obtiene un solo rol por su ID.
     * @param idRol ID del rol que se desea obtener.
     * @return La entidad Rol si se encuentra, de lo contrario, null.
     */
    @GetMapping("{idRol}")
    public Rol get(@PathVariable Integer idRol) {
        return rolServicio.findById(idRol);
    }

    /**
     * Crea un nuevo rol.
     * @param rol La información del nuevo rol en formato JSON.
     * @return La entidad Rol creada y almacenada en la base de datos.
     */
    @ResponseStatus(HttpStatus.CREATED) // Indica que, si se crea correctamente, se devuelve el código de estado 201.
    @PostMapping
    public Rol create(@RequestBody Rol rol) {
        return rolServicio.create(rol);
    }

    /**
     * Actualiza un rol existente.
     * @param idRol ID del rol a actualizar.
     * @param formulario Contiene los nuevos datos del rol.
     * @return La entidad Rol actualizada.
     */
    @PutMapping("{idRol}") // Se utiliza para actualizaciones completas de entidades existentes.
    public Rol update(@PathVariable Integer idRol, @RequestBody Rol formulario) {
        return rolServicio.update(idRol, formulario);
    }

    /**
     * Elimina un rol por su ID.
     * @param idRol ID del rol que se desea eliminar.
     */
    @ResponseStatus(HttpStatus.NO_CONTENT) // Indica que, si se elimina correctamente, se devuelve el código de estado 204.
    @DeleteMapping("{idRol}") // Se utiliza para eliminar una entidad.
    public void delete(@PathVariable Integer idRol) {
        rolServicio.delete(idRol);
    }
}
