package com.casadelacultura.casadelacultura.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Usuario;
import com.casadelacultura.casadelacultura.repositorio.UsuarioRepository;

/**
 * Implementación de la interfaz UserDetailsService para la carga de detalles de usuario
 * en el contexto de autenticación de Spring Security.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

     /**
     * Repositorio para gestionar las operaciones de acceso a datos de Usuario.
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Carga un usuario a partir de su nombre de usuario.
     * 
     * @param username Nombre de usuario a buscar en la base de datos
     * @return UserDetails correspondiente al usuario encontrado
     * @throws UsernameNotFoundException Si el usuario no existe en la base de datos
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByUsername(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return usuario;
    }

}