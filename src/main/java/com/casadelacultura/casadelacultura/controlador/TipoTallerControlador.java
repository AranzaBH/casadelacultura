package com.casadelacultura.casadelacultura.controlador;

import java.util.List;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.casadelacultura.casadelacultura.entity.TipoTaller;
import com.casadelacultura.casadelacultura.servicio.TipoTallerServicio;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController // Marca la clase como un controlador REST que gestiona respuestas en formato JSON.
@RequestMapping("/api/tipotaller") // Define la ruta base para acceder a este controlador.
@CrossOrigin("*")
public class TipoTallerControlador {
    
    // Servicio que contiene la lógica de negocio para la entidad TipoTaller.
    private final TipoTallerServicio tipoTallerServicio;

    /**
     * Obtiene todos los tipos de talleres.
     * @return Una lista de todas las entidades TipoTaller almacenadas en la base de datos.
     */
    @GetMapping()
    public List<TipoTaller> list() {
        return tipoTallerServicio.findAll();
    }

    /**
     * Obtiene un solo tipo de taller por su ID.
     * @param idTipoTaller ID del tipo de taller que se desea obtener.
     * @return La entidad TipoTaller si se encuentra, de lo contrario, null.
     */
    @GetMapping("{idTipoTaller}")
    public TipoTaller get(@PathVariable Long idTipoTaller){
        return tipoTallerServicio.findById(idTipoTaller);

    }

    /**
     * Crea un nuevo tipo de taller.
     * @param tipoTaller La información del nuevo tipo de taller en formato JSON.
     * @return La entidad TipoTaller creada y almacenada en la base de datos.
     */
    @ResponseStatus(HttpStatus.CREATED) // Indica que, si se crea correctamente, se devuelve el código de estado 201.
    @PostMapping
    public TipoTaller create(@Valid @RequestBody TipoTaller tipoTaller){
        return tipoTallerServicio.create(tipoTaller); 
    }
    /**
     * Actualiza un tipo de taller existente.
     * @param idTipoTaller ID del tipo de taller a actualizar.
     * @param formulario Contiene los nuevos datos del tipo de taller.
     * @return La entidad TipoTaller actualizada.
     */
    @PutMapping("{idTipoTaller}") // Se utiliza para actualizaciones completas de entidades existentes.
    public TipoTaller update(@PathVariable Long idTipoTaller, @RequestBody @Valid TipoTaller formulario){
        return tipoTallerServicio.update(idTipoTaller,formulario); 

    }
    /**
     * Elimina un tipo de taller por su ID.
     * @param idTipoTaller ID del tipo de taller que se desea eliminar.
     */
    /* 
    @ResponseStatus(HttpStatus.NO_CONTENT) // Indica que, si se elimina correctamente, se devuelve el código de estado 204.
    @DeleteMapping("{idTipoTaller}") // Se utiliza para eliminar una entidad.
    public void delate(@PathVariable Long idTipoTaller){
        tipoTallerServicio.delate(idTipoTaller);; 
    }*/
    @DeleteMapping("{idTipoTaller}")
public ResponseEntity<String> delate(@PathVariable Long idTipoTaller) {
    String mensaje = tipoTallerServicio.delate(idTipoTaller);
    return ResponseEntity.ok(mensaje);  // Enviar el mensaje como respuesta con el código 200 OK
}

}