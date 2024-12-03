package com.casadelacultura.casadelacultura.servicio;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.casadelacultura.casadelacultura.entity.Auditoria;
import com.casadelacultura.casadelacultura.entity.Cuestionario;
import com.casadelacultura.casadelacultura.entity.Reactivo;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.AuditoriaRepositorio;
import com.casadelacultura.casadelacultura.repositorio.ReactivoRepositorio;

import lombok.*;

@AllArgsConstructor
@Service
public class ReactivoServicio {
    private final ReactivoRepositorio reactivoRepositorio;
    private final AuditoriaRepositorio auditoriaRepositorio;
    private final CuestionarioServicio cuestionarioServicio;

    // Obtener todos los reactivos
    public Iterable<Reactivo> listarReactivos() {
        return reactivoRepositorio.findAll();
    }

    // Obtener un reactivo por ID
    public Reactivo obtenerReactivoPorId(Long idReactivo) {
        return reactivoRepositorio.findById(idReactivo).orElseThrow(
                () -> new GlobalExceptionNoEncontrada("No se encontro el reactivo con el ID: " + idReactivo));
    }

    // Eliminar un reactivo por ID
    public void eliminarReactivo(Long idReactivo) {
        Reactivo reactivoFromDB = obtenerReactivoPorId(idReactivo);
        registrarAuditoria("Reactivo", reactivoFromDB.getIdReactivo(), "ELIMINAR", null,
                reactivoFromDB.getIdReactivo().toString(), null, "Tabla");
        reactivoRepositorio.delete(reactivoFromDB);
    }

    // Crear un nuevo reactivo
    public Reactivo crearReactivo(Reactivo reactivo) {
        // Buesca el cuestionario si no existe lanza una exepcion
        Cuestionario cuestionario = cuestionarioServicio
                .obtenerCuestionarioPorId(reactivo.getCuestionario().getIdCuestionario());

        // valida si existe el reactivo
        if (reactivoRepositorio.existsByPreguntaAndRespuestaCorrectaAndCuestionario_IdCuestionario(
                reactivo.getPregunta(),
                reactivo.getRespuestaCorrecta(),
                reactivo.getCuestionario().getIdCuestionario())) {
            throw new GlobalExceptionNoEncontrada("Ya existe un reactivo con la pregunta '" + reactivo.getPregunta() +
                    "' y respuesta correcta '" + reactivo.getRespuestaCorrecta() + "' en el cuestionario con ID " +
                    reactivo.getCuestionario().getIdCuestionario());
        }

        reactivo.setCuestionario(cuestionario);
        // Guardar el nuevo reactivo
        Reactivo nuevoReactivo = reactivoRepositorio.save(reactivo);

        // Registrar auditoría
        registrarAuditoria("Reactivo", nuevoReactivo.getIdReactivo(), "CREAR", "pregunta", null,
                reactivo.getIdReactivo().toString(), "Tabla");
        // registrarAuditoria("Reactivo",nuevoReactivo.getIdReactivo(),"CREAR","respuestaCorrecta",null,reactivo.getRespuestaCorrecta());
        // registrarAuditoria("Reactivo",nuevoReactivo.getIdReactivo(),"CREAR","respuestaCorrecta",null,reactivo.getRespuesta1());
        // registrarAuditoria("Reactivo",nuevoReactivo.getIdReactivo(),"CREAR","respuestaCorrecta",null,reactivo.getRespuesta2());
        // registrarAuditoria("Reactivo",nuevoReactivo.getIdReactivo(),"CREAR","respuestaCorrecta",null,reactivo.getRespuesta3());
        // registrarAuditoria("Reactivo",nuevoReactivo.getIdReactivo(),"CREAR","respuestaCorrecta",null,reactivo.getRespuesta4());
        // registrarAuditoria("Reactivo",nuevoReactivo.getIdReactivo(),"CREAR","respuestaCorrecta",null,reactivo.getRespuesta5());
        // registrarAuditoria("Reactivo",nuevoReactivo.getIdReactivo(),"CREAR","nombreCuestionario",null,"Nombre
        // cuestionario: "+cuestionario.getNombreCuestionario());
        return nuevoReactivo;
    }

