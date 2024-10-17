package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.casadelacultura.casadelacultura.entity.Material;

public interface MaterialRepositorio extends CrudRepository<Material, Integer> {
}
