package com.casadelacultura.casadelacultura.servicio;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.casadelacultura.casadelacultura.entity.Auditoria;
import com.casadelacultura.casadelacultura.entity.Cuestionario;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.AuditoriaRepositorio;
import com.casadelacultura.casadelacultura.repositorio.CuestionarioRepositorio;
import lombok.*;

@AllArgsConstructor
@Service
public class CuestionarioServicio {
    private final CuestionarioRepositorio cuestionarioRepositorio;
    private final AuditoriaRepositorio auditoriaRepositorio;

    // Obtener todos los cuestionarios
    public Iterable<Cuestionario> listarCuestionarios() {
        return cuestionarioRepositorio.findAll();
    }

    // Obtener un cuestionario por ID
    public Cuestionario obtenerCuestionarioPorId(Long idCuestionario) {
        return cuestionarioRepositorio.findById(idCuestionario)
                .orElseThrow(() -> new GlobalExceptionNoEncontrada(
                        "No se encontro el cuestionario con el ID " + idCuestionario));
    }

    public Cuestionario crearCuestionario(Cuestionario cuestionario) {
        // Valida si ya existe un cuestionario con los mismos datos
        if (cuestionarioRepositorio.existsByNombreCuestionarioAndInstrucciones(
                cuestionario.getNombreCuestionario(), cuestionario.getInstrucciones())) {
            throw new GlobalExceptionNoEncontrada("Ya existe el cuestionario con el nombre: "
                    + cuestionario.getNombreCuestionario() + "Con instrucciones " + cuestionario.getInstrucciones());

        }

        // Guardar el nuevo cuestionario
        Cuestionario nuevoCuestionario = cuestionarioRepositorio.save(cuestionario);

        // Registrar la auditoría para la creación del cuestionario
        registrarAuditoria("Cuestionario", nuevoCuestionario.getIdCuestionario(), "CREAR", null, null,
                nuevoCuestionario.getNombreCuestionario());

        return nuevoCuestionario;
    }

    
    public Cuestionario actualizarCuestionario(Long idCuestionario, Cuestionario formulario) {
        // Obtener el cuestionario existente
        Cuestionario cuestionarioFromDB = obtenerCuestionarioPorId(idCuestionario);
    
        // Validar si ya existe un cuestionario con el mismo nombre e instrucciones
        if (cuestionarioRepositorio.existsByNombreCuestionarioAndInstruccionesAndIdCuestionarioNot(
                formulario.getNombreCuestionario(),
                formulario.getInstrucciones(),
                idCuestionario)) {
            throw new GlobalExceptionNoEncontrada("Ya existe un cuestionario con el nombre: " +
                    formulario.getNombreCuestionario() + " y las instrucciones " + formulario.getInstrucciones());
        }
    
        // Comparar valores anteriores y nuevos para la auditoría
        String valorAnteriorNombre = cuestionarioFromDB.getNombreCuestionario();
        String valorNuevoNombre = formulario.getNombreCuestionario();
        String valorAnteriorInstrucciones = cuestionarioFromDB.getInstrucciones();
        String valorNuevoInstrucciones = formulario.getInstrucciones();
    
        // Actualizar los campos
        cuestionarioFromDB.setCalificacion(formulario.getCalificacion());
        cuestionarioFromDB.setNombreCuestionario(formulario.getNombreCuestionario());
        cuestionarioFromDB.setInstrucciones(formulario.getInstrucciones());
    
        // Guardar los cambios en la base de datos
        Cuestionario cuestionarioActualizado = cuestionarioRepositorio.save(cuestionarioFromDB);
    
        // Registrar auditoría solo si los valores han cambiado
        if (!valorAnteriorNombre.equals(valorNuevoNombre)) {
            registrarAuditoria("Cuestionario",idCuestionario,"ACTUALIZAR","nombreCuestionario",valorAnteriorNombre,valorNuevoNombre);
        }
    
        if (!valorAnteriorInstrucciones.equals(valorNuevoInstrucciones)) {
            registrarAuditoria(
                    "Cuestionario",
                    idCuestionario,
                    "ACTUALIZAR",
                    "instrucciones",
                    valorAnteriorInstrucciones,
                    valorNuevoInstrucciones
            );
        }
    
        return cuestionarioActualizado;
    }

    // Eliminar un cuestionario por ID
    public void eliminarCuestionario(Long idCuestionario) {
        // Obtener el cuestionario desde la base de datos
        Cuestionario cuestionarioFromDB = obtenerCuestionarioPorId(idCuestionario);

        // Registrar la auditoría para la eliminación del cuestionario
        registrarAuditoria("Cuestionario", cuestionarioFromDB.getIdCuestionario(), "ELIMINAR", null,
                cuestionarioFromDB.getNombreCuestionario(), null);

        // Eliminar el cuestionario
        cuestionarioRepositorio.delete(cuestionarioFromDB);
    }

    private void registrarAuditoria(String entidad, Long idEntidad, String accion, String campo, String valorAnterior,
            String valorNuevo) {
        Auditoria auditoria = new Auditoria(entidad, idEntidad, accion, LocalDateTime.now(), valorAnterior, valorNuevo);
        auditoriaRepositorio.save(auditoria);
    }
}
