package com.casadelacultura.casadelacultura.servicio;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Tecnica;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
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
        return tecnicaRepositorio.findById(idTecnica)
                .orElseThrow(() -> new GlobalExceptionNoEncontrada(
                        "No se encontro la tecnica con el ID " + idTecnica));
    }

    // Crear una nueva técnica
    public Tecnica crearTecnica(Tecnica tecnica) {
        tecnica.setFechaCreacion(LocalDateTime.now());
        //Valida si ya existe una tecnica con el mismo nombre
        if (tecnicaRepositorio.existsByNombreTecnicaIgnoreCase(tecnica.getNombreTecnica())) {
            throw new GlobalExceptionNoEncontrada("Ya existe la tecnica con el nombre: "+ tecnica.getNombreTecnica());
            
        }
        return tecnicaRepositorio.save(tecnica);
    }

    // Actualizar una técnica existente
    public Tecnica actualizarTecnica(Long idTecnica, Tecnica formulario) {
        Tecnica tecnicaFromDB = obtenerTecnicaPorId(idTecnica);
        if (tecnicaRepositorio.existsByNombreTecnicaIgnoreCaseAndIdTecnicaNot(formulario.getNombreTecnica(), idTecnica)) {
            throw new GlobalExceptionNoEncontrada("Ya existe la tecnica con el nombre: "+ formulario.getNombreTecnica());
        }
        tecnicaFromDB.setNombreTecnica(formulario.getNombreTecnica());
        tecnicaFromDB.setDescripcionTecnica(formulario.getDescripcionTecnica());
        return tecnicaRepositorio.save(tecnicaFromDB);
    }

    // Eliminar una técnica
    public String eliminarTecnica(Long idTecnica) {
        Tecnica tecnicaFromDB = obtenerTecnicaPorId(idTecnica);
        //Verifica si la tecnica tiene obras asociadas
        if (tecnicaFromDB.getObra() !=null && !tecnicaFromDB.getObra().isEmpty()) {
            return "No se pude eliminar la tecnica "+tecnicaFromDB.getNombreTecnica() + " con ID: " + idTecnica + " porque está vinculado a uno o más obras.";
        }
        tecnicaRepositorio.delete(tecnicaFromDB);
        return "Se ha eliminado con éxito la tecnica: " + tecnicaFromDB.getNombreTecnica() + " con ID: " + idTecnica;
    }
}
