package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casadelacultura.casadelacultura.entity.Tecnica;

public interface TecnicaRepositorio extends JpaRepository<Tecnica, Long> {
    boolean existsByNombreTecnicaIgnoreCase(String nombreTecnica);
    boolean existsByNombreTecnicaIgnoreCaseAndIdTecnicaNot(String nombreTecnica, Long idTecnica);
}
