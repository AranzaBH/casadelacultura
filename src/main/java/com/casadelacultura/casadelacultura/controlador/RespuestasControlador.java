package com.casadelacultura.casadelacultura.controlador;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Respuestas;
import com.casadelacultura.casadelacultura.servicio.RespuestasServicio;
import lombok.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/respuesta")
@CrossOrigin("*")
public class RespuestasControlador {
    private final RespuestasServicio respuestasServicio;

    @GetMapping
    public List<Respuestas> listarRespuestas(){
        return respuestasServicio.listarRespuestas();
    }

    @GetMapping("{idRespuesta}")
    public Respuestas obtenerRespuestaPorId(@PathVariable Long idRespuesta){
        return respuestasServicio.obtenerRespuestaPorId(idRespuesta);
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Respuestas crearRespuestas(@Valid @RequestBody Respuestas respuestas){
        return respuestasServicio.crearRespuestas(respuestas);
    }
    
    @PutMapping("{idRespuesta}")
    public Respuestas actualizarRespueta(@PathVariable Long idRespuesta, @RequestBody @Valid Respuestas formulario){
        return respuestasServicio.actualizarRespuesta(idRespuesta, formulario);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idRespuesta}")
    public void eliminarRespuesta(@PathVariable Long idRespuesta){
        respuestasServicio.eliminarRespuesta(idRespuesta);
    }
}