package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.ObrasPorAutor;
import com.casadelacultura.casadelacultura.repositorio.ObrasPorAutorRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ObrasPorAutorServicio {
    private final ObrasPorAutorRepositorio obrasPorAutorRepositorio;

    // Obtener todas las relaciones entre obras y autores
    public Iterable<ObrasPorAutor> listarObrasPorAutor() {
        return obrasPorAutorRepositorio.findAll();
    }

    // Obtener una relación específica entre obra y autor por ID
    public ObrasPorAutor obtenerRelacionPorId(Long idAutor) {
        return obrasPorAutorRepositorio.findById(idAutor).orElse(null);
    }

    // Crear una nueva relación entre una obra y un autor
    public ObrasPorAutor crearRelacion(ObrasPorAutor obrasPorAutor) {
        return obrasPorAutorRepositorio.save(obrasPorAutor);
    }

    // Actualizar una relación existente entre una obra y un autor
    public ObrasPorAutor actualizarRelacion(Long idAutor, ObrasPorAutor formulario) {
        ObrasPorAutor obrasPorAutorFromDB = obtenerRelacionPorId(idAutor);
        obrasPorAutorFromDB.setAutor(formulario.getAutor());
        obrasPorAutorFromDB.setObra(formulario.getObra());
        return obrasPorAutorRepositorio.save(obrasPorAutorFromDB);
    }

    // Eliminar una relación entre una obra y un autor
    public void eliminarRelacion(Long idAutor) {
        ObrasPorAutor obrasPorAutorFromDB = obtenerRelacionPorId(idAutor);
        obrasPorAutorRepositorio.delete(obrasPorAutorFromDB);
        
    }
}
