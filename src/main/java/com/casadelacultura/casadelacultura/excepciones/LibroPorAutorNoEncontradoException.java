package com.casadelacultura.casadelacultura.excepciones;

public class LibroPorAutorNoEncontradoException extends RuntimeException{
    public LibroPorAutorNoEncontradoException(String mensaje){
        super(mensaje);
    }
}