package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Modulo;
import com.casadelacultura.casadelacultura.repositorio.ModuloRepositorio;
import lombok.AllArgsConstructor;

/**
 * Servicio encargado de gestionar las operaciones CRUD para la entidad Modulo.
 * 
 * @Service Marca la clase como un servicio gestionado por Spring.
 * @AllArgsConstructor Genera un constructor con todas las dependencias.
 */
@AllArgsConstructor
@Service
public class ModuloServicio {

    /**
     * Repositorio para realizar operaciones de persistencia en la entidad Modulo.
     */
    private final ModuloRepositorio moduloRepositorio;

    /**
     * Retorna todos los registros de Modulo almacenados en la base de datos.
     * 
     * @return Iterable<Modulo> Lista de todos los módulos.
     */
    public Iterable<Modulo> findAll() {
        return moduloRepositorio.findAll();
    }

    /**
     * Busca y retorna un Modulo por su ID.
     * 
     * @param idModulo Identificador del módulo a buscar.
     * @return Modulo El módulo encontrado o null si no existe.
     */
    public Modulo findById(Integer idModulo) {
        return moduloRepositorio.findById(idModulo).orElse(null);
    }

    /**
     * Crea un nuevo Modulo y lo guarda en la base de datos.
     * 
     * @param modulo Objeto Modulo a ser creado.
     * @return Modulo El módulo guardado en la base de datos.
     */
    public Modulo create(Modulo modulo) {
        return moduloRepositorio.save(modulo);
    }

    /**
     * Actualiza un Modulo existente en la base de datos.
     * 
     * @param idModulo ID del módulo a actualizar.
     * @param formulario Datos actualizados del Modulo.
     * @return Modulo El módulo actualizado.
     */
    public Modulo update(Integer idModulo, Modulo formulario) {
        Modulo moduloFromDB = findById(idModulo); // Busca el módulo existente.
        moduloFromDB.setNombreModulo(formulario.getNombreModulo()); // Actualiza el nombre.
        return moduloRepositorio.save(moduloFromDB);
    }

    /**
     * Elimina un Modulo por su ID.
     * 
     * @param idModulo Identificador del módulo a eliminar.
     */
    public void delete(Integer idModulo) {
        Modulo moduloFromDB = findById(idModulo); // Busca el módulo a eliminar.
        if (moduloFromDB != null) {
            moduloRepositorio.delete(moduloFromDB);
        }
    }
}
