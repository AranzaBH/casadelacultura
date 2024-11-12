package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.casadelacultura.casadelacultura.entity.LibrosPorAutor;

public interface LibrosPorAutorRepositorio extends CrudRepository<LibrosPorAutor, Long> {
}
