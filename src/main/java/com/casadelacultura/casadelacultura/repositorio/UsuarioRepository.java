package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casadelacultura.casadelacultura.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    public Usuario findByUsername(String username);

}
