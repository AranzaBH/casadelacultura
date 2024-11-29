package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;

import com.casadelacultura.casadelacultura.entity.Autor;
import com.casadelacultura.casadelacultura.entity.Obra;
import com.casadelacultura.casadelacultura.entity.ObrasPorAutor;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.AutorRepositorio;
import com.casadelacultura.casadelacultura.repositorio.ObraRepositorio;
import com.casadelacultura.casadelacultura.repositorio.ObrasPorAutorRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ObrasPorAutorServicio {
    private final ObrasPorAutorRepositorio obrasPorAutorRepositorio;
    private final AutorRepositorio autorRepositorio; // Repositorio para Autor
    private final ObraRepositorio obraRepositorio;

    // Obtener todas las relaciones entre obras y autores
    public Iterable<ObrasPorAutor> listarObrasPorAutor() {
        return obrasPorAutorRepositorio.findAll();
    }

    // Obtener una relación específica entre obra y autor por ID
    public ObrasPorAutor obtenerRelacionPorId(Long idObrasPorAutor) {
        return obrasPorAutorRepositorio.findById(idObrasPorAutor).orElseThrow(() -> new GlobalExceptionNoEncontrada(
                "No se encontro la relacion de obra por autor con el ID" + idObrasPorAutor));
    }

    // Crear una nueva relación entre una obra y un autor
    public ObrasPorAutor crearRelacion(ObrasPorAutor obrasPorAutor) {
        // Valida si la relacion ya existe
        if (obrasPorAutorRepositorio.existsByAutor_IdAutorAndObra_IdObra(
                obrasPorAutor.getAutor().getIdAutor(),
                obrasPorAutor.getObra().getIdObra())) {
            throw new GlobalExceptionNoEncontrada(
                    "Ya existe la relación para la obra con ID " + obrasPorAutor.getObra().getIdObra() +
                            " y el autor " + obrasPorAutor.getAutor().getIdAutor());

        }

        // Valida la existencia del autor
        if (obrasPorAutor.getAutor() == null
                || !autorRepositorio.existsById(obrasPorAutor.getAutor().getIdAutor())) {
            throw new GlobalExceptionNoEncontrada(
                    "Autor con ID " + obrasPorAutor.getAutor().getIdAutor() + " no encontrado.");
        }
        // Validar la existencia del Obra
        if (obrasPorAutor.getObra() == null
                || !obraRepositorio.existsById(obrasPorAutor.getObra().getIdObra())) {
            throw new GlobalExceptionNoEncontrada(
                    "Obra con ID " + obrasPorAutor.getObra().getIdObra() + " no encontrado.");
        }
        return obrasPorAutorRepositorio.save(obrasPorAutor);
    }

    // Actualizar una relación existente entre una obra y un autor
    public ObrasPorAutor actualizarRelacion(Long idObrasPorAutor, ObrasPorAutor formulario) {
        ObrasPorAutor obrasPorAutorFromDB = obtenerRelacionPorId(idObrasPorAutor);
        // Verifica si la relacion ya existe
        if (obrasPorAutorRepositorio.existsByAutor_IdAutorAndObra_IdObraAndIdObrasPorAutorNot(
                formulario.getAutor().getIdAutor(), formulario.getObra().getIdObra(), idObrasPorAutor)) {
            Obra obra = obraRepositorio.findById(formulario.getObra().getIdObra())
                    .orElseThrow(() -> new GlobalExceptionNoEncontrada("Obra no encontrada"));
            Autor autor = autorRepositorio.findById(formulario.getAutor().getIdAutor())
                    .orElseThrow(() -> new GlobalExceptionNoEncontrada("Autor no encontrado"));
            throw new GlobalExceptionNoEncontrada(
                    "Ya existe una relacion para la obra con título: " + obra.getTituloObra() +
                            " (ID: " + formulario.getObra().getIdObra() +
                            " ) y el autor  con el nombre " + autor.getNombreAutor() + " " + autor.getApellidoPaterno()
                            + " " + autor.getApellidoMaterno() +
                            " (ID: " + formulario.getAutor().getIdAutor() + " )");
        }

        // Validar la existencia del Autor
        if (formulario.getAutor() == null || !autorRepositorio.existsById(formulario.getAutor().getIdAutor())) {
            throw new GlobalExceptionNoEncontrada(
                    "Autor con ID " + formulario.getAutor().getIdAutor() + " no encontrado.");
        }

        // Validar la existencia del Obra
        if (formulario.getObra() == null || !obraRepositorio.existsById(formulario.getObra().getIdObra())) {
            throw new GlobalExceptionNoEncontrada(
                    "Obra con ID " + formulario.getObra().getIdObra() + " no encontrado.");
        }

        obrasPorAutorFromDB.setAutor(formulario.getAutor());
        obrasPorAutorFromDB.setObra(formulario.getObra());
        return obrasPorAutorRepositorio.save(obrasPorAutorFromDB);
    }

    // Eliminar una relación entre una obra y un autor
    public void eliminarRelacion(Long idObrasPorAutor) {
        ObrasPorAutor obrasPorAutorFromDB = obtenerRelacionPorId(idObrasPorAutor);
        obrasPorAutorRepositorio.delete(obrasPorAutorFromDB);

    }
}
