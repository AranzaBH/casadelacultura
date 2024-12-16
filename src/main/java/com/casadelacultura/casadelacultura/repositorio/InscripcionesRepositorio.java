package com.casadelacultura.casadelacultura.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.casadelacultura.casadelacultura.entity.Inscripciones;

public interface InscripcionesRepositorio extends CrudRepository<Inscripciones, Long> {
    @Query("SELECT i FROM Inscripciones i WHERE i.usuario.id = :idUsuario")
    Iterable<Inscripciones> findByUsuarioId(Long idUsuario);

    boolean existsByUsuario_IdAndTaller_IdTaller(Long id, Long idTaller);

    boolean existsByUsuario_IdAndTaller_IdTallerAndIdInscripcionesNot(Long id, Long idTaller, Long idInscripciones);

    // busque la inscripción según el idUsuario y el idTaller.
    @Query("SELECT i FROM Inscripciones i WHERE i.usuario.id = :idUsuario AND i.taller.idTaller = :idTaller")
    List<Inscripciones> findByUsuarioIdAndTallerId(Long idUsuario, Long idTaller);
}
