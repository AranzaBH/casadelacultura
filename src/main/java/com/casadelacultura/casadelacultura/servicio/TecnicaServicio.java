package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Tecnica;
import com.casadelacultura.casadelacultura.repositorio.TecnicaRepositorio;

import java.util.Optional;

@Service
public class TecnicaServicio {

    @Autowired
    private TecnicaRepositorio tecnicaRepositorio;

    // Obtener todas las técnicas
    public Iterable<Tecnica> obtenerTodasTecnicas() {
        return tecnicaRepositorio.findAll();
    }

    // Obtener una técnica por su ID
    public Optional<Tecnica> obtenerTecnicaPorId(Long idTecnica) {
        return tecnicaRepositorio.findById(idTecnica);
    }

    // Crear una nueva técnica
    public Tecnica crearTecnica(Tecnica tecnica) {
        return tecnicaRepositorio.save(tecnica);
    }

    // Actualizar una técnica existente
    public Optional<Tecnica> actualizarTecnica(Long idTecnica, Tecnica formulario) {
        return tecnicaRepositorio.findById(idTecnica).map(tecnicaExistente -> {
            tecnicaExistente.setNombreTecnica(formulario.getNombreTecnica());
            tecnicaExistente.setDescripcionTecnica(formulario.getDescripcionTecnica());
            return tecnicaRepositorio.save(tecnicaExistente);
        });
    }

    // Eliminar una técnica
    public boolean eliminarTecnica(Long idTecnica) {
        Optional<Tecnica> tecnica = tecnicaRepositorio.findById(idTecnica);
        if (tecnica.isPresent()) {
            tecnicaRepositorio.delete(tecnica.get());
            return true;
        }
        return false;
    }
}
