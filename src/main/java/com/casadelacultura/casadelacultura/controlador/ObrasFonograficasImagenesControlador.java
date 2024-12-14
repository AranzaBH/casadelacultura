package com.casadelacultura.casadelacultura.controlador;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.casadelacultura.casadelacultura.entity.ObrasFonograficasImagenes;
import com.casadelacultura.casadelacultura.servicio.ObrasFonograficasImagenesServicio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/obrasfonograficasimagenes")
@CrossOrigin("*")
public class ObrasFonograficasImagenesControlador {
    private final ObrasFonograficasImagenesServicio obrasFonograficasImagenesServicio;

    @GetMapping
    public Iterable<ObrasFonograficasImagenes> listarObrasFonograficasImagenes(){
        return obrasFonograficasImagenesServicio.listarObrasFonograficasImagenes();
    }

    @GetMapping("{idObrasFonograficasImagenes}")
    public ObrasFonograficasImagenes obtenerObrasFonograficasImagenesPorId(@PathVariable Long idObrasFonograficasImagenes){
        return obrasFonograficasImagenesServicio.obtenerObrasFonograficasImagenesPorId(idObrasFonograficasImagenes);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ObrasFonograficasImagenes crearObrasFonograficasImagenes(@Valid @RequestBody ObrasFonograficasImagenes obrasFonograficasImagenes){
        return obrasFonograficasImagenesServicio.crearObrasFonograficasImagenes(obrasFonograficasImagenes);
    }

    @PutMapping("{idObrasFonograficasImagenes}")
    public ObrasFonograficasImagenes actualizarObrasFonograficasImagenes(@PathVariable Long idObrasFonograficasImagenes, @RequestBody @Valid ObrasFonograficasImagenes formulario ){
        return obrasFonograficasImagenesServicio.actualizarObrasFonograficasImagenes(idObrasFonograficasImagenes, formulario);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idObrasFonograficasImagenes}")
    public void eliminarObrasFonograficasImagenes(@PathVariable Long idObrasFonograficasImagenes){
        obrasFonograficasImagenesServicio.eliminarObrasFonograficasImagenes(idObrasFonograficasImagenes);
    }

}
