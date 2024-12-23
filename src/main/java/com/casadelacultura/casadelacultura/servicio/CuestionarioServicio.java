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
        cuestionario.setFechaCreacion(LocalDateTime.now());
        // Valida si ya existe un cuestionario con los mismos datos
        if (cuestionarioRepositorio.existsByNombreCuestionarioIgnoreCaseAndInstruccionIgnoreCase(
                cuestionario.getNombreCuestionario(), cuestionario.getInstruccion())) {
            throw new GlobalExceptionNoEncontrada("Ya existe el cuestionario con el nombre: "
                    + cuestionario.getNombreCuestionario() + " Con instrucciones " + cuestionario.getInstruccion());

        }

        // Guardar el nuevo cuestionario
        Cuestionario nuevoCuestionario = cuestionarioRepositorio.save(cuestionario);

        // Registrar la auditoría para la creación del cuestionario
        registrarAuditoria("Cuestionario", nuevoCuestionario.getIdCuestionario(), "CREAR", null, null,
                nuevoCuestionario.getIdCuestionario().toString(), "Tabla");

        return nuevoCuestionario;
    }

    public Cuestionario actualizarCuestionario(Long idCuestionario, Cuestionario formulario) {
        // Obtener el cuestionario existente
        Cuestionario cuestionarioFromDB = obtenerCuestionarioPorId(idCuestionario);

        // Validar si ya existe un cuestionario con el mismo nombre e instrucciones
        if (cuestionarioRepositorio.existsByNombreCuestionarioIgnoreCaseAndInstruccionIgnoreCaseAndIdCuestionarioNot(
                formulario.getNombreCuestionario(),
                formulario.getInstruccion(),
                idCuestionario)) {
            throw new GlobalExceptionNoEncontrada("Ya existe un cuestionario con el nombre: " +
                    formulario.getNombreCuestionario() + " y las instrucciones " + formulario.getInstruccion());
        }

        // Comparar valores anteriores y nuevos para la auditoría
        String valorAnteriorNombre = cuestionarioFromDB.getNombreCuestionario();
        String valorNuevoNombre = formulario.getNombreCuestionario();
        String valorAnteriorInstrucciones = cuestionarioFromDB.getInstruccion();
        String valorNuevoInstrucciones = formulario.getInstruccion();

        // Actualizar los campos
       
        cuestionarioFromDB.setNombreCuestionario(formulario.getNombreCuestionario());
        cuestionarioFromDB.setInstruccion(formulario.getInstruccion());

        // Guardar los cambios en la base de datos
        Cuestionario cuestionarioActualizado = cuestionarioRepositorio.save(cuestionarioFromDB);

        // Registrar auditoría solo si los valores han cambiado
        if (!valorAnteriorNombre.equals(valorNuevoNombre)) {
            registrarAuditoria("Cuestionario", idCuestionario, "ACTUALIZAR", "nombreCuestionario", valorAnteriorNombre,
                    valorNuevoNombre, "nombreCuestionario");
        }

        if (!valorAnteriorInstrucciones.equals(valorNuevoInstrucciones)) {
            registrarAuditoria(
                    "Cuestionario",
                    idCuestionario,
                    "ACTUALIZAR",
                    "instrucciones",
                    valorAnteriorInstrucciones,
                    valorNuevoInstrucciones,
                    "instrucciones");
        }

        return cuestionarioActualizado;
    }

    // Eliminar un cuestionario por ID
    public void eliminarCuestionario(Long idCuestionario) {
        // Obtener el cuestionario desde la base de datos
        Cuestionario cuestionarioFromDB = obtenerCuestionarioPorId(idCuestionario);

        // Registrar la auditoría para la eliminación del cuestionario
        registrarAuditoria("Cuestionario", cuestionarioFromDB.getIdCuestionario(), "ELIMINAR", null,
                cuestionarioFromDB.getIdCuestionario().toString(), null, "Tabla");

        // Eliminar el cuestionario
        cuestionarioRepositorio.delete(cuestionarioFromDB);
    }

    private void registrarAuditoria(String entidad, Long idEntidad, String accion, String campo, String valorAnterior,
            String valorNuevo, String nombreColumna) {
        Auditoria auditoria = new Auditoria(entidad, idEntidad, accion, LocalDateTime.now(), valorAnterior, valorNuevo,
                nombreColumna);
        auditoriaRepositorio.save(auditoria);
    }
}
