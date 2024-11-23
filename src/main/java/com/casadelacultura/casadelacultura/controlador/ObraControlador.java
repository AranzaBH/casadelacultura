package com.casadelacultura.casadelacultura.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.casadelacultura.casadelacultura.entity.Obra;
import com.casadelacultura.casadelacultura.servicio.ObraServicio;
import com.casadelacultura.casadelacultura.servicio.S3Service;

import lombok.AllArgsConstructor;

// Controlador para manejar las operaciones CRUD de Obra
@AllArgsConstructor
@RestController
@RequestMapping("/api/obra")
@CrossOrigin("*")
public class ObraControlador {
    private final ObraServicio obraServicio;
    private final S3Service s3Service;

    // Obtener todas las obras
    @GetMapping
    public Iterable<Obra> list() {
        Iterable<Obra> obras = obraServicio.listarObras();
        for (Obra obra : obras){
            if (obra.getImagenPath() != null && !obra.getImagenPath().isEmpty()){
                String imagenUrl = s3Service.getObjectUrl(obra.getImagenPath());
                obra.setIdUrlImagenPortada(imagenUrl);

            }

        }
       return obras;
    }

    // Obtener una obra por ID
    @GetMapping("{idObra}")
    public Obra get(@PathVariable Long idObra) {
        Obra obra = obraServicio.obtenerObraPorId(idObra);
        if (obra != null && obra.getImagenPath() != null && !obra.getImagenPath().isEmpty()){
            String imagenUrl = s3Service.getObjectUrl(obra.getImagenPath());
            obra.setIdUrlImagenPortada(imagenUrl);

        }
        return obra;
    }

    // Crear una nueva obra
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Obra create(@RequestBody Obra obra) {
        obra.setIdUrlImagenPortada(s3Service.getObjectUrl(obra.getImagenPath()));
        return obraServicio.crearObra(obra);
    }

    // Actualizar una obra existente
    @PutMapping("{idObra}")
    public Obra update(@PathVariable Long idObra, @RequestBody Obra formulario) {
        if (formulario.getImagenPath() != null && !formulario.getImagenPath().isEmpty()) {
            formulario.setImagenPath(formulario.getImagenPath());

            // Obtener la URL de la imagen de S3, si es necesario
            String imagenUrl = s3Service.getObjectUrl(formulario.getImagenPath());
            formulario.setIdUrlImagenPortada(imagenUrl);
        }
        return obraServicio.actualizarObra(idObra, formulario);
        
    }

    // Eliminar una obra
    @ResponseStatus(HttpStatus.NO_CONTENT) 
    @DeleteMapping("{idObra}")
    public void delete(@PathVariable Long idObra) {
        Obra obraExistente = obraServicio.obtenerObraPorId(idObra);
        if (obraExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Obra No Encontrado");
            
        }
        s3Service.deleteObject(obraExistente.getImagenPath());
        obraServicio.eliminarObra(idObra);
    }
}
