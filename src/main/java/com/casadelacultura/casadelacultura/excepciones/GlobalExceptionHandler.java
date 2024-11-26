package com.casadelacultura.casadelacultura.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejar la excepción de validación de campos
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }

    // Manejar la excepción de libro no encontrado
    @ExceptionHandler(LibroNoEncontradoException.class)
    public ResponseEntity<String> manejarLibroNoEncontrado(LibroNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //Manejar la exepcion de libro por autor no encontrado
    @ExceptionHandler(LibroPorAutorNoEncontradoException.class)
    public ResponseEntity<String> manejarLibroPorAutorNoEncontrado(LibroPorAutorNoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //Manejar la exepcion de obra por autor no encontrado 
    @ExceptionHandler(ObrasPorAutorNoEncontradoException.class)
    public ResponseEntity<String> manejarObraPorAutorNoEncontrado(ObrasPorAutorNoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //Manejar la exepcion del autor no encontrado
    @ExceptionHandler(AutorNoEncontradoException.class)
    public ResponseEntity<String> manejarAutorNoEncontrado(AutorNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //Menejar la Exepcion de obra no encontrada
    @ExceptionHandler(ObraNoEncontradoException.class)
    public ResponseEntity<String> manejarObraNoEncontrada(ObraNoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}