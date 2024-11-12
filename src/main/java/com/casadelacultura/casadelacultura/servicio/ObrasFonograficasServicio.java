package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.ObrasFonograficas;
import com.casadelacultura.casadelacultura.repositorio.ObrasFonograficasRepositorio;

import java.util.Optional;

@Service
public class ObrasFonograficasServicio {

    @Autowired
    private ObrasFonograficasRepositorio obrasFonograficasRepositorio;

    // Obtener todas las obras fonográficas
    public Iterable<ObrasFonograficas> listarObrasFonograficas() {
        return obrasFonograficasRepositorio.findAll();
    }

    // Obtener una obra fonográfica por ID
    public Optional<ObrasFonograficas> obtenerObraFonograficaPorId(Long idObrasFonograficas) {
        return obrasFonograficasRepositorio.findById(idObrasFonograficas);
    }

    // Crear una nueva obra fonográfica
    public ObrasFonograficas crearObraFonografica(ObrasFonograficas obrasFonograficas) {
        return obrasFonograficasRepositorio.save(obrasFonograficas);
    }

    // Actualizar una obra fonográfica existente
    public Optional<ObrasFonograficas> actualizarObraFonografica(Long idObrasFonograficas, ObrasFonograficas formulario) {
        return obrasFonograficasRepositorio.findById(idObrasFonograficas).map(obraFonograficaExistente -> {
            obraFonograficaExistente.setTituloObraFonografica(formulario.getTituloObraFonografica());
            obraFonograficaExistente.setDuracion(formulario.getDuracion());
            obraFonograficaExistente.setFechaLanzamiento(formulario.getFechaLanzamiento());
            obraFonograficaExistente.setDescripcion(formulario.getDescripcion());
            obraFonograficaExistente.setIdUrlImagenPortada(formulario.getIdUrlImagenPortada());
            obraFonograficaExistente.setActivo(formulario.getActivo());
            return obrasFonograficasRepositorio.save(obraFonograficaExistente);
        });
    }

    // Eliminar una obra fonográfica
    public boolean eliminarObraFonografica(Long idObrasFonograficas) {
        Optional<ObrasFonograficas> obraFonografica = obrasFonograficasRepositorio.findById(idObrasFonograficas);
        if (obraFonografica.isPresent()) {
            obrasFonograficasRepositorio.delete(obraFonografica.get());
            return true;
        }
        return false;
    }
}