package com.casadelacultura.casadelacultura.controlador;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.casadelacultura.casadelacultura.entity.LibrosImagenes;
import com.casadelacultura.casadelacultura.servicio.LibrosImagenesServicio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/librosimagenes")
@CrossOrigin("*")
public class LibrosImagenesControlador {
    private final LibrosImagenesServicio librosImagenesServicio;

    @GetMapping
    public Iterable <LibrosImagenes> listarLibrosImagenes(){
        return librosImagenesServicio.listarLibrosImagenes();
    }

    @GetMapping("{idLibrosImagenes}")
    public LibrosImagenes getLibrosImgenesId(@PathVariable Long idLibrosImagenes){
        return librosImagenesServicio.obtenerRelacionPorId(idLibrosImagenes);
    }


    //Crear relacion 
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public LibrosImagenes createRelacionLibroImagen(@Valid @RequestBody LibrosImagenes librosImagenes){
        return librosImagenesServicio.crearRealicionLibroImagen(librosImagenes);
    }

    //actualiza relacion
    @PutMapping("{idLibrosImagenes}")
    public LibrosImagenes actualizarRelacionLibroImgane(@PathVariable Long idLibrosImagenes, @RequestBody @Valid LibrosImagenes formulario){
        return librosImagenesServicio.actualizarRelacionLiborImagen(idLibrosImagenes, formulario);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping("{idLibrosImagenes}")
    public void delate(@PathVariable Long idLibrosImagenes){
        librosImagenesServicio.eliminarRelacionImagenLibro(idLibrosImagenes);
    }
}
