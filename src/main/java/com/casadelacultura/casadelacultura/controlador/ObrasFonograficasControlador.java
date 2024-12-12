package com.casadelacultura.casadelacultura.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.casadelacultura.casadelacultura.entity.ObrasFonograficas;
import com.casadelacultura.casadelacultura.servicio.ObrasFonograficasServicio;
import com.casadelacultura.casadelacultura.servicio.S3Service;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

// Controlador para manejar las operaciones CRUD de ObrasFonograficas
@AllArgsConstructor
@RestController
@RequestMapping("/api/obrasfonograficas")
@CrossOrigin("*")
public class ObrasFonograficasControlador {
    private final ObrasFonograficasServicio obrasFonograficasServicio;
    private final S3Service s3Service;

    // Obtener todas las obras fonográficas
    @GetMapping
    public Iterable<ObrasFonograficas> listarObrasFonograficas() {
        Iterable<ObrasFonograficas> obrasFonografica = obrasFonograficasServicio.listarObrasFonograficas();
        for (ObrasFonograficas obra : obrasFonografica) {
            if (obra.getImagenPath() != null && !obra.getImagenPath().isEmpty()) {
                String imagenUrl = s3Service.getObjectUrl(obra.getImagenPath());
                obra.setUrlImagenPortada(imagenUrl);
            }
        }
        return obrasFonografica;
    }

    // Obtener una obra fonográfica por ID
    @GetMapping("{idObrasFonograficas}")
    public ObrasFonograficas get(@PathVariable Long idObrasFonograficas) {
        ObrasFonograficas obrasFonograficas = obrasFonograficasServicio.obtenerObraFonograficaPorId(idObrasFonograficas);
        if (obrasFonograficas != null && obrasFonograficas.getImagenPath() != null && !obrasFonograficas.getImagenPath().isEmpty()) {
            String imagenUrl = s3Service.getObjectUrl(obrasFonograficas.getImagenPath());
            obrasFonograficas.setUrlImagenPortada(imagenUrl);
        }
        return obrasFonograficas;
    }

    // Crear una nueva obra fonográfica
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ObrasFonograficas create(@Valid @RequestBody ObrasFonograficas obrasFonograficas) {
        obrasFonograficas.setUrlImagenPortada(s3Service.getObjectUrl(obrasFonograficas.getImagenPath()));
        return obrasFonograficasServicio.crearObraFonografica(obrasFonograficas);
    }

    // Actualizar una obra fonográfica existente
    @PutMapping("{idObrasFonograficas}")
    public ObrasFonograficas update(@PathVariable Long idObrasFonograficas, @RequestBody @Valid ObrasFonograficas formulario) {
        if (formulario.getImagenPath() != null && !formulario.getImagenPath().isEmpty()) {
            formulario.setImagenPath(formulario.getImagenPath());

            // Obtener la URL de la imagen de S3, si es necesario
            String imagenUrl = s3Service.getObjectUrl(formulario.getImagenPath());
            formulario.setUrlImagenPortada(imagenUrl);
        }
        return obrasFonograficasServicio.actualizarObraFonografica(idObrasFonograficas, formulario);
    }

    // Eliminar una obra fonográfica
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idObrasFonograficas}")
    public void delete(@PathVariable Long idObrasFonograficas) {
        ObrasFonograficas obraExistente = obrasFonograficasServicio.obtenerObraFonograficaPorId(idObrasFonograficas);
        if (obraExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Obra No Encontrado");
        }
        s3Service.deleteObject(obraExistente.getImagenPath());
        obrasFonograficasServicio.eliminarObraFonografica(idObrasFonograficas);
    }
}
