package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.ObrasPorAutor;
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
        return obrasPorAutorRepositorio.findById(idObrasPorAutor).orElse(null);
    }

    // Crear una nueva relación entre una obra y un autor
    public ObrasPorAutor crearRelacion(ObrasPorAutor obrasPorAutor) {
        if (!autorRepositorio.existsById(obrasPorAutor.getAutor().getIdAutor())) {
            throw new IllegalArgumentException("El autor no existe.");
        }

        // Validar que la obra existe
        if (!obraRepositorio.existsById(obrasPorAutor.getObra().getIdObra())) {
            throw new IllegalArgumentException("La obra no existe.");
        }
        return obrasPorAutorRepositorio.save(obrasPorAutor);
    }

    // Actualizar una relación existente entre una obra y un autor
    public ObrasPorAutor actualizarRelacion(Long idObrasPorAutor, ObrasPorAutor formulario) {
        ObrasPorAutor obrasPorAutorFromDB = obtenerRelacionPorId(idObrasPorAutor);
        
        // Validar que el autor existe
        if (!autorRepositorio.existsById(formulario.getAutor().getIdAutor())) {
            throw new IllegalArgumentException("El autor no existe.");
        }

        // Validar que la obra existe
        if (!obraRepositorio.existsById(formulario.getObra().getIdObra())) {
            throw new IllegalArgumentException("La obra no existe.");
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
