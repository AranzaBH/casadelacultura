package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Auditoria;
import com.casadelacultura.casadelacultura.repositorio.AuditoriaRepositorio;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/auditoria")
public class AuditoriaControlador {

    @Autowired
    private AuditoriaRepositorio auditoriaRepositorio;

    // Listar todas las auditorías
    @GetMapping
    public List<Auditoria> obtenerTodasLasAuditorias() {
        return (List<Auditoria>) auditoriaRepositorio.findAll();
    }

    // Filtrar auditorías por entidad
    @GetMapping("/entidad/{entidad}")
    public List<Auditoria> obtenerAuditoriasPorEntidad(@PathVariable String entidad) {
        return auditoriaRepositorio.findByEntidad(entidad);
    }

    // Filtrar auditorías por acción
    @GetMapping("/accion/{accion}")
    public List<Auditoria> obtenerAuditoriasPorAccion(@PathVariable String accion) {
        return auditoriaRepositorio.findByAccion(accion);
    }

    // Obtener detalles de una auditoría específica por ID
    @GetMapping("/{id}")
    public Auditoria obtenerAuditoriaPorId(@PathVariable Long id) {
        return auditoriaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Auditoría no encontrada con ID: " + id));
    }

    // Filtrar auditorías por entidad y acción
    @GetMapping("/entidad/{entidad}/accion/{accion}")
    public List<Auditoria> obtenerAuditoriasPorEntidadYAccion(
            @PathVariable String entidad,
            @PathVariable String accion) {
        return auditoriaRepositorio.findByEntidadAndAccion(entidad, accion);
    }

    // Filtrar actualizaciones realizadas sobre una entidad
    @GetMapping("/entidad/{entidad}/actualizaciones")
    public List<Auditoria> obtenerActualizacionesPorEntidad(@PathVariable String entidad) {
        return auditoriaRepositorio.findByEntidadAndAccion(entidad, "ACTUALIZAR");
    }

    // Filtrar eliminaciones realizadas sobre una entidad
    @GetMapping("/entidad/{entidad}/eliminaciones")
    public List<Auditoria> obtenerEliminacionesPorEntidad(@PathVariable String entidad) {
        return auditoriaRepositorio.findByEntidadAndAccion(entidad, "ELIMINAR");
    }

    // Filtrar creaciones realizadas sobre una entidad
    @GetMapping("/entidad/{entidad}/creaciones")
    public List<Auditoria> obtenerCreacionesPorEntidad(@PathVariable String entidad) {
        return auditoriaRepositorio.findByEntidadAndAccion(entidad, "CREAR");
    }
    /*
     * Listar por fehcas
     */

    // Listar auditorías por acción y rango de fechas
    @GetMapping("/accion/{accion}/fechas")
    public List<Auditoria> obtenerAuditoriasPorAccionYFechas(
            @PathVariable String accion,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        LocalDateTime inicio = LocalDateTime.parse(fechaInicio);
        LocalDateTime fin = LocalDateTime.parse(fechaFin);
        return auditoriaRepositorio.findByAccionAndFechaHoraBetween(accion, inicio, fin);
    }

    // Listar auditorías por entidad, acción y rango de fechas
    @GetMapping("/entidad/{entidad}/accion/{accion}/fechas")
    public List<Auditoria> obtenerAuditoriasPorEntidadAccionYFechas(
            @PathVariable String entidad,
            @PathVariable String accion,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        LocalDateTime inicio = LocalDateTime.parse(fechaInicio);
        LocalDateTime fin = LocalDateTime.parse(fechaFin);
        return auditoriaRepositorio.findByEntidadAndAccionAndFechaHoraBetween(entidad, accion, inicio, fin);
    }

    // Listar creaciones por entidad y rango de fechas
    @GetMapping("/entidad/{entidad}/creaciones/fechas")
    public List<Auditoria> obtenerCreacionesPorEntidadYFechas(
            @PathVariable String entidad,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        LocalDateTime inicio = LocalDateTime.parse(fechaInicio);
        LocalDateTime fin = LocalDateTime.parse(fechaFin);
        return auditoriaRepositorio.findByEntidadAndAccionAndFechaHoraBetween(entidad, "CREAR", inicio, fin);
    }

    // Listar actualizaciones por entidad y rango de fechas
    @GetMapping("/entidad/{entidad}/actualizaciones/fechas")
    public List<Auditoria> obtenerActualizacionesPorEntidadYFechas(
            @PathVariable String entidad,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        LocalDateTime inicio = LocalDateTime.parse(fechaInicio);
        LocalDateTime fin = LocalDateTime.parse(fechaFin);
        return auditoriaRepositorio.findByEntidadAndAccionAndFechaHoraBetween(entidad, "ACTUALIZAR", inicio, fin);
    }

    // Listar eliminaciones por entidad y rango de fechas
    @GetMapping("/entidad/{entidad}/eliminaciones/fechas")
    public List<Auditoria> obtenerEliminacionesPorEntidadYFechas(
            @PathVariable String entidad,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        LocalDateTime inicio = LocalDateTime.parse(fechaInicio);
        LocalDateTime fin = LocalDateTime.parse(fechaFin);
        return auditoriaRepositorio.findByEntidadAndAccionAndFechaHoraBetween(entidad, "ELIMINAR", inicio, fin);
    }

    // Listar todas las auditorías en un rango de fechas
    @GetMapping("/fechas")
    public List<Auditoria> obtenerAuditoriasPorFechas(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        LocalDateTime inicio = LocalDateTime.parse(fechaInicio);
        LocalDateTime fin = LocalDateTime.parse(fechaFin);
        return auditoriaRepositorio.findByFechaHoraBetween(inicio, fin);
    }
}
