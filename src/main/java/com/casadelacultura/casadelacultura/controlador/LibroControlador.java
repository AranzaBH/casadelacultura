package com.casadelacultura.casadelacultura.controlador;

import javax.validation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.casadelacultura.casadelacultura.entity.Libro;
import com.casadelacultura.casadelacultura.servicio.LibroServicio;
import com.casadelacultura.casadelacultura.servicio.S3Service;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/libro")
@CrossOrigin("*")
public class LibroControlador {
    private final LibroServicio libroServicio;
    private final S3Service s3Service;

    // Obtener todos los libros
    @GetMapping
    public Iterable<Libro> list() {
        Iterable<Libro> libros = libroServicio.listarLibros();
        for(Libro libro : libros){
            if (libro.getImagenPath() != null && !libro.getImagenPath().isEmpty()) {
                String imagenURL = s3Service.getObjectUrl(libro.getImagenPath());
                libro.setUrlImagenPortada(imagenURL);
            }
        }
        return libros;
        
    }

    // Obtener un libro por ID
    @GetMapping("{idLibro}")
    public Libro getLibroId(@PathVariable Long idLibro) {
        Libro libro = libroServicio.obtenerLibroPorId(idLibro);
        if (libro != null && libro.getImagenPath() != null && !libro.getImagenPath().isEmpty()){
            String imagenUrl = s3Service.getObjectUrl(libro.getImagenPath());
            libro.setUrlImagenPortada(imagenUrl);
        }
        return libro;
    }

    // Crear un nuevo libro
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Libro create(@Valid @RequestBody Libro libro) {
        libro.setUrlImagenPortada(s3Service.getObjectUrl(libro.getImagenPath()));
        return libroServicio.crearLibro(libro);
    }

    // Actualizar un libro existente
    @PutMapping("{idLibro}")
    public Libro updateLibro(@PathVariable Long idLibro, @RequestBody @Valid Libro formulario) {
        if (formulario.getImagenPath() != null && !formulario.getImagenPath().isEmpty()) {
            formulario.setImagenPath(formulario.getImagenPath());

            // Obtener la URL de la imagen de S3, si es necesario
            String imagenUrl = s3Service.getObjectUrl(formulario.getImagenPath());
            formulario.setUrlImagenPortada(imagenUrl);
        }
        return libroServicio.actualizarLibro(idLibro, formulario);
    }

    // Eliminar un libro
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{idLibro}")
    public void delete(@PathVariable Long idLibro) {
        Libro libroExistente = libroServicio.obtenerLibroPorId(idLibro);
        if (libroExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Obra No Encontrado");
            
        }
        s3Service.deleteObject(libroExistente.getImagenPath());
        libroServicio.eliminarLibro(idLibro);

    }
}
