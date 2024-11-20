package com.casadelacultura.casadelacultura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.casadelacultura.casadelacultura.entity.Rol;
import com.casadelacultura.casadelacultura.entity.Usuario;
import com.casadelacultura.casadelacultura.entity.UsuarioRol;
import com.casadelacultura.casadelacultura.servicio.UsuarioService;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CasadelaculturaApplication implements CommandLineRunner {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(CasadelaculturaApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        
        
        Usuario usuario = new Usuario();

        usuario.setNombre("administrador");
        usuario.setApellido("Ramirez");
        usuario.setUsername("administrador");
        usuario.setPassword(bCryptPasswordEncoder.encode("administrador"));
        usuario.setEmail("damianbautista@gmail.com");
        usuario.setTelefono("9531166328");
        usuario.setPerfil("foto.png");

        Rol rol = new Rol();
        rol.setRolId(1L);
        rol.setRolNombre("ADMIN");

        Set<UsuarioRol> usuariosRoles = new HashSet<>();
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setRol(rol);
        usuarioRol.setUsuario(usuario);
        usuariosRoles.add(usuarioRol);

        Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario, usuariosRoles);
        System.out.println(usuarioGuardado.getUsername());
        
        
    }
}