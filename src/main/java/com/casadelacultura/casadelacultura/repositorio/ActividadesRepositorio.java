package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.casadelacultura.casadelacultura.entity.Actividades;

public interface ActividadesRepositorio extends CrudRepository<Actividades, Long> {
    @Query("SELECT a FROM Actividades a JOIN FETCH a.taller t WHERE a.idActividades = :id")
    Actividades findActividadWithTaller(@Param("id") Long id);

    @Query("SELECT a FROM Actividades a JOIN FETCH a.taller t")
    Iterable<Actividades> findAllActividadesWithTaller();

    //Recupera las actividades asociadas a un taller
    @Query("SELECT a FROM Actividades a WHERE a.taller.idTaller = :idTaller")
    Iterable<Actividades> findActividadesByTallerId(@Param("idTaller") Long idTaller);

}
