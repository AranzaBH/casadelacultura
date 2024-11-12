package com.casadelacultura.casadelacultura.configuraciones;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Clase utilitaria para manejar la creación, validación y extracción de información de tokens JWT.
 * Esta clase contiene métodos para generar tokens, validar tokens y extraer información
 * del token como el nombre de usuario y la fecha de expiración.
 */
@Component
public class JwtUtils {

    private String SECRET_KEY = "examportal";// Clave secreta para firmar los tokens

    /**
     * Extrae el nombre de usuario del token JWT.
     *
     * @param token El token JWT del cual se extraerá el nombre de usuario.
     * @return El nombre de usuario extraído del token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrae la fecha de expiración del token JWT.
     *
     * @param token El token JWT del cual se extraerá la fecha de expiración.
     * @return La fecha de expiración del token.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrae una reclamación del token JWT usando un resolver de reclamaciones.
     *
     * @param token          El token JWT del cual se extraerá la reclamación.
     * @param claimsResolver Función que define cómo extraer la reclamación.
     * @param <T>           El tipo de la reclamación que se extraerá.
     * @return El valor de la reclamación extraída.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    /**
     * Extrae todas las reclamaciones del token JWT.
     *
     * @param token El token JWT del cual se extraerán las reclamaciones.
     * @return Las reclamaciones extraídas del token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /**
     * Verifica si el token JWT ha expirado.
     *
     * @param token El token JWT que se va a verificar.
     * @return Verdadero si el token ha expirado, falso de lo contrario.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Genera un nuevo token JWT para el usuario especificado.
     *
     * @param userDetails Detalles del usuario para el cual se generará el token.
     * @return El token JWT generado.
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Crea un token JWT utilizando los reclamos y el sujeto especificado.
     *
     * @param claims  Reclamaciones a incluir en el token.
     * @param subject El sujeto (nombre de usuario) del token.
     * @return El token JWT creado.
     */
    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    /**
     * Valida un token JWT comparando el nombre de usuario y verificando su fecha de expiración.
     *
     * @param token       El token JWT que se va a validar.
     * @param userDetails Detalles del usuario para comparar con el token.
     * @return Verdadero si el token es válido, falso de lo contrario.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}