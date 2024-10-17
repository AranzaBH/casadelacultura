package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.casadelacultura.casadelacultura.entity.Libro;

public interface LibroRepositorio extends CrudRepository<Libro, Integer> {
}
