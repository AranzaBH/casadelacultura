package com.casadelacultura.casadelacultura.repositorio;

import com.casadelacultura.casadelacultura.entity.Auditoria;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AuditoriaRepositorio extends CrudRepository<Auditoria, Long> {
    List<Auditoria> findByEntidad(String entidad);

    List<Auditoria> findByAccion(String accion);

    List<Auditoria> findByEntidadAndAccion(String entidad, String accion);

    List<Auditoria> findByAccionAndFechaHoraBetween(String accion, LocalDateTime inicio, LocalDateTime fin);

    List<Auditoria> findByEntidadAndAccionAndFechaHoraBetween(String entidad, String accion, LocalDateTime inicio,
            LocalDateTime fin);

    List<Auditoria> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
}
