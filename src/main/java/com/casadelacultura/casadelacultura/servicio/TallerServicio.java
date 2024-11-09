package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Taller;
import com.casadelacultura.casadelacultura.repositorio.TallerRepositorio;

import java.util.Optional;

@Service
public class TallerServicio {

    @Autowired
    private TallerRepositorio tallerRepositorio;

    // Obtener todos los talleres
    public Iterable<Taller> obtenerTodosTalleres() {
        return tallerRepositorio.findAll();
    }

    // Obtener un taller por su ID
    public Optional<Taller> obtenerTallerPorId(Long idTaller) {
        return tallerRepositorio.findById(idTaller);
    }

    // Crear un nuevo taller
    public Taller crearTaller(Taller taller) {
        return tallerRepositorio.save(taller);
    }

    // Actualizar un taller existente
    public Optional<Taller> actualizarTaller(Long idTaller, Taller formulario) {
        return tallerRepositorio.findById(idTaller).map(tallerExistente -> {
            tallerExistente.setTituloTaller(formulario.getTituloTaller());
            tallerExistente.setClave(formulario.getClave());
            tallerExistente.setDescripcion(formulario.getDescripcion());
            tallerExistente.setFechaInico(formulario.getFechaInico());
            tallerExistente.setFechaFinal(formulario.getFechaFinal());
            tallerExistente.setFechaCreacion(formulario.getFechaCreacion());
            tallerExistente.setTipoTaller(formulario.getTipoTaller());
            tallerExistente.setUrlImagenPortadaTaller(formulario.getUrlImagenPortadaTaller());
            tallerExistente.setEstaActivo(formulario.isEstaActivo());
            return tallerRepositorio.save(tallerExistente);
        });
    }

    // Eliminar un taller
    public boolean eliminarTaller(Long idTaller) {
        Optional<Taller> taller = tallerRepositorio.findById(idTaller);
        if (taller.isPresent()) {
            tallerRepositorio.delete(taller.get());
            return true;
        }
        return false;
    }
}
