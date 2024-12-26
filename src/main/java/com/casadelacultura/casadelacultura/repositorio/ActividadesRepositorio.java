package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casadelacultura.casadelacultura.entity.Actividades;

public interface ActividadesRepositorio extends JpaRepository<Actividades, Long> {
        /* 
    @Query("SELECT a FROM Actividades a JOIN FETCH a.taller t WHERE a.idActividades = :id")
    Actividades findActividadWithTaller(@Param("id") Long id);

    @Query("SELECT a FROM Actividades a JOIN FETCH a.taller t")
    Iterable<Actividades> findAllActividadesWithTaller();*/

    // Recupera las actividades asociadas a un taller
    @Query("SELECT a FROM Actividades a WHERE a.taller.idTaller = :idTaller")
    Iterable<Actividades> findActividadesByTallerId(@Param("idTaller") Long idTaller);

    boolean existsByNombreActividadIgnoreCaseAndVideo_IdVideoAndCuestionario_IdCuestionarioAndTaller_IdTaller(
            String nombreActividad,
            Long idVideo,
            Long idCuestionario,
            Long idTaller);

    boolean existsByNombreActividadIgnoreCaseAndVideo_IdVideoAndCuestionario_IdCuestionarioAndTaller_IdTallerAndIdActividadesNot(
            String nombreActividad,
            Long idVideo,
            Long idCuestionario,
            Long idTaller,
            Long idActividades);
}
