package  com.casadelacultura.casadelacultura.excepciones;

/**
 * Excepción personalizada que se lanza cuando se intenta crear un usuario que ya existe en la base de datos.
 */
public class UsuarioFoundException extends Exception{

    /**
     * Constructor por defecto que lanza un mensaje predefinido indicando que el usuario ya existe.
     */
    public UsuarioFoundException(){
        super("El usuario con ese username ya existe en la base de datos , vuelva a intentar !!");
    }

    /**
     * Constructor que permite especificar un mensaje personalizado.
     * 
     * @param mensaje Mensaje personalizado que describe el motivo de la excepción
     */
    public UsuarioFoundException(String mensaje){
        super(mensaje);
    }
}
