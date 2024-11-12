package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.ObrasPorAutor;
import com.casadelacultura.casadelacultura.repositorio.ObrasPorAutorRepositorio;

import java.util.Optional;

@Service
public class ObrasPorAutorServicio {

    @Autowired
    private ObrasPorAutorRepositorio obrasPorAutorRepositorio;

    // Obtener todas las relaciones entre obras y autores
    public Iterable<ObrasPorAutor> listarObrasPorAutor() {
        return obrasPorAutorRepositorio.findAll();
    }

    // Obtener una relación específica entre obra y autor por ID
    public Optional<ObrasPorAutor> obtenerRelacionPorId(Long idAutor) {
        return obrasPorAutorRepositorio.findById(idAutor);
    }

    // Crear una nueva relación entre una obra y un autor
    public ObrasPorAutor crearRelacion(ObrasPorAutor obrasPorAutor) {
        return obrasPorAutorRepositorio.save(obrasPorAutor);
    }

    // Actualizar una relación existente entre una obra y un autor
    public Optional<ObrasPorAutor> actualizarRelacion(Long idAutor, ObrasPorAutor formulario) {
        return obrasPorAutorRepositorio.findById(idAutor).map(relacionExistente -> {
            relacionExistente.setAutor(formulario.getAutor());
            relacionExistente.setObra(formulario.getObra());
            return obrasPorAutorRepositorio.save(relacionExistente);
        });
    }

    // Eliminar una relación entre una obra y un autor
    public boolean eliminarRelacion(Long idAutor) {
        Optional<ObrasPorAutor> relacion = obrasPorAutorRepositorio.findById(idAutor);
        if (relacion.isPresent()) {
            obrasPorAutorRepositorio.delete(relacion.get());
            return true;
        }
        return false;
    }
}
