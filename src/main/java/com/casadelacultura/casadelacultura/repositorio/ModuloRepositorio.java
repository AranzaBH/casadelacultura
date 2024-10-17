package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.casadelacultura.casadelacultura.entity.Modulo;

/**
 * Repositorio para gestionar operaciones CRUD de la entidad Modulo.
 * 
 * Extiende la interfaz CrudRepository de Spring Data, lo que proporciona
 * métodos predefinidos para operaciones básicas como guardar, actualizar,
 * eliminar y buscar entidades.
 * 
 * @param Modulo La entidad sobre la cual se realizarán las operaciones CRUD.
 * @param Integer El tipo de dato de la clave primaria de la entidad Modulo.
 */
public interface ModuloRepositorio extends CrudRepository<Modulo, Integer> {
    
}
