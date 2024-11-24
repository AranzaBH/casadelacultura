package com.casadelacultura.casadelacultura.servicio;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.casadelacultura.casadelacultura.entity.ObrasImagenes;
import com.casadelacultura.casadelacultura.repositorio.ImagenesRepositorio;
import com.casadelacultura.casadelacultura.repositorio.ObraRepositorio;
import com.casadelacultura.casadelacultura.repositorio.ObrasImagenesRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ObrasImagenesServicio {
    private final ObrasImagenesRepositorio obrasImagenesRepositorio;
    private final ImagenesRepositorio imagenesRepositorio;
    private final ObraRepositorio obraRepositorio;

    public Iterable<ObrasImagenes> listarObrasImagenes(){
        return obrasImagenesRepositorio.findAll();
    }
    
    public ObrasImagenes obtenerRelacionPorId(Long idObrasImagenes){
        return obrasImagenesRepositorio.findById(idObrasImagenes).orElse(null);
    }


    public ObrasImagenes crearRealacionObrasImagenes(ObrasImagenes obrasImagenes){
        if (!imagenesRepositorio.existsById(obrasImagenes.getImagenes().getIdImagen())) {
            throw new IllegalArgumentException("El autor no existe.");
        }
        // Validar que la obra existe
        if (!obraRepositorio.existsById(obrasImagenes.getObra().getIdObra())) {
            throw new IllegalArgumentException("La obra no existe.");
        }
        return obrasImagenesRepositorio.save(obrasImagenes);
    }
    

    public ObrasImagenes actualizarRelacionObraImagenes(Long idObrasImagenes, ObrasImagenes formulario) {
        ObrasImagenes obrasImagenesFromDB = obtenerRelacionPorId(idObrasImagenes);
    
        if (obrasImagenesFromDB == null) {
            throw new IllegalArgumentException("La relación no existe.");
        }
    
        // Validar que la obra existe
        if (!obraRepositorio.existsById(formulario.getObra().getIdObra())) {
            throw new IllegalArgumentException("La obra no existe.");
        }
    
        // Validar que la imagen existe
        if (!imagenesRepositorio.existsById(formulario.getImagenes().getIdImagen())) {
            throw new IllegalArgumentException("La imagen no existe.");
        }
    
        // Actualizar los datos de la relación
        obrasImagenesFromDB.setObra(formulario.getObra());
        obrasImagenesFromDB.setImagenes(formulario.getImagenes());
    
        return obrasImagenesRepositorio.save(obrasImagenesFromDB);
    }
    

    public void eliminarRelacionObrasImagenes(Long idObrasImagenes){
        ObrasImagenes obrasImagenesFromDB = obtenerRelacionPorId(idObrasImagenes);
        obrasImagenesRepositorio.delete(obrasImagenesFromDB);
    }

    //Validaciones
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("Ocurrió un error interno: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
