package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * Entidad Rol que representa la tabla "rol" en la base de datos.
 * Define el rol o perfil de un usuario en el sistema.
 */
@Entity
@Table(name = "rol")
public class Rol {

    @Id
    private Long rolId;
    private String rolNombre;

    /**
     * Conjunto de relaciones entre el rol y los usuarios.
     * 
     */
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "rol")
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

    public Rol(){

    }

    /**
     * Constructor con todos los atributos.
     * 
     * @param rolId Identificador único del rol
     * @param rolNombre Nombre del rol
     */
    public Rol(Long rolId, String rolNombre) {
        this.rolId = rolId;
        this.rolNombre = rolNombre;
    }

     /**
     * Obtiene el ID del rol.
     * 
     * @return ID del rol
     */
    public Long getRolId() {
        return rolId;
    }

    /**
     * Establece el ID del rol.
     * 
     * @param rolId ID del rol
     */
    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    /**
     * Obtiene los roles de usuario asociados a este rol.
     * 
     * @return Conjunto de roles de usuario
     */
    public Set<UsuarioRol> getUsuarioRoles() {
        return usuarioRoles;
    }

    /**
     * Establece los roles de usuario asociados a este rol.
     * 
     * @param usuarioRoles Conjunto de roles de usuario
     */
    public void setUsuarioRoles(Set<UsuarioRol> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }
}