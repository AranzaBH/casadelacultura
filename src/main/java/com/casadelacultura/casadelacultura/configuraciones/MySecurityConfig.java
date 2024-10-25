package com.casadelacultura.casadelacultura.configuraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.casadelacultura.casadelacultura.servicio.impl.UserDetailsServiceImpl;

/**
 * Clase de configuración de seguridad para la aplicación.
 * Esta clase se encarga de definir las configuraciones de seguridad
 * incluyendo la autenticación y autorización de usuarios.
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Manejador de autenticación para manejar excepciones no autorizadas.
     */
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    /**
     * Servicio de detalles del usuario para la autenticación.
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    /**
     * Filtro de autenticación JWT para validar los tokens en las solicitudes.
     */
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Bean para el manejador de autenticación.
     * 
     * @return El manejador de autenticación
     * @throws Exception Si ocurre un error al crear el bean
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Bean para el codificador de contraseñas.
     * 
     * @return El codificador de contraseñas BCrypt
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura el manejador de autenticación para usar el servicio de detalles del
     * usuario y el codificador de contraseñas.
     * 
     * @param auth El objeto AuthenticationManagerBuilder para configurar la
     *             autenticación
     * @throws Exception Si ocurre un error al configurar la autenticación
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    /**
     * Configura la seguridad HTTP, incluyendo la autorización de solicitudes y la
     * gestión de sesiones.
     * 
     * @param http El objeto HttpSecurity para configurar la seguridad
     * @throws Exception Si ocurre un error al configurar la seguridad
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable() // Desactiva la protección CSRF
                .cors()
                .disable() // Desactiva el CORS
                .authorizeRequests()
                .antMatchers("/generate-token", "/usuarios/").permitAll() // Permite acceso sin autenticación a estas rutas
                .antMatchers(HttpMethod.OPTIONS).permitAll() // Permite solicitudes OPTIONS sin autenticación
                .anyRequest().authenticated() // Requiere autenticación para cualquier otra solicitud
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler) // Maneja excepciones dea utenticación
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Configura la gestión de sesiones como sin estado

        // Agrega el filtro de autenticación JWT antes del filtro de autenticación de
        // nombre de usuario y contraseña
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
