package com.casadelacultura.casadelacultura.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.casadelacultura.casadelacultura.entity.Imagenes;
import com.casadelacultura.casadelacultura.servicio.ImagenesServicio;
import com.casadelacultura.casadelacultura.servicio.S3Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/imagenes")
@CrossOrigin("*")
public class ImagenesControlador {
    private final ImagenesServicio imagenesServicio;
    private final S3Service s3Service;

    @GetMapping()
    public Iterable<Imagenes> listarTodasLasImagenes() {
        Iterable<Imagenes> imagenes = imagenesServicio.listarImagenes();
        for (Imagenes imagen : imagenes) {
            if (imagen.getImagenPath() != null && !imagen.getImagenPath().isEmpty()) {
                String imagenUrl = s3Service.getObjectUrl(imagen.getImagenPath());
                imagen.setUrlImagen(imagenUrl);
            }
        }
        return imagenes;
    }

    @GetMapping("{idImagen}")
    public Imagenes obtenerImagenPorId(@PathVariable Long idImagen) {
        Imagenes imagenes = imagenesServicio.obtenerImagenPorId(idImagen);

        if (imagenes != null && !imagenes.getImagenPath().isEmpty()) {
            String imagenUrl = s3Service.getObjectUrl(imagenes.getImagenPath());
            imagenes.setUrlImagen(imagenUrl);
        }

        return imagenes;
    }

    /*
     * @ResponseStatus(HttpStatus.CREATED) // Indica que, si se crea correctamente,
     * se devuelve el código de estado 201.
     * 
     * @PostMapping
     * public Imagenes create(@RequestBody Imagenes imagenes){
     * return imagenesServicio.crearImagen(imagenes);
     * }
     */

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "multipart/form-data")
    public Imagenes create(@RequestParam("archivo") MultipartFile archivo) {
        // 1. Subir la imagen a S3
        String key = s3Service.putObject(archivo);
        String urlImagen = s3Service.getObjectUrl(key);

        // 2. Crear la entidad Imagenes
        Imagenes nuevaImagen = new Imagenes();
        nuevaImagen.setImagenPath(key); // Usamos el key como identificador en S3
        nuevaImagen.setUrlImagen(urlImagen); // Transient no se guarda, pero lo enviamos en la respuesta

        // 3. Guardar la entidad en la base de datos
        return imagenesServicio.crearImagen(nuevaImagen);
    }

    @PutMapping("{idImagen}")
    public Imagenes actualizarImagen(@PathVariable Long idImagen,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo) {
        // 1. Obtener la imagen existente desde la base de datos
        Imagenes imagenExistente = imagenesServicio.obtenerImagenPorId(idImagen);
        if (imagenExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Imagen no encontrada");
        }

        // 2. Si se proporcionó un nuevo archivo, subirlo a S3 y actualizar los datos
        if (archivo != null && !archivo.isEmpty()) {
            // Eliminar la imagen antigua de S3
            s3Service.deleteObject(imagenExistente.getImagenPath());

            // Subir el nuevo archivo a S3
            String key = s3Service.putObject(archivo);
            String urlImagen = s3Service.getObjectUrl(key);

            // Actualizar los datos de la imagen
            imagenExistente.setImagenPath(key); // Usamos el nuevo key
            imagenExistente.setUrlImagen(urlImagen); // Actualizamos la URL
        }

        // 3. Guardar la imagen actualizada en la base de datos
        return imagenesServicio.actualizarImagen(idImagen, imagenExistente);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT) // Indica que, si se elimina correctamente, se devuelve el código de estado
                                           // 204
    @DeleteMapping("{idImagen}")
    public void eliminarImagen(@PathVariable Long idImagen) {
        // 1. Obtener la imagen desde la base de datos
        Imagenes imagenExistente = imagenesServicio.obtenerImagenPorId(idImagen);
        if (imagenExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Imagen no encontrada");
        }

        // 2. Eliminar la imagen de S3
        s3Service.deleteObject(imagenExistente.getImagenPath());

        // 3. Eliminar la imagen de la base de datos
        imagenesServicio.eliminarImagen(idImagen);
    }

    /*
     * @PutMapping("{idImagen}")
     * public Imagenes acutalizarImagenes(@PathVariable Long idIamgen, @RequestBody
     * Imagenes formulImagenes) {
     * return imagenesServicio.actualizarImagen(idIamgen, formulImagenes);
     * }
     * 
     * @ResponseStatus(HttpStatus.NO_CONTENT) // Indica que, si se elimina
     * correctamente, se devuelve el código de estado
     * // 204.
     * 
     * @DeleteMapping("{idImagen}")
     * public void delate(@PathVariable Long idImagen) {
     * imagenesServicio.eliminarImagen(idImagen);
     * }
     */

}
