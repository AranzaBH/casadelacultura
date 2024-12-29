package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.casadelacultura.casadelacultura.entity.CategoriaObra;

public interface CategoriaObraRepositorio  extends JpaRepository<CategoriaObra, Long> {
    boolean existsByNombreCategoriaIgnoreCase(String nombreCategoria);
    boolean existsByNombreCategoriaIgnoreCaseAndIdCategoriaObraNot(String nombreCategoria, Long idCategoriaObra);
}
