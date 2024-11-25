package com.casadelacultura.casadelacultura.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Actividades;
import com.casadelacultura.casadelacultura.servicio.ActividadesServicio;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/actividades")
@CrossOrigin("*")
public class ActividadesControlador {
    private final ActividadesServicio actividadesServicio;

    // Obtener todas las actividades
    @GetMapping
    public Iterable<Actividades> obtenerTodasLasActividades() {
        return actividadesServicio.listarActividades();
    }

    // Obtener una actividad por su ID
    @GetMapping("{idActividad}")
    public Actividades obtenerActividadPorId(@PathVariable Long idActividad) {
        return actividadesServicio.obtenerActividadPorId(idActividad);
    }

    // Crear una nueva actividad
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Actividades crearActividad(@RequestBody Actividades actividad) {
        return actividadesServicio.crearActividad(actividad);
    }

    // Actualizar una actividad existente
    @PutMapping("{idActividad}")
    public Actividades actualizarActividad(@PathVariable Long idActividad, @RequestBody Actividades formulario) {
        return actividadesServicio.actualizarActividad(idActividad, formulario);
    }

    // Eliminar una actividad por su ID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idActividad}")
    public void eliminarActividad(@PathVariable Long idActividad) {
        actividadesServicio.eliminarActividad(idActividad);
    }

    // Obtener actividades por id del taller
    @GetMapping("/taller/{idTaller}")
    public List<Actividades> obtenerActividadesPorTaller(@PathVariable Long idTaller) {
        // Llama al servicio para obtener las actividades asociadas al taller
        return actividadesServicio.listarActividadesPorTaller(idTaller);
    }

}
