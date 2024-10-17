package com.casadelacultura.casadelacultura.servicio;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.AudioLibro;
import com.casadelacultura.casadelacultura.repositorio.AudioLibroRepositorio;

@Service
public class AudioLibroServicio {

    @Autowired
    private AudioLibroRepositorio audioLibroRepositorio;

    // Obtener todos los audiolibros
    public Iterable<AudioLibro> findAll() {
        return audioLibroRepositorio.findAll();
    }

    // Buscar un audiolibro por su ID
    public Optional<AudioLibro> findById(Integer idAudioLibro) {
        return audioLibroRepositorio.findById(idAudioLibro);
    }

    // Crear un nuevo audiolibro
    public AudioLibro create(AudioLibro audioLibro) {
        return audioLibroRepositorio.save(audioLibro);
    }

    // Actualizar un audiolibro existente
    public AudioLibro update(AudioLibro audioLibro) {
        return audioLibroRepositorio.save(audioLibro);
    }

    // Eliminar un audiolibro por su ID
    public void delete(Integer idAudioLibro) {
        audioLibroRepositorio.deleteById(idAudioLibro);
    }
}
