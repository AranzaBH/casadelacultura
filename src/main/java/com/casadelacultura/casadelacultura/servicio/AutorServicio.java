package com.casadelacultura.casadelacultura.servicio;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Autor;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
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
                .orElseThrow(() -> new GlobalExceptionNoEncontrada(
                        "No se encontro el autor con el ID " + idAutor));
    }

    // Crear un nuevo autor
    // Crear un nuevo autor
    public Autor crearAutor(Autor autor) {
        // Validar si ya existe un autor con los mismos datos
        validarFechas(autor);
        if (autorRepositorio
                .existsByNombreAutorAndApellidoPaternoAndApellidoMaternoAndFechaNacimientoAndFechaFallecimiento(
                        autor.getNombreAutor(), autor.getApellidoPaterno(), autor.getApellidoMaterno(),
                        autor.getFechaNacimiento(), autor.getFechaFallecimiento())) {
            throw new GlobalExceptionNoEncontrada("Ya existe un autor con el nombre: "
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
        validarFechas(formulario);
        // Validar si ya existe un autor con los mismos datos, excluyendo el autor
        // actual
        if (autorRepositorio
                .existsByNombreAutorAndApellidoPaternoAndApellidoMaternoAndFechaNacimientoAndFechaFallecimientoAndIdAutorNot(
                        formulario.getNombreAutor(), formulario.getApellidoPaterno(), formulario.getApellidoMaterno(),
                        formulario.getFechaNacimiento(), formulario.getFechaFallecimiento(), idAutor)) {
            throw new GlobalExceptionNoEncontrada("Ya existe un autor con el nombre: "
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

    public void validarFechas(Autor autor) {
        LocalDate hoy = LocalDate.now();
        // Validar si la fecha de nacimiento es mayor a la fecha de hoy
        if (autor.getFechaNacimiento() != null && autor.getFechaNacimiento().isAfter(hoy)) {
            throw new GlobalExceptionNoEncontrada("La fecha de nacimiento no puede ser mayor al día de hoy.");
        }
        
        // Validar si la fecha de fallecimiento es mayor a la fecha de hoy
        if (autor.getFechaFallecimiento() != null && autor.getFechaFallecimiento().isAfter(hoy)) {
            throw new GlobalExceptionNoEncontrada("La fecha de fallecimiento no puede ser mayor al día de hoy.");
        }
    }

}
