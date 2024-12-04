package com.casadelacultura.casadelacultura.servicio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Actividades;
import com.casadelacultura.casadelacultura.entity.Auditoria;
import com.casadelacultura.casadelacultura.entity.Cuestionario;
import com.casadelacultura.casadelacultura.entity.Taller;
import com.casadelacultura.casadelacultura.entity.Video;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.ActividadesRepositorio;
import com.casadelacultura.casadelacultura.repositorio.AuditoriaRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ActividadesServicio {
    private final ActividadesRepositorio actividadesRepositorio;
    private final AuditoriaRepositorio auditoriaRepositorio;
    private final VideoServicio videoServicio;
    private final CuestionarioServicio cuestionarioServicio;
    private final TallerServicio tallerServicio;

    // Obtener todas las actividades
    public Iterable<Actividades> listarActividades() {
        return actividadesRepositorio.findAllActividadesWithTaller();
        // return actividadesRepositorio.findAll();
    }

    // Obtener una actividad por ID
    public Actividades obtenerActividadPorId(Long idActividad) {
        return actividadesRepositorio.findById(idActividad).orElseThrow(
                () -> new GlobalExceptionNoEncontrada("No se encontro la actividad con el ID: " + idActividad));
    }

    // Crear una nueva actividad
    public Actividades crearActividad(Actividades actividad) {
        // Valida si ya existe una actividad con esos mismos datos
        if (actividadesRepositorio
                .existsByNombreActividadIgnoreCaseAndVideo_IdVideoAndCuestionario_IdCuestionarioAndTaller_IdTaller(
                        actividad.getNombreActividad(),
                        actividad.getVideo().getIdVideo(),
                        actividad.getCuestionario() != null ? actividad.getCuestionario().getIdCuestionario() : null,
                        actividad.getTaller().getIdTaller())) {
            throw new GlobalExceptionNoEncontrada(
                    "Ya existe una actividad con el nombre '" + actividad.getNombreActividad() +
                            "', el video con ID " + actividad.getVideo().getIdVideo() +
                            (actividad.getCuestionario() != null
                                    ? ", en el cuestionario con ID " + actividad.getCuestionario().getIdCuestionario()
                                    : "")
                            +
                            " y en el taller con ID " + actividad.getTaller().getIdTaller());
        }

        // Validar si ya existe un video
        Video video = videoServicio.obtenerVideoPorId(actividad.getVideo().getIdVideo());
        actividad.setVideo(video);

        // Valida si ya existe el cuestionario
        if (actividad.getCuestionario() != null) {
            Cuestionario cuestionario = cuestionarioServicio
                    .obtenerCuestionarioPorId(actividad.getCuestionario().getIdCuestionario());
            actividad.setCuestionario(cuestionario); // Opcional: asegura que se persista el cuestionario encontrado
        }

        // Validar si existe el taller
        Taller taller = tallerServicio.obtenerTallerPorId(actividad.getTaller().getIdTaller());
        actividad.setTaller(taller);

        Actividades nuevaActividad = actividadesRepositorio.save(actividad);

        // Registra auditoria
        registrarAuditoria("Actividad", nuevaActividad.getIdActividades(), "CREAR", "idActividades", null,
                actividad.getIdActividades().toString(), "Tabla");

        return actividadesRepositorio.save(nuevaActividad);
    }

    // Actualizar una actividad existente
    public Actividades actualizarActividad(Long idActividad, Actividades formulario) {
        Actividades actividadesFromDB = obtenerActividadPorId(idActividad);
        // Valida si ya existe una actividad con los mismos datos
        if (actividadesRepositorio
                .existsByNombreActividadIgnoreCaseAndVideo_IdVideoAndCuestionario_IdCuestionarioAndTaller_IdTallerAndIdActividadesNot(
                        formulario.getNombreActividad(),
                        formulario.getVideo().getIdVideo(),
                        formulario.getCuestionario() != null ? formulario.getCuestionario().getIdCuestionario() : null,
                        formulario.getTaller().getIdTaller(),
                        idActividad)) {
            throw new GlobalExceptionNoEncontrada(
                    "Ya existe una actividad con el nombre '" + formulario.getNombreActividad() +
                            "', el video con ID " + formulario.getVideo().getIdVideo() +
                            (formulario.getCuestionario() != null
                                    ? ", en el cuestionario con ID " + formulario.getCuestionario().getIdCuestionario()
                                    : "")
                            +
                            " y en el taller con ID " + formulario.getTaller().getIdTaller());

        }

        // Valida si el id del video existe
        Video video = videoServicio.obtenerVideoPorId(formulario.getVideo().getIdVideo());
        formulario.setVideo(video);
        // Valiida si el id del custionario existe
        if (formulario.getCuestionario() != null) {
            Cuestionario cuestionario = cuestionarioServicio
                    .obtenerCuestionarioPorId(formulario.getCuestionario().getIdCuestionario());
            formulario.setCuestionario(cuestionario); // Opcional: asegura que se persista el cuestionario encontrado
        }
        // Valida si el id del taller existe
        Taller taller = tallerServicio.obtenerTallerPorId(formulario.getTaller().getIdTaller());
        formulario.setTaller(taller);

        // Comparar valores anteriores y nuevos para la auditoría
        String valorAnteriorNombreActividad = actividadesFromDB.getNombreActividad();
        String valorNuevoNombreActividad = formulario.getNombreActividad();
        Long valorAnteriorVideoId = actividadesFromDB.getVideo().getIdVideo();
        Long valorNuevoVideoId = formulario.getVideo().getIdVideo();
        Long valorAnteriorCuestionarioId = actividadesFromDB.getCuestionario() != null
                ? actividadesFromDB.getCuestionario().getIdCuestionario()
                : null;
        Long valorNuevoCuestionarioId = formulario.getCuestionario() != null
                ? formulario.getCuestionario().getIdCuestionario()
                : null;
        Long valorAnteriorTallerId = actividadesFromDB.getTaller().getIdTaller();
        Long valorNuevoTallerId = formulario.getTaller().getIdTaller();

        actividadesFromDB.setNombreActividad(formulario.getNombreActividad());
        actividadesFromDB.setVideo(formulario.getVideo());
        actividadesFromDB.setCuestionario(formulario.getCuestionario());
        actividadesFromDB.setTaller(formulario.getTaller());
        actividadesFromDB.setModulo(formulario.getModulo());
        actividadesFromDB.setAvance(formulario.getAvance());
        actividadesFromDB.setEstatus(formulario.isEstatus());

        Actividades actividadActualizada = actividadesRepositorio.save(actividadesFromDB);
        // Registrar auditoría si hubo cambios
        if (!valorAnteriorNombreActividad.equals(valorNuevoNombreActividad)) {
            registrarAuditoria("Actividad", idActividad, "ACTUALIZAR", "nombreActividad", valorAnteriorNombreActividad,
                    valorNuevoNombreActividad, "nombreActividad");
        }
        if (!valorAnteriorVideoId.equals(valorNuevoVideoId)) {
            registrarAuditoria("Actividad", idActividad, "ACTUALIZAR", "videoId",
                    "idVideo: " + valorAnteriorVideoId.toString(),
                    "idVideo: " + valorNuevoVideoId.toString(), "idVideo");
        }
        if (!Objects.equals(valorAnteriorCuestionarioId, valorNuevoCuestionarioId)) {
            registrarAuditoria("Actividad", idActividad, "ACTUALIZAR", "cuestionarioId",
                    valorAnteriorCuestionarioId != null ? valorAnteriorCuestionarioId.toString() : "null",
                    valorNuevoCuestionarioId != null ? valorNuevoCuestionarioId.toString() : "null", "idCuestionario");
        }
        if (!valorAnteriorTallerId.equals(valorNuevoTallerId)) {
            registrarAuditoria("Actividad", idActividad, "ACTUALIZAR", "tallerId", valorAnteriorTallerId.toString(),
                    valorNuevoTallerId.toString(), "idTaller");
        }

        return actividadActualizada;

    }

    // Eliminar una actividad por ID
    public void eliminarActividad(Long idActividad) {
        Actividades actividadesFromDB = obtenerActividadPorId(idActividad);
        registrarAuditoria("Actividad", actividadesFromDB.getIdActividades(), "ELIMINAR", null,
                actividadesFromDB.getIdActividades().toString(), null, "Tabla");
        actividadesRepositorio.delete(actividadesFromDB);
    }

    // Listar actividades por taller
    public List<Actividades> listarActividadesPorTaller(Long idTaller) {
        // Utiliza el repositorio para obtener las actividades asociadas al taller
        return (List<Actividades>) actividadesRepositorio.findActividadesByTallerId(idTaller);
    }

    private void registrarAuditoria(String entidad, Long idEntidad, String accion, String campo, String valorAnterior,
            String valorNuevo, String nombreColumna) {
        Auditoria auditoria = new Auditoria(entidad, idEntidad, accion, LocalDateTime.now(), valorAnterior, valorNuevo,
                nombreColumna);
        auditoriaRepositorio.save(auditoria);
    }
}
