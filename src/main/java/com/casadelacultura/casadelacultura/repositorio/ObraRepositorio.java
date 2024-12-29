package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.casadelacultura.casadelacultura.entity.Obra;

public interface ObraRepositorio extends JpaRepository<Obra, Long> {
    boolean existsByCodigoIgnoreCase(String codigo);
    boolean existsByCodigoIgnoreCaseAndIdObraNot(String codigo,Long idObra);
}
