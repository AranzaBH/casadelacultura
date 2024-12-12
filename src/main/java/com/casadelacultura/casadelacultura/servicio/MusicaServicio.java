package com.casadelacultura.casadelacultura.servicio;

import org.springframework.stereotype.Service;
import com.casadelacultura.casadelacultura.entity.Musica;
import com.casadelacultura.casadelacultura.entity.ObrasFonograficas;
import com.casadelacultura.casadelacultura.excepciones.GlobalExceptionNoEncontrada;
import com.casadelacultura.casadelacultura.repositorio.MusicaRepositorio;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MusicaServicio {
    private final  MusicaRepositorio musicaRepositorio;
    private final ObrasFonograficasServicio obrasFonograficasServicio;

    // Obtener todas las músicas
    public Iterable<Musica> listarMusica() {
        return musicaRepositorio.findAll();
    }

    // Obtener una música por ID
    public Musica obtenerMusicaPorId(Long idMusica) {
        return musicaRepositorio.findById(idMusica)
                .orElseThrow(() -> new GlobalExceptionNoEncontrada("No se encontro la obra con el ID: " + idMusica));
    }

    // Crear una nueva música
    public Musica crearMusica(Musica musica) {
        //Valida la existencia de al obra fonografica 
        ObrasFonograficas obrasFonograficas = obrasFonograficasServicio.obtenerObraFonograficaPorId(musica.getObrasFonograficas().getIdObrasFonograficas());
        musica.setObrasFonograficas(obrasFonograficas);
        return musicaRepositorio.save(musica);
    }

    // Actualizar una música existente
    public Musica actualizarMusica(Long idMusica, Musica formulario) {
        Musica musicaFromDB = obtenerMusicaPorId(idMusica);
        ObrasFonograficas obrasFonograficas = obrasFonograficasServicio.obtenerObraFonograficaPorId(formulario.getObrasFonograficas().getIdObrasFonograficas());
        formulario.setObrasFonograficas(obrasFonograficas);

        musicaFromDB.setNombre(formulario.getNombre());
        musicaFromDB.setDescripcion(formulario.getDescripcion());
        musicaFromDB.setObrasFonograficas(formulario.getObrasFonograficas());
        return musicaRepositorio.save(musicaFromDB);
    }

    // Eliminar una música
    public void eliminarMusica(Long idMusica) {
        Musica musicaFromDB = obtenerMusicaPorId(idMusica);
        musicaRepositorio.delete(musicaFromDB);
    }
}
