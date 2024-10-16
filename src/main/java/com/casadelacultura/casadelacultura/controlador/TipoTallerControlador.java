package com.casadelacultura.casadelacultura.controlador;

import org.springframework.http.HttpStatus;
import com.casadelacultura.casadelacultura.entity.TipoTaller;
import com.casadelacultura.casadelacultura.servicio.TipoTallerServicio;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
/**
 * Controlador REST para manejar las solicitudes HTTP relacionadas con la entidad TipoTaller.
 * Este controlador se encarga de las operaciones CRUD mediante los métodos expuestos.
 */
@AllArgsConstructor
@RestController // Marca la clase como un controlador REST que gestiona respuestas en formato JSON.
@RequestMapping("/api/tipotaller") // Define la ruta base para acceder a este controlador.
public class TipoTallerControlador {
    
    // Servicio que contiene la lógica de negocio para la entidad TipoTaller.
    private final TipoTallerServicio tipoTallerServicio;

    /**
     * Obtiene todos los tipos de talleres.
     * @return Una lista de todas las entidades TipoTaller almacenadas en la base de datos.
     */
    @GetMapping()
    public Iterable<TipoTaller> list() {
        return tipoTallerServicio.findAll();


    }

    /**
     * Obtiene un solo tipo de taller por su ID.
     * @param idTipoTaller ID del tipo de taller que se desea obtener.
     * @return La entidad TipoTaller si se encuentra, de lo contrario, null.
     */
    @GetMapping("{idTipoTaller}")
    public TipoTaller get(@PathVariable Integer idTipoTaller){
        return tipoTallerServicio.findById(idTipoTaller);

    }

    /**
     * Crea un nuevo tipo de taller.
     * @param tipoTaller La información del nuevo tipo de taller en formato JSON.
     * @return La entidad TipoTaller creada y almacenada en la base de datos.
     */
    @ResponseStatus(HttpStatus.CREATED) // Indica que, si se crea correctamente, se devuelve el código de estado 201.
    @PostMapping
    public TipoTaller create(@RequestBody TipoTaller tipoTaller){
        return tipoTallerServicio.create(tipoTaller); 
    }
 
    /**
     * Actualiza un tipo de taller existente.
     * @param idTipoTaller ID del tipo de taller a actualizar.
     * @param formulario Contiene los nuevos datos del tipo de taller.
     * @return La entidad TipoTaller actualizada.
     */
    @PutMapping("{idTipoTaller}") // Se utiliza para actualizaciones completas de entidades existentes.
    public TipoTaller update(@PathVariable Integer idTipoTaller, @RequestBody TipoTaller formulario){
        return tipoTallerServicio.update(idTipoTaller,formulario); 

    }

    /**
     * Elimina un tipo de taller por su ID.
     * @param idTipoTaller ID del tipo de taller que se desea eliminar.
     */
    @ResponseStatus(HttpStatus.NO_CONTENT) // Indica que, si se elimina correctamente, se devuelve el código de estado 204.
    @DeleteMapping("{idTipoTaller}") // Se utiliza para eliminar una entidad.
    public void delate(@PathVariable Integer idTipoTaller){
        tipoTallerServicio.delate(idTipoTaller); 
    }

}
