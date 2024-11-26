package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Autor;
import com.casadelacultura.casadelacultura.excepciones.AutorNoEncontradoException;
import com.casadelacultura.casadelacultura.repositorio.AutorRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AutorServicio {
    private final AutorRepositorio autorRepositorio;

    // Obtener todos los autores
    public Iterable<Autor> listarAutores() {
        return autorRepositorio.findAll();
    }

    // Obtener un autor por ID
    public Autor obtenerAutorPorId(Long idAutor) {
        return autorRepositorio.findById(idAutor)
                .orElseThrow(() -> new AutorNoEncontradoException(
                        "No se encontro el autor con el ID " + idAutor));
    }

    // Crear un nuevo autor
    // Crear un nuevo autor
public Autor crearAutor(Autor autor) {
    // Validar si ya existe un autor con los mismos datos
    if (autorRepositorio.existsByNombreAutorAndApellidoPaternoAndApellidoMaternoAndFechaNacimientoAndFechaFallecimiento(
            autor.getNombreAutor(), autor.getApellidoPaterno(), autor.getApellidoMaterno(),
            autor.getFechaNacimiento(), autor.getFechaFallecimiento())) {
        throw new AutorNoEncontradoException("Ya existe un autor con el nombre: "
                + autor.getNombreAutor() + ", apellido paterno: " + autor.getApellidoPaterno()
                + ", apellido materno: " + autor.getApellidoMaterno()
                + ", fecha de nacimiento: " + autor.getFechaNacimiento()
                + " y fecha de fallecimiento: " + autor.getFechaFallecimiento());
    }
    return autorRepositorio.save(autor);
}


    // Actualizar un autor existente
    // Actualizar un autor existente
public Autor actualizarAutor(Long idAutor, Autor formulario) {
    Autor autorFromDB = obtenerAutorPorId(idAutor);

    // Validar si ya existe un autor con los mismos datos, excluyendo el autor actual
    if (autorRepositorio.existsByNombreAutorAndApellidoPaternoAndApellidoMaternoAndFechaNacimientoAndFechaFallecimientoAndIdAutorNot(
            formulario.getNombreAutor(), formulario.getApellidoPaterno(), formulario.getApellidoMaterno(),
            formulario.getFechaNacimiento(), formulario.getFechaFallecimiento(), idAutor)) {
        throw new AutorNoEncontradoException("Ya existe un autor con el nombre: "
                + formulario.getNombreAutor() + ", apellido paterno: " + formulario.getApellidoPaterno()
                + ", apellido materno: " + formulario.getApellidoMaterno()
                + ", fecha de nacimiento: " + formulario.getFechaNacimiento()
                + " y fecha de fallecimiento: " + formulario.getFechaFallecimiento());
    }

    // Actualizar los campos del autor
    autorFromDB.setNombreAutor(formulario.getNombreAutor());
    autorFromDB.setApellidoPaterno(formulario.getApellidoPaterno());
    autorFromDB.setApellidoMaterno(formulario.getApellidoMaterno());
    autorFromDB.setFechaNacimiento(formulario.getFechaNacimiento());
    autorFromDB.setFechaFallecimiento(formulario.getFechaFallecimiento());
    
    return autorRepositorio.save(autorFromDB);
}


    // Eliminar un autor por ID
    public void eliminarAutor(Long idAutor) {
        Autor autorFromDB = obtenerAutorPorId(idAutor);
        autorRepositorio.delete(autorFromDB);
    }
}
