package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.casadelacultura.casadelacultura.entity.ObrasImagenes;

/**
 * Repositorio para manejar las operaciones CRUD relacionadas con la entidad {@link ObrasImagenes}.
 * Este repositorio extiende la interfaz {@link CrudRepository}, lo que permite realizar operaciones básicas 
 * como guardar, buscar, actualizar y eliminar registros en la base de datos.
 * 
 * Además, proporciona métodos personalizados para verificar la existencia de relaciones específicas entre obras e imágenes.
 */
public interface ObrasImagenesRepositorio extends CrudRepository <ObrasImagenes, Long>{
    /**
     * Verifica si existe una relación entre una obra específica y una imagen específica en la base de datos.
     * 
     * @param idObra El ID de la obra a buscar.
     * @param idImagenes El ID de la imagen a buscar.
     * @return {@code true} si existe una relación entre la obra e imagen especificadas, de lo contrario {@code false}.
     */
    boolean existsByObra_IdObraAndImagenes_IdImagen(Long idObra, Long idImagenes);

    /**
     * Verifica si existe una relación entre una obra específica y una imagen específica en la base de datos,
     * excluyendo un registro con un ID específico.
     * 
     * Este método es útil para evitar duplicados al actualizar un registro, ya que ignora el registro actual 
     * durante la validación.
     * 
     * @param idObra El ID de la obra a buscar.
     * @param idImagen El ID de la imagen a buscar.
     * @param idObrasImagenes El ID del registro que se debe excluir de la búsqueda.
     * @return {@code true} si existe una relación entre la obra e imagen especificadas, excluyendo el registro 
     * con el ID proporcionado, de lo contrario {@code false}.
     */
    boolean existsByObra_IdObraAndImagenes_IdImagenAndIdObrasImagenesNot(Long idObra, Long idImagen, Long idObrasImagenes);
}
