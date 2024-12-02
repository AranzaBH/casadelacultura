package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Auditoria;
import com.casadelacultura.casadelacultura.repositorio.AuditoriaRepositorio;
import java.time.LocalDateTime;

@Service
public class AuditoriaServicio {

    @Autowired
    private AuditoriaRepositorio auditoriaRepositorio;

    // Método para registrar auditoría con los valores anteriores y nuevos
    public void registrarAuditoria(String entidad, Long idEntidad, String accion, String campo, String valorAnterior,
            String valorNuevo) {
        Auditoria auditoria = new Auditoria();
        auditoria.setEntidad(entidad);
        auditoria.setIdEntidad(idEntidad);
        auditoria.setAccion(accion);
        auditoria.setFechaHora(LocalDateTime.now());
        auditoria.setValorAnterior(valorAnterior);
        auditoria.setValorNuevo(valorNuevo);
        auditoriaRepositorio.save(auditoria);
    }
}
