package com.casadelacultura.casadelacultura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.casadelacultura.casadelacultura.entity.Taller;
import com.casadelacultura.casadelacultura.repositorio.TallerRepositorio;
import com.casadelacultura.casadelacultura.servicio.S3Service;
import com.casadelacultura.casadelacultura.servicio.TallerServicio;

import lombok.AllArgsConstructor;

import java.util.Optional;

// Controlador para manejar las operaciones CRUD de Taller
@AllArgsConstructor
@RestController
@RequestMapping("/api/taller")
public class TallerControlador {

    @Autowired
    private TallerServicio tallerServicio;

    @Autowired
    private S3Service s3Service;

    // Obtener todos los talleres
    @GetMapping
    public Iterable<Taller> list() {
        Iterable<Taller> talleres = tallerServicio.obtenerTodosTalleres();
        // Asignar la URL de la imagen a cada taller antes de devolver la lista
        for (Taller taller : talleres) {
            if (taller.getImagenPath() != null && !taller.getImagenPath().isEmpty()) {
                String imagenUrl = s3Service.getObjectUrl(taller.getImagenPath());
                taller.setUrlImagenPortadaTaller(imagenUrl);
            }
        }
        return talleres;
    }

    // Obtener un taller por su ID
    @GetMapping("{idTaller}")
    public Taller get(@PathVariable Long idTaller) {
        Taller taller = tallerServicio.obtenerTallerPorId(idTaller);
        if (taller != null && taller.getImagenPath() != null && !taller.getImagenPath().isEmpty()) {
            // Asignar la URL de la imagen al taller
            String imagenUrl = s3Service.getObjectUrl(taller.getImagenPath());
            taller.setUrlImagenPortadaTaller(imagenUrl);
        }
        return taller;
    }

    // Crear un nuevo taller
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Taller create(@RequestBody Taller taller) {

        taller.setUrlImagenPortadaTaller(
                s3Service.getObjectUrl(taller.getImagenPath()));
        return tallerServicio.crearTaller(taller);
    }

    // Actualizar un taller existente

    @PutMapping("{idTaller}")
    public Taller update(@PathVariable Long idTaller, @RequestBody Taller formulario) {
        if (formulario.getImagenPath() != null && !formulario.getImagenPath().isEmpty()) {
            formulario.setImagenPath(formulario.getImagenPath());

            // Obtener la URL de la imagen de S3, si es necesario
            String imagenUrl = s3Service.getObjectUrl(formulario.getImagenPath());
            formulario.setUrlImagenPortadaTaller(imagenUrl);
        }
        return tallerServicio.actualizarTaller(idTaller, formulario);

    }

    // Eliminar un taller
    @ResponseStatus(HttpStatus.NO_CONTENT) // Indica que, si se elimina correctamente, se devuelve el código de estado
                                           // 204.
    @DeleteMapping("{idTaller}")
    public void delate(@PathVariable Long idTaller) {
        tallerServicio.eliminarTaller(idTaller);
    }

}
