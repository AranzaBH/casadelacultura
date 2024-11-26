package com.casadelacultura.casadelacultura.excepciones;

public class ObrasPorAutorNoEncontradoException extends RuntimeException {
    public ObrasPorAutorNoEncontradoException(String message) {
        super(message);
    }
}