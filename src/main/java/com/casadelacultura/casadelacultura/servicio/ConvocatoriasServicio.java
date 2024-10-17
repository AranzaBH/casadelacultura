package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Convocatorias;
import com.casadelacultura.casadelacultura.repositorio.ConvocatoriasRepositorio;

import java.util.Optional;

@Service
public class ConvocatoriasServicio {

    @Autowired
    private final ConvocatoriasRepositorio convocatoriasRepositorio;

    public ConvocatoriasServicio(ConvocatoriasRepositorio convocatoriasRepositorio) {
        this.convocatoriasRepositorio = convocatoriasRepositorio;
    }

    // Obtener todas las convocatorias
    public Iterable<Convocatorias> listarConvocatorias() {
        return convocatoriasRepositorio.findAll();
    }

    // Obtener una convocatoria por ID
    public Optional<Convocatorias> obtenerConvocatoriaPorId(Integer idConvocatorias) {
        return convocatoriasRepositorio.findById(idConvocatorias);
    }

    // Crear una nueva convocatoria
    public Convocatorias crearConvocatoria(Convocatorias convocatoria) {
        return convocatoriasRepositorio.save(convocatoria);
    }

    // Actualizar una convocatoria existente
    public Optional<Convocatorias> actualizarConvocatoria(Integer idConvocatorias, Convocatorias formulario) {
        return convocatoriasRepositorio.findById(idConvocatorias).map(convocatoriaExistente -> {
            convocatoriaExistente.setNombreConvocatoria(formulario.getNombreConvocatoria());
            convocatoriaExistente.setDescripcion(formulario.getDescripcion());
            convocatoriaExistente.setFechaPublicacion(formulario.getFechaPublicacion());
            convocatoriaExistente.setFechaInicio(formulario.getFechaInicio());
            convocatoriaExistente.setFechaFinalizacion(formulario.getFechaFinalizacion());
            return convocatoriasRepositorio.save(convocatoriaExistente);
        });
    }

    // Eliminar una convocatoria
    public boolean eliminarConvocatoria(Integer idConvocatorias) {
        Optional<Convocatorias> convocatoria = convocatoriasRepositorio.findById(idConvocatorias);
        if (convocatoria.isPresent()) {
            convocatoriasRepositorio.delete(convocatoria.get());
            return true;
        }
        return false;
    }
}
