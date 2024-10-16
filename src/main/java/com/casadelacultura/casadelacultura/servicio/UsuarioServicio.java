package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import com.casadelacultura.casadelacultura.entity.Usuario;
import com.casadelacultura.casadelacultura.repositorio.UsuarioRepositorio;
import lombok.AllArgsConstructor;

/**
 * Servicio encargado de gestionar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * para la entidad Usuario.
 * 
 * @Service Indica que esta clase es un servicio gestionado por Spring.
 * @AllArgsConstructor Se genera automáticamente un constructor con todas las dependencias.
 */
@AllArgsConstructor
@Service
public class UsuarioServicio {

    /**
     * Repositorio que permite realizar operaciones de persistencia para Usuario.
     */
    private final UsuarioRepositorio usuarioRepositorio;

    /**
     * Retorna todos los registros de Usuario almacenados en la base de datos.
     * 
     * @return Iterable<Usuario> Lista de todos los usuarios.
     */
    public Iterable<Usuario> findAll() {
        return usuarioRepositorio.findAll();
    }

    /**
     * Busca y retorna un Usuario por su ID.
     * 
     * @param idUsuario Identificador del usuario que se desea buscar.
     * @return Usuario El usuario encontrado o null si no existe.
     */
    public Usuario findById(Integer idUsuario){
        return usuarioRepositorio.findById(idUsuario).orElse(null);
    }

    /**
     * Crea un nuevo Usuario y lo guarda en la base de datos.
     * 
     * @param usuario Objeto Usuario a ser creado.
     * @return Usuario El objeto guardado en la base de datos.
     */
    public Usuario create(Usuario usuario){
        usuario.setFechaCreacion(LocalDateTime.now()); // Se le asigna una fecha de creación
        return usuarioRepositorio.save(usuario);
    }

    /**
     * Actualiza un Usuario existente en la base de datos.
     * 
     * @param idUsuario ID del usuario que se desea actualizar.
     * @param formulario Datos actualizados del Usuario.
     * @return Usuario El objeto actualizado.
     */
    public Usuario update(Integer idUsuario, Usuario formulario){
        Usuario usuarioFromDB = findById(idUsuario); // Busca el usuario existente.
        usuarioFromDB.setNombre(formulario.getNombre()); // Actualiza el nombre.
        usuarioFromDB.setApellidoMaterno(formulario.getApellidoMaterno()); // Actualiza el apellido materno.
        usuarioFromDB.setApellidoPaterno(formulario.getApellidoPaterno()); // Actualiza el apellido paterno.
        usuarioFromDB.setCorreoElectronico(formulario.getCorreoElectronico()); // Actualiza el correo electrónico.
        usuarioFromDB.setPassword(formulario.getPassword()); // Actualiza la contraseña.
        usuarioFromDB.setRol(formulario.getRol()); // Actualiza el rol.
        return usuarioRepositorio.save(usuarioFromDB);
    }

    /**
     * Elimina un Usuario por su ID.
     * 
     * @param idUsuario Identificador del usuario que se desea eliminar.
     */
    public void delete(Integer idUsuario){
        Usuario usuarioFromDB = findById(idUsuario); // Busca el usuario a eliminar.
        usuarioRepositorio.delete(usuarioFromDB);
    }
}

