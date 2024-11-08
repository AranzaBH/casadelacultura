package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.casadelacultura.casadelacultura.entity.Autor;

public interface AutorRepositorio extends CrudRepository<Autor, Long> {
    
    
}
