package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.casadelacultura.casadelacultura.entity.Usuario;

/**
 * Repositorio para gestionar operaciones CRUD de la entidad Usuario.
 * 
 * Extiende la interfaz CrudRepository de Spring Data, lo que proporciona
 * métodos predefinidos para operaciones básicas como guardar, actualizar,
 * eliminar y buscar entidades.
 * 
 * @param Usuario La entidad sobre la cual se realizarán las operaciones CRUD.
 * @param Integer El tipo de dato de la clave primaria de la entidad Usuario.
 */
public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer> {
    
}
