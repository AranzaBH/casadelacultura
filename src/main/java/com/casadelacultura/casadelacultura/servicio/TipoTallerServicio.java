package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import com.casadelacultura.casadelacultura.entity.TipoTaller;
import com.casadelacultura.casadelacultura.repositorio.TipoTallerRepositorio;
import lombok.AllArgsConstructor;

/**
 * Servicio encargado de gestionar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * para la entidad TipoTaller.
 * 
 * @Service Indica que esta clase es un servicio gestionado por Spring.
 * @AllArgsConstructor Se genera automáticamente un constructor con todas las dependencias.
 */
@AllArgsConstructor
@Service
public class TipoTallerServicio {
    /**
     * Repositorio que permite realizar operaciones de persistencia para TipoTaller.
     */
    private final TipoTallerRepositorio tipoTallerRepositorio;

    /**
     * Retorna todos los registros de TipoTaller almacenados en la base de datos.
     * 
     * @return Iterable<TipoTaller> Lista de todos los tipos de talleres.
     */
    public Iterable<TipoTaller> findAll() {
        return tipoTallerRepositorio.findAll();

    }

    /**
     * Busca y retorna un TipoTaller por su ID.
     * 
     * @param idTipoTaller Identificador del tipo de taller que se desea buscar.
     * @return TipoTaller El tipo de taller encontrado o null si no existe.
     */
    public TipoTaller findById(Long idTipoTaller){
        return tipoTallerRepositorio.findById(idTipoTaller).orElse(null);

    }

    /**
     * Crea un nuevo TipoTaller y lo guarda en la base de datos.
     * 
     * @param tipoTaller Objeto TipoTaller a ser creado.
     * @return TipoTaller El objeto guardado en la base de datos.
     */
    public TipoTaller create(TipoTaller tipoTaller){
        tipoTaller.setFechaCreacion(LocalDateTime.now()); //Se le asigna una fecha de creacion
        return tipoTallerRepositorio.save(tipoTaller); 

    }

    /**
     * Actualiza un TipoTaller existente en la base de datos.
     * 
     * @param idTipoTaller ID del tipo de taller que se desea actualizar.
     * @param formulario Datos actualizados del TipoTaller.
     * @return TipoTaller El objeto actualizado.
     */
    public TipoTaller update(Long idTipoTaller,TipoTaller formulario){
        TipoTaller tipoTallerFromDB = findById(idTipoTaller);// Busca el tipo de taller existente.
        tipoTallerFromDB.setNombreTipoTaller(formulario.getNombreTipoTaller()); // Actualiza el nombre.
        tipoTallerFromDB.setDescripcion(formulario.getDescripcion()); // Actualiza la descripción.
        return tipoTallerRepositorio.save(tipoTallerFromDB); 

    }

    /**
     * Elimina un TipoTaller por su ID.
     * 
     * @param idTipoTaller Identificador del tipo de taller que se desea eliminar.
     */
    public void delate(Long idTipoTaller){
        TipoTaller tipoTallerFromDB = findById(idTipoTaller);// Busca el tipo de taller a eliminar.
        tipoTallerRepositorio.delete(tipoTallerFromDB); 
    }
}
