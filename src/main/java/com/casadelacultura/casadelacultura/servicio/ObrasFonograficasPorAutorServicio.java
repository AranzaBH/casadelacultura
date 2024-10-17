package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.ObrasFonograficasPorAutor;
import com.casadelacultura.casadelacultura.repositorio.ObrasFonograficasPorAutorRepositorio;

import java.util.Optional;

@Service
public class ObrasFonograficasPorAutorServicio {

    @Autowired
    private ObrasFonograficasPorAutorRepositorio obrasFonograficasPorAutorRepositorio;

    // Obtener todas las relaciones de obras fonográficas por autor
    public Iterable<ObrasFonograficasPorAutor> listarObrasFonograficasPorAutor() {
        return obrasFonograficasPorAutorRepositorio.findAll();
    }

    // Obtener una relación entre obra fonográfica y autor por ID
    public Optional<ObrasFonograficasPorAutor> obtenerRelacionPorId(Integer idAutor) {
        return obrasFonograficasPorAutorRepositorio.findById(idAutor);
    }

    // Crear una nueva relación entre obra fonográfica y autor
    public ObrasFonograficasPorAutor crearRelacion(ObrasFonograficasPorAutor obrasFonograficasPorAutor) {
        return obrasFonograficasPorAutorRepositorio.save(obrasFonograficasPorAutor);
    }

    // Actualizar una relación existente entre obra fonográfica y autor
    public Optional<ObrasFonograficasPorAutor> actualizarRelacion(Integer idAutor, ObrasFonograficasPorAutor formulario) {
        return obrasFonograficasPorAutorRepositorio.findById(idAutor).map(relacionExistente -> {
            relacionExistente.setAutor(formulario.getAutor());
            relacionExistente.setObrasFonograficas(formulario.getObrasFonograficas());
            return obrasFonograficasPorAutorRepositorio.save(relacionExistente);
        });
    }

    // Eliminar una relación entre obra fonográfica y autor
    public boolean eliminarRelacion(Integer idAutor) {
        Optional<ObrasFonograficasPorAutor> relacion = obrasFonograficasPorAutorRepositorio.findById(idAutor);
        if (relacion.isPresent()) {
            obrasFonograficasPorAutorRepositorio.delete(relacion.get());
            return true;
        }
        return false;
    }
}
