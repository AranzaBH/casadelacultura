package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;

import com.casadelacultura.casadelacultura.entity.Obra;
import com.casadelacultura.casadelacultura.entity.ObrasImagenes;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.ImagenesRepositorio;
import com.casadelacultura.casadelacultura.repositorio.ObraRepositorio;
import com.casadelacultura.casadelacultura.repositorio.ObrasImagenesRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ObrasImagenesServicio {
    private final ObrasImagenesRepositorio obrasImagenesRepositorio;
    private final ImagenesRepositorio imagenesRepositorio;
    private final ObraRepositorio obraRepositorio;

    public Iterable<ObrasImagenes> listarObrasImagenes() {
        return obrasImagenesRepositorio.findAll();
    }

    public ObrasImagenes obtenerRelacionPorId(Long idObrasImagenes) {
        return obrasImagenesRepositorio.findById(idObrasImagenes)
        .orElseThrow(() -> new GlobalExceptionNoEncontrada(
                "No se encontro la relacion libro por autor con el ID " + idObrasImagenes));
    }

    public ObrasImagenes crearRealacionObrasImagenes(ObrasImagenes obrasImagenes) {
        // Validar si la relación ya existe
        if (obrasImagenesRepositorio.existsByObra_IdObraAndImagenes_IdImagen(
                obrasImagenes.getObra().getIdObra(), obrasImagenes.getImagenes().getIdImagen())) {
            throw new GlobalExceptionNoEncontrada(
                    "Ya existe una relación para la obra con ID " + obrasImagenes.getObra().getIdObra() +
                            " y la imagen con ID " + obrasImagenes.getImagenes().getIdImagen());
        }
        // valida si la imagen existe
        if (obrasImagenes.getImagenes() == null
                || !imagenesRepositorio.existsById(obrasImagenes.getImagenes().getIdImagen())) {
            throw new GlobalExceptionNoEncontrada(
                    "Imagene con ID " + obrasImagenes.getImagenes().getIdImagen() + " no encontrado.");
        }
        // Valida si la obra ya existe
        if (obrasImagenes.getObra() == null
                || !obraRepositorio.existsById(obrasImagenes.getObra().getIdObra())) {
            throw new GlobalExceptionNoEncontrada(
                    "Obra con ID " + obrasImagenes.getObra().getIdObra() + " no encontrado.");
        }

        return obrasImagenesRepositorio.save(obrasImagenes);
    }

    public ObrasImagenes actualizarRelacionObraImagenes(Long idObrasImagenes, ObrasImagenes formulario) {
        ObrasImagenes obrasImagenesFromDB = obtenerRelacionPorId(idObrasImagenes);
        // Verificar si la relación ya existe, excluyendo un ID específico
        if (obrasImagenesRepositorio.existsByObra_IdObraAndImagenes_IdImagenAndIdObrasImagenesNot(
                formulario.getObra().getIdObra(),
                formulario.getImagenes().getIdImagen(),
                idObrasImagenes)) {
            // Obtener la obra correspondiente al ID
            Obra obra = obraRepositorio.findById(formulario.getObra().getIdObra())
                    .orElseThrow(() -> new GlobalExceptionNoEncontrada("Obra no encontrada"));
            // Lanzar la excepción con detalles en el mensaje
            throw new GlobalExceptionNoEncontrada(
                    "Ya existe una relación para la obra con título: " + obra.getTituloObra() +
                            " (ID: " + formulario.getObra().getIdObra() +
                            " ) y la imagen con ID " + formulario.getImagenes().getIdImagen());
        }

        // Valida si exsite la imagen
        if (formulario.getImagenes() == null
                || !imagenesRepositorio.existsById(formulario.getImagenes().getIdImagen())) {
            throw new GlobalExceptionNoEncontrada(
                    "Imagen con ID " + formulario.getImagenes().getIdImagen() + " no encontrado.");

        }

        if (formulario.getObra() == null
                || !obraRepositorio.existsById(formulario.getObra().getIdObra())) {
            throw new GlobalExceptionNoEncontrada(
                    "Obra con ID " + formulario.getObra().getIdObra() + " no encontrado.");

        }

        // Actualizar los datos de la relación
        obrasImagenesFromDB.setObra(formulario.getObra());
        obrasImagenesFromDB.setImagenes(formulario.getImagenes());

        return obrasImagenesRepositorio.save(obrasImagenesFromDB);
    }

    public void eliminarRelacionObrasImagenes(Long idObrasImagenes) {
        ObrasImagenes obrasImagenesFromDB = obtenerRelacionPorId(idObrasImagenes);
        obrasImagenesRepositorio.delete(obrasImagenesFromDB);
    }

    /*
     * // Validaciones
     * 
     * @ExceptionHandler(IllegalArgumentException.class)
     * public ResponseEntity<String>
     * handleIllegalArgumentException(IllegalArgumentException ex) {
     * return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
     * }
     * 
     * @ExceptionHandler(Exception.class)
     * public ResponseEntity<String> handleGeneralException(Exception ex) {
     * return new ResponseEntity<>("Ocurrió un error interno: " + ex.getMessage(),
     * HttpStatus.INTERNAL_SERVER_ERROR);
     * }
     */

}
