package com.casadelacultura.casadelacultura.repositorio;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;

import com.casadelacultura.casadelacultura.entity.Autor;

public interface AutorRepositorio extends CrudRepository<Autor, Long> {
    // Método para verificar si un autor con el mismo nombre y apellido paterno ya existe
    // Validar si ya existe un autor con los mismos datos
    boolean existsByNombreAutorAndApellidoPaternoAndApellidoMaternoAndFechaNacimientoAndFechaFallecimiento(
        String nombreAutor, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, LocalDate fechaFallecimiento);

    // Validar si ya existe un autor con los mismos datos, excluyendo el autor actual
    boolean existsByNombreAutorAndApellidoPaternoAndApellidoMaternoAndFechaNacimientoAndFechaFallecimientoAndIdAutorNot(
        String nombreAutor, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, LocalDate fechaFallecimiento, Long idAutor);

}
