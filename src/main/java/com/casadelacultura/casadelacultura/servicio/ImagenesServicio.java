package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Imagenes;
import com.casadelacultura.casadelacultura.repositorio.ImagenesRepositorio;

import lombok.*;

@AllArgsConstructor
@Service
public class ImagenesServicio {
    private final ImagenesRepositorio imagenesRepositorio;

    public Iterable<Imagenes> listarImagenes(){
        return imagenesRepositorio.findAll();
    }

    public Imagenes obtenerImagenPorId(Long idImagen){
        return imagenesRepositorio.findById(idImagen).orElse(null);
    }
    public Imagenes crearImagen(Imagenes imagenes){
        return imagenesRepositorio.save(imagenes);
    }

    public Imagenes actualizarImagen(Long idImagen, Imagenes formularioImagen){
        Imagenes imagenesFromDB = obtenerImagenPorId(idImagen);
        imagenesFromDB.setImagenPath(formularioImagen.getImagenPath());
        return imagenesRepositorio.save(formularioImagen);
    }

    public void eliminarImagen(Long idImagen){
        Imagenes imagenesFromDB = obtenerImagenPorId(idImagen);
        imagenesRepositorio.delete(imagenesFromDB);
    }
}
