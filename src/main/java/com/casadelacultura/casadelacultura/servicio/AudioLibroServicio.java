package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.AudioLibro;
import com.casadelacultura.casadelacultura.entity.CategoriaLibro;
import com.casadelacultura.casadelacultura.entity.ObrasFonograficas;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.AudioLibroRepositorio;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AudioLibroServicio {
    private final AudioLibroRepositorio audioLibroRepositorio;
    private final CategoriaLibroServicio categoriaLibroServicio;
    private final ObrasFonograficasServicio obrasFonograficasServicio;

    // Obtener todos los audiolibros
    public Iterable<AudioLibro> listarAudioLibros() {
        return audioLibroRepositorio.findAll();
    }

    // Buscar un audiolibro por su ID
    public AudioLibro audioLibrioPorId(Long idAudioLibro) {
        return audioLibroRepositorio.findById(idAudioLibro)
                .orElseThrow(() -> new GlobalExceptionNoEncontrada("No se encontro el audio libro con el ID: " + idAudioLibro));
    }

    // Crear un nuevo audiolibro
    public AudioLibro crearAudioLibro(AudioLibro audioLibro) {
        CategoriaLibro categoriaLibro = categoriaLibroServicio.obtenerCategoriaPorId(audioLibro.getCategoriaLibro().getIdCategoriaLibro());
        audioLibro.setCategoriaLibro(categoriaLibro);

        ObrasFonograficas obrasFonograficas = obrasFonograficasServicio.obtenerObraFonograficaPorId(audioLibro.getObrasFonograficas().getIdObrasFonograficas());
        audioLibro.setObrasFonograficas(obrasFonograficas);

        return audioLibroRepositorio.save(audioLibro);
    }

    // Actualizar un audiolibro existente
    public AudioLibro actualizarAudioLibro(Long idAudioLibro, AudioLibro formualrio ) {
        AudioLibro audioLibroFrom = audioLibrioPorId(idAudioLibro);
        audioLibroFrom.setNombreEditorial(formualrio.getNombreEditorial());
        audioLibroFrom.setAsin(formualrio.getAsin());
        audioLibroFrom.setCategoriaLibro(formualrio.getCategoriaLibro());
        audioLibroFrom.setObrasFonograficas(formualrio.getObrasFonograficas());
        return audioLibroRepositorio.save(audioLibroFrom);
    }

    // Eliminar un audiolibro por su ID
    public void eliminarAudioLibro(Long idAudioLibro) {
        AudioLibro audioLibroFrom = audioLibrioPorId(idAudioLibro);
        audioLibroRepositorio.delete(audioLibroFrom);
    }
}
