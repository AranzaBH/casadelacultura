package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

import com.casadelacultura.casadelacultura.entity.Rol;
import com.casadelacultura.casadelacultura.entity.Usuario;
import com.casadelacultura.casadelacultura.entity.UsuarioRol;
import com.casadelacultura.casadelacultura.servicio.UsuarioService;

/**
 * Controlador REST para la gestión de usuarios en la aplicación.
 */
@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {
    /**
     * Servicio para la gestión de usuarios.
     */
    @Autowired
    private UsuarioService usuarioService;

    /**
     * Codificador de contraseñas para encriptar las contraseñas de los usuarios.
     */
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Guarda un nuevo usuario en la base de datos.
     * 
     * @param usuario El objeto Usuario que se desea guardar
     * @return El usuario guardado en la base de datos
     * @throws Exception Si ocurre un error al guardar el usuario
     */
    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
        usuario.setPerfil("default.png");
        // Encripta la contraseña del usuario
        usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));

        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        // Crea un nuevo rol para el usuario
        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setRolNombre("NORMAL");

        // Asocia el rol al usuario
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        // Agrega el rol al conjunto de roles del usuario
        usuarioRoles.add(usuarioRol);
        return usuarioService.guardarUsuario(usuario,usuarioRoles);
    }


    /**
     * Obtiene un usuario por su nombre de usuario.
     * 
     * @param username Nombre de usuario a buscar
     * @return Usuario correspondiente al nombre de usuario proporcionado
     */
    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username){
        return usuarioService.obtenerUsuario(username);
    }

    /**
     * Elimina un usuario de la base de datos por su ID.
     * 
     * @param usuarioId ID del usuario a eliminar
     */
    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
        usuarioService.eliminarUsuario(usuarioId);
    }

}