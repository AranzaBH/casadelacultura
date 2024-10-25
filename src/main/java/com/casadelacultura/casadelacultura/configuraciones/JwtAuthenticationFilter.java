package com.casadelacultura.casadelacultura.configuraciones;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.casadelacultura.casadelacultura.servicio.impl.UserDetailsServiceImpl;

import java.io.IOException;

import javax.servlet.ServletException;

/**
 * Filtro de autenticación JWT que se ejecuta una vez por solicitud.
 * Este filtro se encarga de validar el token JWT presente en la cabecera 
 * de las solicitudes HTTP, autenticando al usuario si el token es válido.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtil;

    /**
     * Método que se ejecuta para filtrar la solicitud HTTP.
     * @param request La solicitud HTTP entrante.
     * @param response La respuesta HTTP a enviar.
     * @param filterChain La cadena de filtros.
     * @throws ServletException Si hay un error en la servlet.
     * @throws IOException Si hay un error de entrada/salida.
     */
    @Override
    protected void doFilterInternal(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, javax.servlet.FilterChain filterChain) throws javax.servlet.ServletException, IOException {
        // Obtener el token JWT de la cabecera de la solicitud.
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        // Verificar si el token existe y comienza con "Bearer ".
        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){
            jwtToken = requestTokenHeader.substring(7);

            try{
                // Extraer el nombre de usuario del token JWT.
                username = this.jwtUtil.extractUsername(jwtToken);
            }catch (ExpiredJwtException exception){
                // Manejar el caso en que el token ha expirado.
                System.out.println("El token ha expirado");
            }catch (Exception e){
                // Manejar otras excepciones.
                e.printStackTrace();
            }

        }else{
            // Si el token no es válido.
            System.out.println("Token invalido , no empieza con bearer string");
        }

        // Verificar si el usuario no está autenticado y se ha extraído un nombre de usuario.
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            // Cargar los detalles del usuario.
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            // Validar el token JWT.
            if(this.jwtUtil.validateToken(jwtToken,userDetails)){
                // Crear un objeto de autenticación y establecerlo en el contexto de seguridad.
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }else{
            // Manejar el caso donde el token no es válido.
            System.out.println("El token no es valido");
        }
        // Continuar con la cadena de filtros.
        filterChain.doFilter(request,response);
    }
}