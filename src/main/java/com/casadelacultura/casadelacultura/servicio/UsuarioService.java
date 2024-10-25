package com.casadelacultura.casadelacultura.servicio;

import java.util.Set;
import com.casadelacultura.casadelacultura.entity.Usuario;
import com.casadelacultura.casadelacultura.entity.UsuarioRol;

/**
 * Interfaz UsuarioService que define los métodos de servicio para la gestión de usuarios.
 */
public interface UsuarioService {
    /**
     * Guarda un nuevo usuario en el sistema junto con sus roles asociados.
     * 
     * @param usuario El objeto Usuario que se desea guardar
     * @param usuarioRoles Conjunto de roles asociados al usuario
     * @return El usuario guardado en el sistema
     * @throws Exception Si ocurre un error durante el guardado del usuario
     */
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    /**
     * Obtiene un usuario a partir de su nombre de usuario.
     * 
     * @param username Nombre de usuario a buscar
     * @return El usuario correspondiente al nombre de usuario proporcionado o null si no se encuentra
     */
    public Usuario obtenerUsuario(String username);

    /**
     * Elimina un usuario del sistema mediante su ID.
     * 
     * @param usuarioId ID del usuario a eliminar
     */
    public void eliminarUsuario(Long usuarioId);
}
