package com.casadelacultura.casadelacultura.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidad Usuario que representa la tabla "usuario" en la base de datos.
 * Implementa la interfaz UserDetails para ser usada en autenticación y 
 * autorización mediante Spring Security.
 */
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {
    /**
     * Identificador único del usuario, autogenerado.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    /**
     * Estado de habilitación del usuario; si es true, el usuario está habilitado.
     */
    private boolean enabled = true;
    private String perfil;

    /**
     * Roles asociados al usuario. Se ignoran en la serialización JSON para evitar bucles.
     */
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "usuario")
    @JsonIgnore
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

    /**
     * Constructor sin argumentos.
     */
    public Usuario(){

    }

    /**
     * Constructor con todos los atributos.
     * 
     * @param id Identificador único del usuario
     * @param username Nombre de usuario
     * @param password Contraseña del usuario
     * @param nombre Nombre real del usuario
     * @param apellido Apellido del usuario
     * @param email Correo electrónico del usuario
     * @param telefono Número de teléfono del usuario
     * @param enabled Estado de habilitación del usuario
     * @param perfil Rol o perfil del usuario en el sistema
     */
    public Usuario(Long id, String username, String password, String nombre, String apellido, String email, String telefono, boolean enabled, String perfil) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.enabled = enabled;
        this.perfil = perfil;
    }

    /**
     * Obtiene el ID del usuario.
     * 
     * @return ID del usuario
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del usuario.
     * 
     * @param id ID del usuario
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de usuario.
     * 
     * @return Nombre de usuario
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     * 
     * @param username Nombre de usuario
     */
    public void setUsername(String username) {
        this.username = username;
    }

    

    

    /**
     * Devuelve la lista de autoridades del usuario, basada en sus roles.
     * 
     * @return Colección de GrantedAuthority asociadas al usuario
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> autoridades = new HashSet<>();
        this.usuarioRoles.forEach(usuarioRol -> {
            autoridades.add(new Authority(usuarioRol.getRol().getRolNombre()));
        });
        return autoridades;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return Contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param password Contraseña del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el nombre del usuario.
     * 
     * @return Nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * 
     * @param nombre Nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido del usuario.
     * 
     * @return Apellido del usuario
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del usuario.
     * 
     * @param apellido Apellido del usuario
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     * 
     * @return Correo electrónico del usuario
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     * 
     * @param email Correo electrónico del usuario
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el número de teléfono del usuario.
     * 
     * @return Número de teléfono del usuario
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del usuario.
     * 
     * @param telefono Número de teléfono del usuario
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Indica si el usuario está habilitado.
     * 
     * @return true si el usuario está habilitado; de lo contrario, false
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Establece el estado de habilitación del usuario.
     * 
     * @param enabled Estado de habilitación del usuario
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Obtiene la foto de perfil del usuario.
     * 
     * @return Foto de perfil del usuario
     */
    public String getPerfil() {
        return perfil;
    }

    /**
     * Establece foto de perfil del usuario.
     * 
     * @param perfil Foto de perfil del usuario
     */
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    /**
     * Obtiene los roles asociados al usuario.
     * 
     * @return Conjunto de roles de usuario
     */
    public Set<UsuarioRol> getUsuarioRoles() {
        return usuarioRoles;
    }

    /**
     * Establece los roles asociados al usuario.
     * 
     * @param usuarioRoles Conjunto de roles de usuario
     */
    public void setUsuarioRoles(Set<UsuarioRol> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }


    // Métodos de la interfaz UserDetails

    /**
     * Indica si la cuenta del usuario no ha expirado.
     * 
     * @return true, si la cuenta no ha expirado
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica si la cuenta del usuario no está bloqueada.
     * 
     * @return true, si la cuenta no está bloqueada
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica si las credenciales del usuario no han expirado.
     * 
     * @return true, si las credenciales no han expirado
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}