package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Autor;
import com.casadelacultura.casadelacultura.entity.ObrasFonograficas;
import com.casadelacultura.casadelacultura.entity.ObrasFonograficasPorAutor;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.ObrasFonograficasPorAutorRepositorio;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ObrasFonograficasPorAutorServicio {
    private ObrasFonograficasPorAutorRepositorio obrasFonograficasPorAutorRepositorio;
    private final AutorServicio autorServicio;
    private final ObrasFonograficasServicio obrasFonograficasServicio;

    // Obtener todas las relaciones de obras fonográficas por autor
    public Iterable<ObrasFonograficasPorAutor> listarObrasFonograficasPorAutor() {
        return obrasFonograficasPorAutorRepositorio.findAll();
    }

    // Obtener una relación entre obra fonográfica y autor por ID
    public ObrasFonograficasPorAutor obtenerRelacionPorId(Long idObrasFonograficasPorAutor) {
        return obrasFonograficasPorAutorRepositorio.findById(idObrasFonograficasPorAutor)
                .orElseThrow(() -> new GlobalExceptionNoEncontrada(
                        "No se encontro la relacion Obra Fonografica por autor con el ID: "
                                + idObrasFonograficasPorAutor));
    }

    // Crear una nueva relación entre obra fonográfica y autor
    public ObrasFonograficasPorAutor crearRelacion(ObrasFonograficasPorAutor obrasFonograficasPorAutor) {
        // Valida si existe una relacion con los mismos datos
        if (obrasFonograficasPorAutorRepositorio.existsByAutor_IdAutorAndObrasFonograficas_IdObrasFonograficas(
                obrasFonograficasPorAutor.getAutor().getIdAutor(),
                obrasFonograficasPorAutor.getObrasFonograficas().getIdObrasFonograficas())) {
            throw new GlobalExceptionNoEncontrada(
                    "Ya esite la relación para el Autor con ID " + obrasFonograficasPorAutor.getAutor().getIdAutor() +
                            " y la obra fonografica con ID: "
                            + obrasFonograficasPorAutor.getObrasFonograficas().getIdObrasFonograficas());
        }
        // Valida existencia de un autor
        Autor autor = autorServicio.obtenerAutorPorId(obrasFonograficasPorAutor.getAutor().getIdAutor());
        obrasFonograficasPorAutor.setAutor(autor);

        ObrasFonograficas obrasFonograficas = obrasFonograficasServicio
                .obtenerObraFonograficaPorId(obrasFonograficasPorAutor.getObrasFonograficas().getIdObrasFonograficas());
        obrasFonograficasPorAutor.setObrasFonograficas(obrasFonograficas);

        return obrasFonograficasPorAutorRepositorio.save(obrasFonograficasPorAutor);
    }

    // Actualizar una relación existente entre obra fonográfica y autor
    public ObrasFonograficasPorAutor actualizarRelacion(Long idObrasFonograficasPorAutor,
            ObrasFonograficasPorAutor formulario) {
        ObrasFonograficasPorAutor obrasFonograficasPorAutorFrom = obtenerRelacionPorId(idObrasFonograficasPorAutor);

        // Valida si existe una relacion igual
        if (obrasFonograficasPorAutorRepositorio
                .existsByAutor_IdAutorAndObrasFonograficas_IdObrasFonograficasAndIdObrasFonograficasPorAutorNot(
                        formulario.getAutor().getIdAutor(), formulario.getObrasFonograficas().getIdObrasFonograficas(),
                        idObrasFonograficasPorAutor)) {
            throw new GlobalExceptionNoEncontrada(
                    "Ya esite la relación para el Autor con ID: " + formulario.getAutor().getIdAutor() +
                            " y la obra fonografica con ID: "
                            + formulario.getObrasFonograficas().getIdObrasFonograficas());
        }
        // Valida existencia de un autor
        Autor autor = autorServicio.obtenerAutorPorId(formulario.getAutor().getIdAutor());
        formulario.setAutor(autor);

        ObrasFonograficas obrasFonograficas = obrasFonograficasServicio
                .obtenerObraFonograficaPorId(formulario.getObrasFonograficas().getIdObrasFonograficas());
        formulario.setObrasFonograficas(obrasFonograficas);

        obrasFonograficasPorAutorFrom.setAutor(formulario.getAutor());
        obrasFonograficasPorAutorFrom.setObrasFonograficas(formulario.getObrasFonograficas());
        return obrasFonograficasPorAutorRepositorio.save(obrasFonograficasPorAutorFrom);
    }

    // Eliminar una relación entre obra fonográfica y autor
    public void eliminarRelacion(Long idObrasFonograficasPorAutor) {
        ObrasFonograficasPorAutor obrasFonograficasPorAutorFrom = obtenerRelacionPorId(idObrasFonograficasPorAutor);
        obrasFonograficasPorAutorRepositorio.delete(obrasFonograficasPorAutorFrom);
    }
}
