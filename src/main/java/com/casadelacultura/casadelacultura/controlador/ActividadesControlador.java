package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.casadelacultura.casadelacultura.entity.Actividades;
import com.casadelacultura.casadelacultura.servicio.ActividadesServicio;

import java.util.Optional;

@RestController
@RequestMapping("/actividades")
public class ActividadesControlador {

    @Autowired
    private ActividadesServicio actividadesServicio;

    // Obtener todas las actividades
    @GetMapping
    public ResponseEntity<Iterable<Actividades>> obtenerTodasLasActividades() {
        Iterable<Actividades> actividades = actividadesServicio.listarActividades();
        return new ResponseEntity<>(actividades, HttpStatus.OK);
    }

    // Obtener una actividad por su ID
    @GetMapping("/{idActividad}")
    public ResponseEntity<Actividades> obtenerActividadPorId(@PathVariable Long idActividad) {
        Optional<Actividades> actividad = actividadesServicio.obtenerActividadPorId(idActividad);
        return actividad.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva actividad
    @PostMapping
    public ResponseEntity<Actividades> crearActividad(@RequestBody Actividades actividad) {
        Actividades nuevaActividad = actividadesServicio.crearActividad(actividad);
        return new ResponseEntity<>(nuevaActividad, HttpStatus.CREATED);
    }

    // Actualizar una actividad existente
    @PutMapping("/{idActividad}")
    public ResponseEntity<Actividades> actualizarActividad(@PathVariable Long idActividad, @RequestBody Actividades formulario) {
        Optional<Actividades> actividadActualizada = actividadesServicio.actualizarActividad(idActividad, formulario);
        return actividadActualizada.map(ResponseEntity::ok)
                                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar una actividad por su ID
    @DeleteMapping("/{idActividad}")
    public ResponseEntity<Void> eliminarActividad(@PathVariable Long idActividad) {
        boolean eliminado = actividadesServicio.eliminarActividad(idActividad);
        return eliminado ? ResponseEntity.noContent().build()
                         : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
