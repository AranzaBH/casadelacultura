package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Tecnica;
import com.casadelacultura.casadelacultura.repositorio.TecnicaRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TecnicaServicio {
    private final TecnicaRepositorio tecnicaRepositorio;

    // Obtener todas las técnicas
    public Iterable<Tecnica> obtenerTodasTecnicas() {
        return tecnicaRepositorio.findAll();
    }

    // Obtener una técnica por su ID
    public Tecnica obtenerTecnicaPorId(Long idTecnica) {
        return tecnicaRepositorio.findById(idTecnica).orElse(null);
    }

    // Crear una nueva técnica
    public Tecnica crearTecnica(Tecnica tecnica) {
        return tecnicaRepositorio.save(tecnica);
    }

    // Actualizar una técnica existente
    public Tecnica actualizarTecnica(Long idTecnica, Tecnica formulario) {
        Tecnica tecnicaFromDB = obtenerTecnicaPorId(idTecnica);
        tecnicaFromDB.setNombreTecnica(formulario.getNombreTecnica());
        tecnicaFromDB.setDescripcionTecnica(formulario.getDescripcionTecnica());
        return tecnicaRepositorio.save(tecnicaFromDB);
        
    }

    // Eliminar una técnica
    public void eliminarTecnica(Long idTecnica) {
        Tecnica tecnicaFromDB = obtenerTecnicaPorId(idTecnica);
        tecnicaRepositorio.delete(tecnicaFromDB);
    }
}
