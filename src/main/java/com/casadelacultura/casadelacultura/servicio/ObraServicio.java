package com.casadelacultura.casadelacultura.servicio;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Obra;
import com.casadelacultura.casadelacultura.repositorio.ObraRepositorio;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ObraServicio {
    private final ObraRepositorio obraRepositorio;

    // Obtener todas las obras
    public Iterable<Obra> listarObras() {
        return obraRepositorio.findAll();
    }

    // Obtener una obra por ID
    public Obra obtenerObraPorId(Long idObra) {
        return obraRepositorio.findById(idObra).orElse(null);
    }

    // Crear una nueva obra
    public Obra crearObra(Obra obra) {
        obra.setFechaCreacion(LocalDateTime.now());
        return obraRepositorio.save(obra);
    }

    // Actualizar una obra existente
    public Obra actualizarObra(Long idObra, Obra formulario) {
        Obra obraFromDB = obtenerObraPorId(idObra);
        obraFromDB.setTituloObra(formulario.getTituloObra());
        obraFromDB.setTituloOriginalObra(formulario.getTituloOriginalObra());
        obraFromDB.setDescripcion(formulario.getDescripcion());
        obraFromDB.setEstadoActivo(formulario.isEstadoActivo());
        obraFromDB.setDimension(formulario.getDimension());
        obraFromDB.setFechaObra(formulario.getFechaObra());
        obraFromDB.setImagenPath(formulario.getImagenPath());
        obraFromDB.setLocalizacion(formulario.getLocalizacion());
        obraFromDB.setTecnica(formulario.getTecnica());
        obraFromDB.setMaterial(formulario.getMaterial());
        obraFromDB.setCategoriaObra(formulario.getCategoriaObra());
        return obraRepositorio.save(obraFromDB);
    }

    // Eliminar una obra
    public void eliminarObra(Long idObra) {
        Obra obraFromDB = obtenerObraPorId(idObra);
        obraRepositorio.delete(obraFromDB);
    }
}
