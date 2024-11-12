package com.casadelacultura.casadelacultura.configuraciones;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Clase que implementa AuthenticationEntryPoint para manejar errores de autenticación.
 * Se utiliza para interceptar solicitudes no autorizadas y responder con un error adecuado.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Este método se invoca cuando se produce una excepción de autenticación.
     * Se envía una respuesta de error con el estado HTTP 401 (No autorizado).
     * 
     * @param request La solicitud HTTP que se está procesando.
     * @param response La respuesta HTTP a enviar al cliente.
     * @param authException La excepción de autenticación que se ha producido.
     * @throws IOException Si hay un error de entrada/salida al enviar la respuesta.
     * @throws ServletException Si hay un error en la servlet.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Envía un error 401 (No autorizado) al cliente con un mensaje de error.
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Usuario no autorizado");
    }
}