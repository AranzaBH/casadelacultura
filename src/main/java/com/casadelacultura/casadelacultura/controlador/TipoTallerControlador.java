package com.casadelacultura.casadelacultura.controlador;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.casadelacultura.casadelacultura.entity.TipoTaller;
import com.casadelacultura.casadelacultura.repositorio.TipoTallerRepositorio;

//Clase controlador JSON colecciones de objetos
@RestController
//Rutas Api
@RequestMapping("/api/tipotaller")
public class TipoTallerControlador {
    
    @Autowired
    private TipoTallerRepositorio tipoTallerRepositorio;

    @GetMapping()
    Iterable<TipoTaller> list() {
        return tipoTallerRepositorio.findAll();

    }

    //Metodo para optener un solo tipodetaller
    @GetMapping("{idTipoTaller}")
    public TipoTaller get(@PathVariable Integer idTipoTaller){
        return tipoTallerRepositorio.findById(idTipoTaller).orElse(null);

    }

    //creacion de un nuevo tipo taller
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TipoTaller create(@RequestBody TipoTaller tipoTaller){
        tipoTaller.setFechaCreacion(LocalDateTime.now()); //Se le asigna una fecha de creacion
        return tipoTallerRepositorio.save(tipoTaller); 

    }
 
    @PutMapping("{idTipoTaller}") //Actualizacion
    public TipoTaller update(@PathVariable Integer idTipoTaller, @RequestBody TipoTaller formulario){
        TipoTaller tipoTallerFromDB = tipoTallerRepositorio.findById(idTipoTaller).orElse(null);
        tipoTallerFromDB.setNombreTipoTaller(formulario.getNombreTipoTaller()); //Se va a establecer el nombre del tipo de taller para actualizar
        tipoTallerFromDB.setDescripcion(formulario.getDescripcion()); //Tambien se atualiza contacto
        return tipoTallerRepositorio.save(tipoTallerFromDB); 

    }

    //Eliminar
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idTipoTaller}") //Eliminar
    public void delate(@PathVariable Integer idTipoTaller){
        TipoTaller tipoTallerFromDB = tipoTallerRepositorio.findById(idTipoTaller).orElse(null);
        tipoTallerRepositorio.delete(tipoTallerFromDB); 
    }

}
