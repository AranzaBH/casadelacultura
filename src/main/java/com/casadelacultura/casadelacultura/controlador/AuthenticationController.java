package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.casadelacultura.casadelacultura.configuraciones.JwtUtils;
import com.casadelacultura.casadelacultura.entity.JwtRequest;
import com.casadelacultura.casadelacultura.entity.JwtResponse;
import com.casadelacultura.casadelacultura.entity.Usuario;
import com.casadelacultura.casadelacultura.excepciones.UsuarioNotFoundException;
import com.casadelacultura.casadelacultura.servicio.impl.UserDetailsServiceImpl;

import java.security.Principal;
/**
 * Controlador para gestionar la autenticación de usuarios y la generación de tokens JWT.
 * Proporciona endpoints para la autenticación y obtención de información del usuario actual.
 */
@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * Endpoint para generar un token JWT.
     * 
     * @param jwtRequest Contiene el nombre de usuario y la contraseña del usuario que intenta autenticarse.
     * @return Un ResponseEntity que contiene el token JWT generado en caso de éxito.
     * @throws Exception Si ocurre un error durante la autenticación o si el usuario no se encuentra.
     */
    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try{
            autenticar(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch (UsuarioNotFoundException exception){
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails =  this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    /**
     * Método privado para autenticar al usuario utilizando el AuthenticationManager.
     * 
     * @param username Nombre de usuario del usuario que intenta autenticarse.
     * @param password Contraseña del usuario que intenta autenticarse.
     * @throws Exception Si el usuario está deshabilitado o si las credenciales son inválidas.
     */
    private void autenticar(String username,String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException exception){
            throw  new Exception("USUARIO DESHABILITADO " + exception.getMessage());
        }catch (BadCredentialsException e){
            throw  new Exception("Credenciales invalidas " + e.getMessage());
        }
    }

    /**
     * Endpoint para obtener el usuario actual autenticado.
     * 
     * @param principal Contiene la información del usuario actual autenticado.
     * @return El objeto Usuario correspondiente al usuario autenticado.
     */
    @GetMapping("/actual-usuario")
    public Usuario obtenerUsuarioActual(Principal principal){
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
    }
}