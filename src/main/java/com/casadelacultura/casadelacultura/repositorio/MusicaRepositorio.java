package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.casadelacultura.casadelacultura.entity.Musica;

public interface MusicaRepositorio extends CrudRepository<Musica, Integer> {
    // Se pueden agregar métodos personalizados si es necesario
}
