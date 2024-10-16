package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;

import com.casadelacultura.casadelacultura.entity.Rol;
import com.casadelacultura.casadelacultura.repositorio.RolRepositorio;

import lombok.AllArgsConstructor;

/**
 * Servicio encargado de gestionar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * para la entidad Rol.
 * 
 * @Service Indica que esta clase es un servicio gestionado por Spring.
 * @AllArgsConstructor Se genera automáticamente un constructor con todas las dependencias.
 */
@AllArgsConstructor
@Service
public class RolServicio {
    /**
     * Repositorio que permite realizar operaciones de persistencia para Rol.
     */
    private final RolRepositorio rolRepositorio;

    /**
     * Retorna todos los registros de Rol almacenados en la base de datos.
     * 
     * @return Iterable<Rol> Lista de todos los roles.
     */
    public Iterable<Rol> findAll() {
        return rolRepositorio.findAll();
    }

    /**
     * Busca y retorna un Rol por su ID.
     * 
     * @param idRol Identificador del rol que se desea buscar.
     * @return Rol El rol encontrado o null si no existe.
     */
    public Rol findById(Integer idRol) {
        return rolRepositorio.findById(idRol).orElse(null);
    }

    /**
     * Crea un nuevo Rol y lo guarda en la base de datos.
     * 
     * @param rol Objeto Rol a ser creado.
     * @return Rol El objeto guardado en la base de datos.
     */
    public Rol create(Rol rol) {
        return rolRepositorio.save(rol);
    }

    /**
     * Actualiza un Rol existente en la base de datos.
     * 
     * @param idRol ID del rol que se desea actualizar.
     * @param formulario Datos actualizados del Rol.
     * @return Rol El objeto actualizado.
     */
    public Rol update(Integer idRol, Rol formulario) {
        Rol rolFromDB = findById(idRol); // Busca el rol existente.
        rolFromDB.setNombreRol(formulario.getNombreRol()); // Actualiza el nombre del rol.
        rolFromDB.setActivo(formulario.getActivo()); // Actualiza el estado activo.
        return rolRepositorio.save(rolFromDB);
    }

    /**
     * Elimina un Rol por su ID.
     * 
     * @param idRol Identificador del rol que se desea eliminar.
     */
    public void delete(Integer idRol) {
        Rol rolFromDB = findById(idRol); // Busca el rol a eliminar.
        rolRepositorio.delete(rolFromDB);
    }
}