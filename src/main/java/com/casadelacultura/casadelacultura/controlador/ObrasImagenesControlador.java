package com.casadelacultura.casadelacultura.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.casadelacultura.casadelacultura.entity.ObrasImagenes;
import com.casadelacultura.casadelacultura.servicio.ObrasImagenesServicio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/obrasimagenes")
@CrossOrigin("*")
public class ObrasImagenesControlador {
    private final ObrasImagenesServicio obrasImagenesServicio;

    @GetMapping
    public Iterable<ObrasImagenes> listarObraImagenes(){
        return obrasImagenesServicio.listarObrasImagenes();
    }

    @GetMapping("{idObrasImagenes}")
    public ObrasImagenes getId(@PathVariable Long idObrasImagenes){
        return obrasImagenesServicio.obtenerRelacionPorId(idObrasImagenes);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ObrasImagenes create (@RequestBody ObrasImagenes obrasImagenes){
        return obrasImagenesServicio.crearRealacionObrasImagenes(obrasImagenes);
    }

    // Actualizar una relación existente
    @PutMapping("{idObrasImagenes}")
    public ObrasImagenes update(@PathVariable Long idObrasImagenes, @RequestBody ObrasImagenes formulario){
        return obrasImagenesServicio.actualizarRelacionObraImagenes(idObrasImagenes, formulario);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idObrasImagenes}")
    public void delete (@PathVariable Long idObrasImagenes){
        obrasImagenesServicio.eliminarRelacionObrasImagenes(idObrasImagenes);
    }


}
