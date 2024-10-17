package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.casadelacultura.casadelacultura.entity.Usuario;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer> {
}
