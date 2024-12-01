package com.casadelacultura.casadelacultura.servicio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import com.casadelacultura.casadelacultura.entity.Obra;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.CategoriaObraRepositorio;
import com.casadelacultura.casadelacultura.repositorio.ObraRepositorio;
import com.casadelacultura.casadelacultura.repositorio.TecnicaRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ObraServicio {
    private final ObraRepositorio obraRepositorio;
    private final CategoriaObraRepositorio categoriaObraRepositorio;
    private final TecnicaRepositorio tecnicaRepositorio;

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
        //Valida si ya existe una obra con los mismos datos 

        // Valida si la categoria de la obra existe
        if (obra.getCategoriaObra() == null
                || !categoriaObraRepositorio.existsById(obra.getCategoriaObra().getIdCategoriaObra())) {
            throw new GlobalExceptionNoEncontrada(
                    "Categoria de la Obra con ID: " + obra.getCategoriaObra().getIdCategoriaObra() + " No encontrado");
        }
        // Valida si la tecnica existe
        if (obra.getTecnica() == null
                || !tecnicaRepositorio.existsById(obra.getTecnica().getIdTecnica())) {
            throw new GlobalExceptionNoEncontrada(
                    "Tecnica de la obra con ID: " + obra.getTecnica().getIdTecnica() + " No encontrado");
        }


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
