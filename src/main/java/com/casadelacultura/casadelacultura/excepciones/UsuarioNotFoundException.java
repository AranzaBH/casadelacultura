package com.casadelacultura.casadelacultura.excepciones;

/**
 * Excepción personalizada que se lanza cuando se intenta buscar un usuario que no existe en la base de datos.
 */
public class UsuarioNotFoundException extends Exception {

    /**
     * Constructor por defecto que lanza un mensaje predefinido indicando que el usuario no existe.
     */
    public UsuarioNotFoundException() {
        super("El usuario con ese username no existe en la base de datos, vuelva a intentar!!");
    }

    /**
     * Constructor que permite especificar un mensaje personalizado.
     * 
     * @param mensaje Mensaje personalizado que describe el motivo de la excepción
     */
    public UsuarioNotFoundException(String mensaje) {
        super(mensaje);
    }
}

