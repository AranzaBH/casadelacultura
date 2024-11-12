package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;

/**
 * Entidad UsuarioRol que representa la relación entre las entidades Usuario y Rol.
 */
@Entity
public class UsuarioRol {
    /**
     * Identificador único de la relación Usuario-Rol.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioRolId;

    /**
     * Usuario asociado a esta relación.
     * La carga es EAGER para obtener la información del usuario al momento de cargar la relación.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    /**
     * Rol asociado a esta relación.
     */
    @ManyToOne
    private Rol rol;

    /**
     * Obtiene el ID de la relación Usuario-Rol.
     * 
     * @return ID de la relación Usuario-Rol
     */
    public Long getUsuarioRolId() {
        return usuarioRolId;
    }

    /**
     * Establece el ID de la relación Usuario-Rol.
     * 
     * @param usuarioRolId ID de la relación Usuario-Rol
     */
    public void setUsuarioRolId(Long usuarioRolId) {
        this.usuarioRolId = usuarioRolId;
    }

    /**
     * Obtiene el usuario asociado a esta relación.
     * 
     * @return Usuario de esta relación
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado a esta relación.
     * 
     * @param usuario Usuario de esta relación
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el rol asociado a esta relación.
     * 
     * @return Rol de esta relación
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Establece el rol asociado a esta relación.
     * 
     * @param rol Rol de esta relación
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }
}

