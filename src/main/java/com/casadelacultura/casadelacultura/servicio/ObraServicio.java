package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Obra;
import com.casadelacultura.casadelacultura.repositorio.ObraRepositorio;

import java.util.Optional;

@Service
public class ObraServicio {

    @Autowired
    private ObraRepositorio obraRepositorio;

    // Obtener todas las obras
    public Iterable<Obra> listarObras() {
        return obraRepositorio.findAll();
    }

    // Obtener una obra por ID
    public Optional<Obra> obtenerObraPorId(Long idObra) {
        return obraRepositorio.findById(idObra);
    }

    // Crear una nueva obra
    public Obra crearObra(Obra obra) {
        return obraRepositorio.save(obra);
    }

    // Actualizar una obra existente
    public Optional<Obra> actualizarObra(Long idObra, Obra formulario) {
        return obraRepositorio.findById(idObra).map(obraExistente -> {
            obraExistente.setNombreObra(formulario.getNombreObra());
            obraExistente.setEstadoActivo(formulario.isEstadoActivo());
            obraExistente.setFechaCreacion(formulario.getFechaCreacion());
            obraExistente.setDimension(formulario.getDimension());
            obraExistente.setIdUrlImagenPortada(formulario.getIdUrlImagenPortada());
            obraExistente.setNombreUbicacionCreacion(formulario.getNombreUbicacionCreacion());
            obraExistente.setTecnica(formulario.getTecnica());
            obraExistente.setMaterial(formulario.getMaterial());
            obraExistente.setCategoriaObra(formulario.getCategoriaObra());
            return obraRepositorio.save(obraExistente);
        });
    }

    // Eliminar una obra
    public boolean eliminarObra(Long idObra) {
        Optional<Obra> obra = obraRepositorio.findById(idObra);
        if (obra.isPresent()) {
            obraRepositorio.delete(obra.get());
            return true;
        }
        return false;
    }
}
