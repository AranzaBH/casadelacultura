package com.casadelacultura.casadelacultura.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.casadelacultura.casadelacultura.entity.Taller;
import com.casadelacultura.casadelacultura.servicio.S3Service;
import com.casadelacultura.casadelacultura.servicio.TallerServicio;

import lombok.AllArgsConstructor;

// Controlador para manejar las operaciones CRUD de Taller
@AllArgsConstructor
@RestController
@RequestMapping("/api/taller")
@CrossOrigin("*")
public class TallerControlador {
    private final TallerServicio tallerServicio;
    private final S3Service s3Service;

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
    public Taller create(@Valid @RequestBody Taller taller) {
        taller.setUrlImagenPortadaTaller(s3Service.getObjectUrl(taller.getImagenPath()));
        return tallerServicio.crearTaller(taller);
    }

    // Actualizar un taller existente

    @PutMapping("{idTaller}")
    public Taller update(@PathVariable Long idTaller, @RequestBody @Valid Taller formulario) {
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
        // Obtenemos la imagen desde la base de datos
        Taller tallerExistente = tallerServicio.obtenerTallerPorId(idTaller);
        if (tallerExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Taller No Encontrado");
        }
        // Eliminamos la imagen de s3
        s3Service.deleteObject(tallerExistente.getImagenPath());
        // eliminamos el taller
        tallerServicio.eliminarTaller(idTaller);
    }

    // Mapea las solicitudes HTTP GET a la URL '/buscar' para buscar talleres por título.
    //http://localhost:8080/api/taller/buscar?titulo=Musica
    @GetMapping("/buscar")
    public ResponseEntity<List<Taller>> buscarTallerPorTitulo(@RequestParam String titulo) {
        List<Taller> talleres = tallerServicio.buscarTallerPorTitulo(titulo);
        return ResponseEntity.ok(talleres);
    }

    



}