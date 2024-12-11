package com.casadelacultura.casadelacultura.servicio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.CategoriaObra;
import com.casadelacultura.casadelacultura.entity.Obra;
import com.casadelacultura.casadelacultura.entity.Tecnica;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.ObraRepositorio;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ObraServicio {
    private final ObraRepositorio obraRepositorio;
    private final CategoriaObraServicio categoriaObraServicio;
    private final TecnicaServicio tecnicaServicio;

    // Obtener todas las obras
    public Iterable<Obra> listarObras() {
        return obraRepositorio.findAll();
    }

    // Obtener una obra por ID
    public Obra obtenerObraPorId(Long idObra) {
        return obraRepositorio.findById(idObra)
                .orElseThrow(() -> new GlobalExceptionNoEncontrada("No se encontro la obra con el ID: " + idObra));
    }

    // Crear una nueva obra
    public Obra crearObra(Obra obra) {
        validarFechas(obra);
        // Valida si ya existe una obra con los mismos datos
        if (obraRepositorio.existsByCodigoIgnoreCase(obra.getCodigo())) {
            throw new GlobalExceptionNoEncontrada("Ya existe la obra con  \nTitulo Original: "
                    + obra.getTituloOriginalObra() + "\nTitulo de la obra: " + obra.getTituloObra() + "\nCodigo: "
                    + obra.getCodigo());
        }
        // Valida si la categoria de la obra existe
        CategoriaObra categoriaObra = categoriaObraServicio
                .obtenerCategoriaPorId(obra.getCategoriaObra().getIdCategoriaObra());
        obra.setCategoriaObra(categoriaObra);

        // Valida si la tecnica existe
        Tecnica tecnica = tecnicaServicio.obtenerTecnicaPorId(obra.getTecnica().getIdTecnica());
        obra.setTecnica(tecnica);

        obra.setFechaCreacion(LocalDateTime.now());
        return obraRepositorio.save(obra);
    }

    // Actualizar una obra existente
    public Obra actualizarObra(Long idObra, Obra formulario) {
        Obra obraFromDB = obtenerObraPorId(idObra);
        // Valida si existe una obra con los mismos datos
        if (obraRepositorio.existsByCodigoIgnoreCaseAndIdObraNot(formulario.getCodigo(), idObra)) {
            throw new GlobalExceptionNoEncontrada("Ya existe la obra con  \nTitulo Original: "
                    + formulario.getTituloOriginalObra() + "\nCodigo: " + formulario.getCodigo());
        }

        // Valida la existencia de la categoria
        CategoriaObra categoriaObra = categoriaObraServicio.obtenerCategoriaPorId(formulario.getCategoriaObra().getIdCategoriaObra());
        formulario.setCategoriaObra(categoriaObra);

        // Valida si la tecnica existe
        Tecnica tecnica = tecnicaServicio.obtenerTecnicaPorId(formulario.getTecnica().getIdTecnica());
        formulario.setTecnica(tecnica);

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
        obraFromDB.setCodigo(formulario.getCodigo());
        obraFromDB.setCategoriaObra(formulario.getCategoriaObra());
        return obraRepositorio.save(obraFromDB);
    }

    // Eliminar una obra
    public void eliminarObra(Long idObra) {
        Obra obraFromDB = obtenerObraPorId(idObra);
        obraRepositorio.delete(obraFromDB);
    }

    public void validarFechas(Obra obra) {
        LocalDate hoy = LocalDate.now();
        // Validar si la fecha de nacimiento es mayor a la fecha de hoy
        if (obra.getFechaObra() != null && obra.getFechaObra().isAfter(hoy)) {
            throw new GlobalExceptionNoEncontrada(
                    "La fecha de publicacion de la obra no puede ser mayor al día de hoy.");
        }
    }
}