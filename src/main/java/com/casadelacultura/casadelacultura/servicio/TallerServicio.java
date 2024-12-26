package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;

import com.casadelacultura.casadelacultura.entity.Auditoria;
import com.casadelacultura.casadelacultura.entity.Taller;
import com.casadelacultura.casadelacultura.entity.TipoTaller;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.AuditoriaRepositorio;
import com.casadelacultura.casadelacultura.repositorio.TallerRepositorio;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class TallerServicio {
    private final TallerRepositorio tallerRepositorio;
    private final TipoTallerServicio tipoTallerServicio;
    private final AuditoriaRepositorio auditoriaRepositorio;

    // Obtener todos los talleres
    public Iterable<Taller> obtenerTodosTalleres() {
        return tallerRepositorio.findAll();
    }

    // Obtener un taller por su ID
    public Taller obtenerTallerPorId(Long idTaller) {
        return tallerRepositorio.findById(idTaller).orElseThrow(
                () -> new GlobalExceptionNoEncontrada("No se encontro el Taller con el ID: " + idTaller));
    }

    // Crear un nuevo taller
    public Taller crearTaller(Taller taller) {
        taller.setFechaCreacion(LocalDateTime.now());
        // Valida que no exista otro taller
        if (tallerRepositorio.existsByTituloTallerIgnoreCaseAndClaveIgnoreCase(taller.getTituloTaller(),
                taller.getClave())) {
            throw new GlobalExceptionNoEncontrada("Ya existe un taller con el mismo Titulo: " + taller.getTituloTaller()
                    + ", con Clave: " + taller.getClave());

        }
        // Valida si el id de tipo taller existe
        TipoTaller tipoTaller = tipoTallerServicio.findById(taller.getTipoTaller().getIdTipoTaller());
        taller.setTipoTaller(tipoTaller);

        Taller nuevoTaller = tallerRepositorio.save(taller);

        // Registra auditoria
        // registrarAuditoria("Taller", nuevoTaller.getIdTaller(), "CREAR", "idTaller",
        // null,taller.getIdTaller().toString(), "Tabla");
        // Registra auditoría para los campos relevantes
        registrarCambioCrear("tituloTaller", nuevoTaller.getIdTaller(), null, nuevoTaller.getTituloTaller());
        /* 
        registrarCambioCrear("descripcion", nuevoTaller.getIdTaller(), null, nuevoTaller.getDescripcion());
        registrarCambioCrear("tipoTaller", nuevoTaller.getIdTaller(), null,
                nuevoTaller.getTipoTaller().getNombreTipoTaller());
        registrarCambioCrear("fechaInico", nuevoTaller.getIdTaller(), null, nuevoTaller.getFechaInico());
        registrarCambioCrear("fechaFinal", nuevoTaller.getIdTaller(), null, nuevoTaller.getFechaFinal());
        registrarCambioCrear("clave", nuevoTaller.getIdTaller(), null, nuevoTaller.getClave());
        registrarCambioCrear("imagenPath", nuevoTaller.getIdTaller(), null, nuevoTaller.getImagenPath());
        */
        return nuevoTaller;
    }

    // Actualizar un taller existente
    public Taller actualizarTaller(Long idTaller, Taller formulario) {
        Taller tallerFromDB = obtenerTallerPorId(idTaller);

        // Valida si ya existe un taller
        if (tallerRepositorio.existsByTituloTallerIgnoreCaseAndClaveIgnoreCaseAndIdTallerNot(
                formulario.getTituloTaller(), formulario.getClave(), idTaller)) {
            throw new GlobalExceptionNoEncontrada("Ya existe un taller con el mismo Titulo: "
                    + formulario.getTituloTaller() + ", con Clave: " + formulario.getClave());
        }

        // Valida si el id del tipo taller existe
        TipoTaller tipoTaller = tipoTallerServicio.findById(formulario.getTipoTaller().getIdTipoTaller());
        formulario.setTipoTaller(tipoTaller);

        //

        registrarCambio("tituloTaller", idTaller, tallerFromDB.getTituloTaller(), formulario.getTituloTaller());
        registrarCambio("descripcion", idTaller, tallerFromDB.getDescripcion(), formulario.getDescripcion());
        registrarCambio("clave", idTaller, tallerFromDB.getClave(), formulario.getClave());
        registrarCambio("fechaInico", idTaller, tallerFromDB.getFechaInico(), formulario.getFechaInico());
        registrarCambio("fechaFinal", idTaller, tallerFromDB.getFechaFinal(), formulario.getFechaFinal());
        registrarCambio("imagenPath", idTaller, tallerFromDB.getImagenPath(), formulario.getImagenPath());
        registrarCambio("tipoTaller", idTaller, tallerFromDB.getTipoTaller().getIdTipoTaller().toString(),
                formulario.getTipoTaller().getIdTipoTaller().toString());
        registrarCambio("tituloTaller", idTaller, tallerFromDB.getTituloTaller(), formulario.getTituloTaller());

        tallerFromDB.setTituloTaller(formulario.getTituloTaller());
        tallerFromDB.setClave(formulario.getClave());
        tallerFromDB.setDescripcion(formulario.getDescripcion());
        tallerFromDB.setFechaInico(formulario.getFechaInico());
        tallerFromDB.setFechaFinal(formulario.getFechaFinal());
        tallerFromDB.setTipoTaller(formulario.getTipoTaller());
        tallerFromDB.setImagenPath(formulario.getImagenPath());
        tallerFromDB.setEstaActivo(formulario.isEstaActivo());
        tallerFromDB.setAvanceGeneral(formulario.getAvanceGeneral());
        

        Taller tallerActualizado = tallerRepositorio.save(tallerFromDB);
        return tallerActualizado;

    }

    // Eliminar un taller
    public void eliminarTaller(Long idTaller) {
        Taller tallerFromDB = obtenerTallerPorId(idTaller);
        registrarAuditoria("Taller", tallerFromDB.getIdTaller(), "ELIMINAR", null, tallerFromDB.getIdTaller().toString(), null, "Tabla");
        tallerRepositorio.delete(tallerFromDB);
    }

    private void registrarAuditoria(String entidad, Long idEntidad, String accion, String campo, String valorAnterior,
            String valorNuevo, String nombreColumna) {
        Auditoria auditoria = new Auditoria(entidad, idEntidad, accion, LocalDateTime.now(), valorAnterior, valorNuevo,
                nombreColumna);
        auditoriaRepositorio.save(auditoria);
    }

    private void registrarCambio(String atributo, Long idTaller, Object valorAnterior, Object valorNuevo) {
        if (valorAnterior == null && valorNuevo == null) {
            return;
        }
        if (valorAnterior == null || !valorAnterior.equals(valorNuevo)) {
            registrarAuditoria("Taller", idTaller, "ACTUALIZAR", atributo,
                    valorAnterior == null ? "null" : valorAnterior.toString(),
                    valorNuevo == null ? "null" : valorNuevo.toString(), atributo);
        }
    }

    private void registrarCambioCrear(String atributo, Long idTaller, Object valorAnterior, Object valorNuevo) {
        if (valorAnterior == null && valorNuevo == null) {
            return;
        }
        if (valorAnterior == null || !valorAnterior.equals(valorNuevo)) {
            registrarAuditoria("Taller", idTaller, "CREAR", atributo,
                    valorAnterior == null ? "null" : valorAnterior.toString(),
                    valorNuevo == null ? "null" : valorNuevo.toString(), "Tabla");
        }
    }

}