    public Reactivo actualizarReactivo(Long idReactivo, Reactivo formulario) {
        // Obtener el reactivo existente de la base de datos
        Reactivo reactivoFromDB = obtenerReactivoPorId(idReactivo);
        // Validar si ya existe un reactivo con los mismos datos (excepto el actual)
        if (reactivoRepositorio.existsByPreguntaAndRespuestaCorrectaAndCuestionario_IdCuestionarioAndIdReactivoNot(
                formulario.getPregunta(),
                formulario.getRespuestaCorrecta(),
                formulario.getCuestionario().getIdCuestionario(),
                idReactivo)) {
            throw new GlobalExceptionNoEncontrada("Ya existe un reactivo con la pregunta '" + formulario.getPregunta() +
                    "' y respuesta correcta '" + formulario.getRespuestaCorrecta() + "' en el cuestionario con ID " +
                    formulario.getCuestionario().getIdCuestionario());
        }

        // Validar si el cuestionario asociado existe utilizando findById
        Cuestionario cuestionario = cuestionarioServicio
                .obtenerCuestionarioPorId(formulario.getCuestionario().getIdCuestionario());
        formulario.setCuestionario(cuestionario);

        // Comparar valores anteriores y nuevos para la auditoría
        String valorAnteriorPregunta = reactivoFromDB.getPregunta();
        String valorNuevoPregunta = formulario.getPregunta();
        String valorAnteriorRespuestaCorrecta = reactivoFromDB.getRespuestaCorrecta();
        String valorNuevoRespuestaCorrecta = formulario.getRespuestaCorrecta();
        String valorAnteriorRespuesta1 = reactivoFromDB.getRespuesta1();
        String valorNuevoRespuesta1 = formulario.getRespuesta1();
        String valorAnteriorRespuesta2 = reactivoFromDB.getRespuesta2();
        String valorNuevoRespuesta2 = formulario.getRespuesta2();
        String valorAnteriorRespuesta3 = reactivoFromDB.getRespuesta3();
        String valorNuevoRespuesta3 = formulario.getRespuesta3();
        String valorAnteriorRespuesta4 = reactivoFromDB.getRespuesta4();
        String valorNuevoRespuesta4 = formulario.getRespuesta4();
        String valorAnteriorRespuesta5 = reactivoFromDB.getRespuesta5();
        String valorNuevoRespuesta5 = formulario.getRespuesta5();
        String valorAnteriorNombreCuestionario = reactivoFromDB.getCuestionario().getNombreCuestionario();
        String valorNuevoNombreCuestionario = cuestionario.getNombreCuestionario();

        // Actualizar los campos
        reactivoFromDB.setPregunta(formulario.getPregunta());
        reactivoFromDB.setRespuestaCorrecta(formulario.getRespuestaCorrecta());
        reactivoFromDB.setRespuesta1(formulario.getRespuesta1());
        reactivoFromDB.setRespuesta2(formulario.getRespuesta2());
        reactivoFromDB.setRespuesta3(formulario.getRespuesta3());
        reactivoFromDB.setRespuesta4(formulario.getRespuesta4());
        reactivoFromDB.setRespuesta5(formulario.getRespuesta5());
        reactivoFromDB.setCuestionario(cuestionario); // Asignar el cuestionario actualizado

        // Guardar los cambios en la base de datos
        Reactivo reactivoActualizado = reactivoRepositorio.save(reactivoFromDB);

        // Registrar auditoría solo si los valores han cambiado
        if (!valorAnteriorPregunta.equals(valorNuevoPregunta)) {
            registrarAuditoria("Reactivo", idReactivo, "ACTUALIZAR", "pregunta", valorAnteriorPregunta,
                    valorNuevoPregunta, "pregunta");
        }
        if (!valorAnteriorRespuestaCorrecta.equals(valorNuevoRespuestaCorrecta)) {
            registrarAuditoria("Reactivo", idReactivo, "ACTUALIZAR", "respuestaCorrecta",
                    valorAnteriorRespuestaCorrecta, valorNuevoRespuestaCorrecta, "respuestaCorrecta");
        }
        if (!valorAnteriorRespuesta1.equals(valorNuevoRespuesta1)) {
            registrarAuditoria("Reactivo", idReactivo, "ACTUALIZAR", "respuesta1", valorAnteriorRespuesta1,
                    valorNuevoRespuesta1, "respuesta1");
        }
        if (!valorAnteriorRespuesta2.equals(valorNuevoRespuesta2)) {
            registrarAuditoria("Reactivo", idReactivo, "ACTUALIZAR", "respuesta2", valorAnteriorRespuesta2,
                    valorNuevoRespuesta2, "respuesta2");
        }
        if (!valorAnteriorRespuesta3.equals(valorNuevoRespuesta3)) {
            registrarAuditoria("Reactivo", idReactivo, "ACTUALIZAR", "respuesta3", valorAnteriorRespuesta3,
                    valorNuevoRespuesta3, "respuesta3");
        }
        if (!Objects.equals(valorAnteriorRespuesta4, valorNuevoRespuesta4)) {
            registrarAuditoria("Reactivo", idReactivo, "ACTUALIZAR", "respuesta4", valorAnteriorRespuesta4,
                    valorNuevoRespuesta4, "respuesta4");
        }
        if (!Objects.equals(valorAnteriorRespuesta5, valorNuevoRespuesta5)) {
            registrarAuditoria("Reactivo", idReactivo, "ACTUALIZAR", "respuesta5", valorAnteriorRespuesta5,
                    valorNuevoRespuesta5, "respuesta5");
        }
        if (!valorAnteriorNombreCuestionario.equals(valorNuevoNombreCuestionario)) {
            registrarAuditoria("Reactivo", idReactivo, "ACTUALIZAR", "nombreCuestionario",
                    valorAnteriorNombreCuestionario, valorNuevoNombreCuestionario, "nombreCuestionario");
        }

        return reactivoActualizado;
    }

    private void registrarAuditoria(String entidad, Long idEntidad, String accion, String campo, String valorAnterior,
            String valorNuevo, String nombreColumna) {
        Auditoria auditoria = new Auditoria(entidad, idEntidad, accion, LocalDateTime.now(), valorAnterior, valorNuevo,
                nombreColumna);
        auditoriaRepositorio.save(auditoria);
    }
}
