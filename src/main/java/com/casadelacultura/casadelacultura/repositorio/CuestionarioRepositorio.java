package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casadelacultura.casadelacultura.entity.Cuestionario;

public interface CuestionarioRepositorio extends JpaRepository<Cuestionario, Long> {
    boolean existsByNombreCuestionarioIgnoreCaseAndInstruccionIgnoreCase(String nombreCuestionario,
            String instruccion);

    boolean existsByNombreCuestionarioIgnoreCaseAndInstruccionIgnoreCaseAndIdCuestionarioNot(
            String nombreCuestionario, String instruccion, Long idCuestionario);
}
