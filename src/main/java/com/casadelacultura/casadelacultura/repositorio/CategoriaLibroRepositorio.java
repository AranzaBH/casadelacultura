package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.casadelacultura.casadelacultura.entity.CategoriaLibro;

public interface CategoriaLibroRepositorio extends CrudRepository<CategoriaLibro,Long> {
    boolean existsByNombreCategoriaIgnoreCase(String nombreCategoria);
    boolean existsByNombreCategoriaIgnoreCaseAndIdCategoriaLibroNot(String nombreCategoria, Long idCategoriaLibro);
}
