package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import com.casadelacultura.casadelacultura.entity.Auditoria;
import com.casadelacultura.casadelacultura.entity.TipoTaller;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.AuditoriaRepositorio;
import com.casadelacultura.casadelacultura.repositorio.TipoTallerRepositorio;
import lombok.*;


@AllArgsConstructor
@Service
public class TipoTallerServicio {
    private final TipoTallerRepositorio tipoTallerRepositorio;
    private final AuditoriaRepositorio auditoriaRepositorio;

    /**
     * Retorna todos los registros de TipoTaller almacenados en la base de datos.
     * 
     * @return Iterable<TipoTaller> Lista de todos los tipos de talleres.
     */
    public Iterable<TipoTaller> findAll() {
        return tipoTallerRepositorio.findAll();

    }

    /**
     * Busca y retorna un TipoTaller por su ID.
     * 
     * @param idTipoTaller Identificador del tipo de taller que se desea buscar.
     * @return TipoTaller El tipo de taller encontrado o null si no existe.
     */
    public TipoTaller findById(Long idTipoTaller){
        return tipoTallerRepositorio.findById(idTipoTaller)
                .orElseThrow(() -> new GlobalExceptionNoEncontrada(
                        "No se encontro el tipo taller con el ID " + idTipoTaller));

    }

    /**
     * Crea un nuevo TipoTaller y lo guarda en la base de datos.
     * 
     * @param tipoTaller Objeto TipoTaller a ser creado.
     * @return TipoTaller El objeto guardado en la base de datos.
     */
    public TipoTaller create(TipoTaller tipoTaller){
        tipoTaller.setFechaCreacion(LocalDateTime.now()); //Se le asigna una fecha de creacion
        //Valida si ya existe una categoria 
        // Normaliza el nombre a minúsculas para la validación

        if (tipoTallerRepositorio.existsByNombreTipoTallerIgnoreCase(tipoTaller.getNombreTipoTaller())) {
            throw new GlobalExceptionNoEncontrada("Ya existe el tipo taller con el nombre: "+ tipoTaller.getNombreTipoTaller());
            
        }
        TipoTaller nuevoTipoTaller = tipoTallerRepositorio.save(tipoTaller);

        //Registra auditoria 
        registrarAuditoria("tipoTaller", nuevoTipoTaller.getIdTipoTaller(), "CREAR", null, null, nuevoTipoTaller.getIdTipoTaller().toString(), "Tabla");

        return nuevoTipoTaller; 

    }

    /**
     * Actualiza un TipoTaller existente en la base de datos.
     * 
     * @param idTipoTaller ID del tipo de taller que se desea actualizar.
     * @param formulario Datos actualizados del TipoTaller.
     * @return TipoTaller El objeto actualizado.
     */
    public TipoTaller update(Long idTipoTaller,TipoTaller formulario){
        TipoTaller tipoTallerFromDB = findById(idTipoTaller);// Busca el tipo de taller existente.
        if (tipoTallerRepositorio.existsByNombreTipoTallerIgnoreCaseAndIdTipoTallerNot(formulario.getNombreTipoTaller(), idTipoTaller)) {
            throw new GlobalExceptionNoEncontrada("Ya existe el tipo taller con el nombre: "+ formulario.getNombreTipoTaller());
        }
        //Compara valores anteriores y nuevos para la uditoria
        String valorAnterioNombreTipoTaller = tipoTallerFromDB.getNombreTipoTaller();
        String valorNuevoNombreTipoTaller = formulario.getNombreTipoTaller();

        String valorAnterioDescripcion = tipoTallerFromDB.getDescripcion();
        String valorNuevoDescripcion = formulario.getDescripcion();


        tipoTallerFromDB.setNombreTipoTaller(formulario.getNombreTipoTaller()); // Actualiza el nombre.
        tipoTallerFromDB.setDescripcion(formulario.getDescripcion()); // Actualiza la descripción.

        TipoTaller tipoTallerActualizado = tipoTallerRepositorio.save(tipoTallerFromDB); 

        //Registra Auditoria
        if (!valorAnterioNombreTipoTaller.equals(valorNuevoNombreTipoTaller)) {
            registrarAuditoria("tipoTaller", idTipoTaller, "ACTUALIZAR", "nombreTipoTaller", valorAnterioNombreTipoTaller, valorNuevoNombreTipoTaller, "nombreTipoTaller");
            
        }

        if (!valorAnterioDescripcion.equals(valorNuevoDescripcion)) {
            registrarAuditoria("tipoTaller", idTipoTaller, "ACTUALIZAR", "descripcion", valorAnterioDescripcion, valorNuevoDescripcion, "descripcion");
            
        }

        return tipoTallerActualizado;

    }

    /**
     * Elimina un TipoTaller por su ID.
     * 
     * @param idTipoTaller Identificador del tipo de taller que se desea eliminar.
     */
    public void delate(Long idTipoTaller){
        TipoTaller tipoTallerFromDB = findById(idTipoTaller);// Busca el tipo de taller a eliminar.
        registrarAuditoria("tipoTaller", tipoTallerFromDB.getIdTipoTaller(), "ELIMINAR", null, tipoTallerFromDB.getIdTipoTaller().toString(), null, "Tabla");
        tipoTallerRepositorio.delete(tipoTallerFromDB); 
    }

    //Registra auditoria
    private void registrarAuditoria(String entidad, Long idEntidad, String accion, String campo, String valorAnterior,
            String valorNuevo, String nombreColumna) {
        Auditoria auditoria = new Auditoria(entidad, idEntidad, accion, LocalDateTime.now(), valorAnterior, valorNuevo,
                nombreColumna);
        auditoriaRepositorio.save(auditoria);
    }
}
