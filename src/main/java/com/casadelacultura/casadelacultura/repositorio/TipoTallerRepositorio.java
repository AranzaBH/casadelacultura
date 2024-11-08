package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.casadelacultura.casadelacultura.entity.TipoTaller;

/**
 * Repositorio para gestionar operaciones CRUD de la entidad TipoTaller.
 * 
 * Extiende la interfaz CrudRepository de Spring Data, lo que proporciona
 * métodos predefinidos para operaciones básicas como guardar, actualizar,
 * eliminar y buscar entidades.
 * 
 * @param TipoTaller La entidad sobre la cual se realizarán las operaciones CRUD.
 * @param Integer El tipo de dato de la clave primaria de la entidad TipoTaller.
 */
public interface TipoTallerRepositorio extends CrudRepository<TipoTaller, Long> {
    
}
