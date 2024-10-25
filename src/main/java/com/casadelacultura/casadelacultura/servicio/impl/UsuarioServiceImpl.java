package com.casadelacultura.casadelacultura.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

import com.casadelacultura.casadelacultura.entity.Usuario;
import com.casadelacultura.casadelacultura.entity.UsuarioRol;
import com.casadelacultura.casadelacultura.excepciones.UsuarioFoundException;
import com.casadelacultura.casadelacultura.repositorio.RolRepository;
import com.casadelacultura.casadelacultura.repositorio.UsuarioRepository;
import com.casadelacultura.casadelacultura.servicio.UsuarioService;

/**
 * Implementación de la interfaz UsuarioService que proporciona servicios para la gestión de usuarios.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {
    /**
     * Repositorio para las operaciones de acceso a datos de Usuario.
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Repositorio para las operaciones de acceso a datos de Rol.
     */
    @Autowired
    private RolRepository rolRepository;

    /**
     * Guarda un usuario nuevo en la base de datos, junto con sus roles asociados.
     * 
     * @param usuario El objeto Usuario que se desea guardar
     * @param usuarioRoles Conjunto de roles asociados al usuario
     * @return El usuario guardado en la base de datos
     * @throws UsuarioFoundException Si el usuario ya existe en la base de datos
     */
    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        if(usuarioLocal != null){
            System.out.println("El usuario ya existe");
            throw new UsuarioFoundException("El usuario ya esta presente");
        }
        else{
            for(UsuarioRol usuarioRol:usuarioRoles){
                rolRepository.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);
        }
        return usuarioLocal;
    }

    /**
     * Obtiene un usuario por su nombre de usuario.
     * 
     * @param username Nombre de usuario a buscar
     * @return Usuario correspondiente al nombre de usuario proporcionado, o null si no se encuentra
     */
    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

    /**
     * Elimina un usuario de la base de datos por su ID.
     * 
     * @param usuarioId ID del usuario a eliminar
     */
    @Override
    public void eliminarUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

}