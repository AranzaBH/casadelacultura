package com.casadelacultura.casadelacultura.controlador;

import org.springframework.http.HttpStatus;
import com.casadelacultura.casadelacultura.entity.Usuario;
import com.casadelacultura.casadelacultura.servicio.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para manejar las solicitudes HTTP relacionadas con la entidad Usuario.
 * Este controlador se encarga de las operaciones CRUD mediante los métodos expuestos.
 */
@CrossOrigin
@AllArgsConstructor
@RestController // Marca la clase como un controlador REST que gestiona respuestas en formato JSON.
@RequestMapping("/api/usuario") // Define la ruta base para acceder a este controlador.
public class UsuarioControlador {
    
    // Servicio que contiene la lógica de negocio para la entidad Usuario.
    private final UsuarioServicio usuarioServicio;

    /**
     * Obtiene todos los usuarios.
     * @return Una lista de todos los usuarios almacenados en la base de datos.
     */
    @GetMapping()
    public Iterable<Usuario> list() {
        return usuarioServicio.findAll();
    }

    /**
     * Obtiene un solo usuario por su ID.
     * @param idUsuario ID del usuario que se desea obtener.
     * @return La entidad Usuario si se encuentra, de lo contrario, null.
     */
    @GetMapping("{idUsuario}")
    public Usuario get(@PathVariable Integer idUsuario){
        return usuarioServicio.findById(idUsuario);
    }

    /**
     * Crea un nuevo usuario.
     * @param usuario La información del nuevo usuario en formato JSON.
     * @return El usuario creado y almacenado en la base de datos.
     */
    @ResponseStatus(HttpStatus.CREATED) // Indica que, si se crea correctamente, se devuelve el código de estado 201.
    @PostMapping
    public Usuario create(@RequestBody Usuario usuario){
        return usuarioServicio.create(usuario); 
    }

    /**
     * Actualiza un usuario existente.
     * @param idUsuario ID del usuario a actualizar.
     * @param formulario Contiene los nuevos datos del usuario.
     * @return El usuario actualizado.
     */
    @PutMapping("{idUsuario}") // Se utiliza para actualizaciones completas de entidades existentes.
    public Usuario update(@PathVariable Integer idUsuario, @RequestBody Usuario formulario){
        return usuarioServicio.update(idUsuario, formulario);
    }

    /**
     * Elimina un usuario por su ID.
     * @param idUsuario ID del usuario que se desea eliminar.
     */
    @ResponseStatus(HttpStatus.NO_CONTENT) // Indica que, si se elimina correctamente, se devuelve el código de estado 204.
    @DeleteMapping("{idUsuario}") // Se utiliza para eliminar una entidad.
    public void delete(@PathVariable Integer idUsuario){
        usuarioServicio.delete(idUsuario); 
    }

    
}

