package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.ObrasFonograficas;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.ObrasFonograficasRepositorio;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ObrasFonograficasServicio {
    private final ObrasFonograficasRepositorio obrasFonograficasRepositorio;

    // Obtener todas las obras fonográficas
    public Iterable<ObrasFonograficas> listarObrasFonograficas() {
        return obrasFonograficasRepositorio.findAll();
    }

    // Obtener una obra fonográfica por ID
    public ObrasFonograficas obtenerObraFonograficaPorId(Long idObrasFonograficas) {
        return obrasFonograficasRepositorio.findById(idObrasFonograficas)
                .orElseThrow(() -> new GlobalExceptionNoEncontrada(
                        "No se encontro obra fonografica con el ID: " + idObrasFonograficas));
    }

    // Crear una nueva obra fonográfica
    public ObrasFonograficas crearObraFonografica(ObrasFonograficas obrasFonograficas) {
        // Valida si ya existe una obra con los mismos datos
        if (obrasFonograficasRepositorio.existsByCodigoIgnoreCase(obrasFonograficas.getCodigo())) {
            throw new GlobalExceptionNoEncontrada("Ya existe la obra fonografica con  \nTitulo: "
                    + obrasFonograficas.getTituloObraFonografica()  + "\nCodigo: "
                    + obrasFonograficas.getCodigo());
        }
        obrasFonograficas.setFechaCreacion(LocalDateTime.now());
        return obrasFonograficasRepositorio.save(obrasFonograficas);
    }

    // Actualizar una obra fonográfica existente
    public ObrasFonograficas actualizarObraFonografica(Long idObrasFonograficas, ObrasFonograficas formulario) {
        ObrasFonograficas obraFonograficasFromDB = obtenerObraFonograficaPorId(idObrasFonograficas);
        //Valida si ya existe una obra con el mismo codigo 
        if (obrasFonograficasRepositorio.existsByCodigoIgnoreCaseAndIdObrasFonograficasNot(formulario.getCodigo(), idObrasFonograficas)) {
            throw new GlobalExceptionNoEncontrada("Ya existe la obra fonografica con  \nTitulo: "
                    + formulario.getTituloObraFonografica() + "\nCodigo: " + formulario.getCodigo());
        }
        obraFonograficasFromDB.setTituloObraFonografica(formulario.getTituloObraFonografica());
        obraFonograficasFromDB.setDescripcion(formulario.getDescripcion());
        obraFonograficasFromDB.setCodigo(formulario.getCodigo());
        obraFonograficasFromDB.setDuracion(formulario.getDuracion());
        obraFonograficasFromDB.setFechaLanzamiento(formulario.getFechaLanzamiento());
        obraFonograficasFromDB.setImagenPath(formulario.getImagenPath());    
        obraFonograficasFromDB.setActivo(formulario.getActivo());
        return obrasFonograficasRepositorio.save(obraFonograficasFromDB);
        
    }

    // Eliminar una obra fonográfica
    public void eliminarObraFonografica(Long idObrasFonograficas) {
        ObrasFonograficas obraFonograficasFromDB = obtenerObraFonograficaPorId(idObrasFonograficas);
        obrasFonograficasRepositorio.delete(obraFonograficasFromDB);
    }
}