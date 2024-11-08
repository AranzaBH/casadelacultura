package com.casadelacultura.casadelacultura.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Musica;
import com.casadelacultura.casadelacultura.repositorio.MusicaRepositorio;

import java.util.Optional;

@Service
public class MusicaServicio {

    @Autowired
    private MusicaRepositorio musicaRepositorio;

    // Obtener todas las músicas
    public Iterable<Musica> listarMusica() {
        return musicaRepositorio.findAll();
    }

    // Obtener una música por ID
    public Optional<Musica> obtenerMusicaPorId(Long idMusica) {
        return musicaRepositorio.findById(idMusica);
    }

    // Crear una nueva música
    public Musica crearMusica(Musica musica) {
        return musicaRepositorio.save(musica);
    }

    // Actualizar una música existente
    public Optional<Musica> actualizarMusica(Long idMusica, Musica formulario) {
        return musicaRepositorio.findById(idMusica).map(musicaExistente -> {
            musicaExistente.setDescripcion(formulario.getDescripcion());
            musicaExistente.setObrasFonograficas(formulario.getObrasFonograficas());
            return musicaRepositorio.save(musicaExistente);
        });
    }

    // Eliminar una música
    public boolean eliminarMusica(Long idMusica) {
        Optional<Musica> musica = musicaRepositorio.findById(idMusica);
        if (musica.isPresent()) {
            musicaRepositorio.delete(musica.get());
            return true;
        }
        return false;
    }
}
