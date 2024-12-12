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

import com.casadelacultura.casadelacultura.entity.Evaluaciones;
import com.casadelacultura.casadelacultura.servicio.EvaluacionesServicio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/evaluaciones")
@CrossOrigin("*")
public class EvaluacionesControlador {
    private final EvaluacionesServicio evaluacionesServicio;

    //Obtener todas las evaluciones 
    @GetMapping
    public Iterable<Evaluaciones> listarEvaluaciones(){
        return evaluacionesServicio.listarEvaluaciones();
    }

    @GetMapping("{idEvaluaciones}")
    public Evaluaciones getEvaluaciones(@PathVariable Long idEvaluaciones){
        return evaluacionesServicio.obtenerEvaluacionesPorID(idEvaluaciones);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Evaluaciones crearEvaluacion(@Valid @RequestBody Evaluaciones evaluaciones){
        return evaluacionesServicio.crearEvaluaciones(evaluaciones);
    }

    @PutMapping("{idEvaluaciones}")
    public Evaluaciones actualizarEvalucion(@PathVariable Long idEvaluaciones, @RequestBody @Valid Evaluaciones fomulario){
        return evaluacionesServicio.actualizarEvaluaciones(idEvaluaciones, fomulario);
    }

    //Eliminar 
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idEvaluaciones}")
    public void delateEvaluaciones(@PathVariable Long idEvaluaciones){
        evaluacionesServicio.eliminarEvalucion(idEvaluaciones);
    }
}
