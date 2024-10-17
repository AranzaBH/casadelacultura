package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Usuario;
import com.casadelacultura.casadelacultura.repositorio.UsuarioRepositorio;

import java.util.Optional;

// Controlador para manejar las operaciones CRUD de Usuario
@RestController
@RequestMapping("/api/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<Iterable<Usuario>> list() {
        return ResponseEntity.ok(usuarioRepositorio.findAll());
    }

    // Obtener un usuario por su ID
    @GetMapping("{idUsuario}")
    public ResponseEntity<Usuario> get(@PathVariable Integer idUsuario) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(idUsuario);
        return usuario.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo usuario
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    // Actualizar un usuario existente
    @PutMapping("{idUsuario}")
    public ResponseEntity<Usuario> update(@PathVariable Integer idUsuario, @RequestBody Usuario formulario) {
        Optional<Usuario> optionalUsuario = usuarioRepositorio.findById(idUsuario);
        if (optionalUsuario.isPresent()) {
            Usuario usuarioFromDB = optionalUsuario.get();
            usuarioFromDB.setNombre(formulario.getNombre());
            usuarioFromDB.setApellidoMaterno(formulario.getApellidoMaterno());
            usuarioFromDB.setApellidoPaterno(formulario.getApellidoPaterno());
            usuarioFromDB.setCorreoElectronico(formulario.getCorreoElectronico());
            usuarioFromDB.setPassword(formulario.getPassword());
            return ResponseEntity.ok(usuarioRepositorio.save(usuarioFromDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Usuario no encontrado
    }

    // Eliminar un usuario
    @DeleteMapping("{idUsuario}")
    public ResponseEntity<Void> delete(@PathVariable Integer idUsuario) {
        Optional<Usuario> optionalUsuario = usuarioRepositorio.findById(idUsuario);
        if (optionalUsuario.isPresent()) {
            usuarioRepositorio.delete(optionalUsuario.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Eliminación exitosa
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Usuario no encontrado
    }
}
