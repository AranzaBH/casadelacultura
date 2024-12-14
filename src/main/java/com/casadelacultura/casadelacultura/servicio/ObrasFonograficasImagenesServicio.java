package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Imagenes;
import com.casadelacultura.casadelacultura.entity.ObrasFonograficas;
import com.casadelacultura.casadelacultura.entity.ObrasFonograficasImagenes;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.ObrasFonograficasImagenesRepositorio;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ObrasFonograficasImagenesServicio {
    private final ObrasFonograficasImagenesRepositorio obrasFonograficasImagenesRepositorio;
    private final ObrasFonograficasServicio obrasFonograficasServicio;
    private final ImagenesServicio imagenesServicio;

    public Iterable <ObrasFonograficasImagenes> listarObrasFonograficasImagenes(){
        return obrasFonograficasImagenesRepositorio.findAll();
    }

    public ObrasFonograficasImagenes obtenerObrasFonograficasImagenesPorId(Long idObrasFonograficasImagenes){
        return obrasFonograficasImagenesRepositorio.findById(idObrasFonograficasImagenes).orElseThrow(() -> new GlobalExceptionNoEncontrada(
                "No se encontro la relacion obras fonograficas por autor con el ID: " + idObrasFonograficasImagenes));
    }

    public ObrasFonograficasImagenes crearObrasFonograficasImagenes(ObrasFonograficasImagenes obrasFonograficasImagenes){
        //Valida si existes una relacion con la misma imagen 
        if (obrasFonograficasImagenesRepositorio.existsByObrasFonograficas_IdObrasFonograficasAndImagenes_IdImagen(obrasFonograficasImagenes.getObrasFonograficas().getIdObrasFonograficas(), 
        obrasFonograficasImagenes.getImagenes().getIdImagen())) {
            throw new GlobalExceptionNoEncontrada(
                    "Ya existe una relación \nObra fonografica ID: " + obrasFonograficasImagenes.getObrasFonograficas().getIdObrasFonograficas() +
                            "\nImagen con ID: " + obrasFonograficasImagenes.getImagenes().getIdImagen());
            
        }
        // Valida la existencia de una imagen
        Imagenes imagenes = imagenesServicio.obtenerImagenPorId(obrasFonograficasImagenes.getImagenes().getIdImagen());
        obrasFonograficasImagenes.setImagenes(imagenes);

        //vALIDA LA EXISTENCIA DE LA OBRA FONOGRAFICA
        ObrasFonograficas obrasFonograficas = obrasFonograficasServicio.obtenerObraFonograficaPorId(obrasFonograficasImagenes.getObrasFonograficas().getIdObrasFonograficas());
        obrasFonograficasImagenes.setObrasFonograficas(obrasFonograficas);

        return obrasFonograficasImagenesRepositorio.save(obrasFonograficasImagenes);
    }

    public ObrasFonograficasImagenes actualizarObrasFonograficasImagenes(Long idObrasFonograficasImagenes, ObrasFonograficasImagenes formulario){
        ObrasFonograficasImagenes obrasFIDB = obtenerObrasFonograficasImagenesPorId(idObrasFonograficasImagenes);

        if (obrasFonograficasImagenesRepositorio.existsByObrasFonograficas_IdObrasFonograficasAndImagenes_IdImagenAndIdObrasFonograficasImagenesNot(formulario.getObrasFonograficas().getIdObrasFonograficas(), formulario.getImagenes().getIdImagen(), idObrasFonograficasImagenes)) {
            throw new GlobalExceptionNoEncontrada(
                    "Ya existe una relación \nObra fonografica ID: " + formulario.getObrasFonograficas().getIdObrasFonograficas() +
                            "\nImagen con ID: " + formulario.getImagenes().getIdImagen());
            
        }

        // Valida la existencia de una imagen
        Imagenes imagenes = imagenesServicio.obtenerImagenPorId(formulario.getImagenes().getIdImagen());
        formulario.setImagenes(imagenes);

        //vALIDA LA EXISTENCIA DE LA OBRA FONOGRAFICA
        ObrasFonograficas obrasFonograficas = obrasFonograficasServicio.obtenerObraFonograficaPorId(formulario.getObrasFonograficas().getIdObrasFonograficas());
        formulario.setObrasFonograficas(obrasFonograficas);

        //Actualizr 
        obrasFIDB.setImagenes(formulario.getImagenes());
        obrasFIDB.setObrasFonograficas(formulario.getObrasFonograficas());

        return obrasFonograficasImagenesRepositorio.save(obrasFIDB);
    }

    public void eliminarObrasFonograficasImagenes(Long idObrasFonograficasImagenes){
        ObrasFonograficasImagenes obrasFIDB = obtenerObrasFonograficasImagenesPorId(idObrasFonograficasImagenes);
        obrasFonograficasImagenesRepositorio.delete(obrasFIDB);
    }
}