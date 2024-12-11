package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.casadelacultura.casadelacultura.entity.CategoriaObra;

public interface CategoriaObraRepositorio  extends CrudRepository<CategoriaObra, Long> {
    boolean existsByNombreCategoriaIgnoreCase(String nombreCategoria);
    boolean existsByNombreCategoriaIgnoreCaseAndIdCategoriaObraNot(String nombreCategoria, Long idCategoriaObra);
}
