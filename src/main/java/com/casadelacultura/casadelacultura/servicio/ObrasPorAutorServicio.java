package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.ObrasPorAutor;
import com.casadelacultura.casadelacultura.excepciones.AutorNoEncontradoException;
import com.casadelacultura.casadelacultura.excepciones.LibroNoEncontradoException;
import com.casadelacultura.casadelacultura.excepciones.ObrasPorAutorNoEncontradoException;
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
        return obrasPorAutorRepositorio.findById(idObrasPorAutor).orElseThrow(() -> new ObrasPorAutorNoEncontradoException("No se encontro la relacion de obra por autor con el ID"+idObrasPorAutor));
    }

    // Crear una nueva relación entre una obra y un autor
    public ObrasPorAutor crearRelacion(ObrasPorAutor obrasPorAutor) {
        //Valida la existencia del autor
        if (obrasPorAutor.getAutor() == null || !autorRepositorio.existsById(obrasPorAutor.getAutor().getIdAutor())) {
            throw new AutorNoEncontradoException(
                    "Autor con ID " + obrasPorAutor.getAutor().getIdAutor() + " no encontrado.");
        }
        // Validar la existencia del Obra
        if (obrasPorAutor.getObra() == null || !obraRepositorio.existsById(obrasPorAutor.getObra().getIdObra())) {
            throw new LibroNoEncontradoException(
                    "Obra con ID " + obrasPorAutor.getObra().getIdObra() + " no encontrado.");
        }
        return obrasPorAutorRepositorio.save(obrasPorAutor);
    }

    // Actualizar una relación existente entre una obra y un autor
    public ObrasPorAutor actualizarRelacion(Long idObrasPorAutor, ObrasPorAutor formulario) {
        ObrasPorAutor obrasPorAutorFromDB = obtenerRelacionPorId(idObrasPorAutor);
        // Validar la existencia del Autor
        if (formulario.getAutor() == null || !autorRepositorio.existsById(formulario.getAutor().getIdAutor())) {
            throw new AutorNoEncontradoException(
                    "Autor con ID " + formulario.getAutor().getIdAutor() + " no encontrado.");
        }

        // Validar la existencia del Obra
        if (formulario.getObra() == null || !obraRepositorio.existsById(formulario.getObra().getIdObra())) {
            throw new LibroNoEncontradoException(
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
