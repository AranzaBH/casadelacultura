package com.casadelacultura.casadelacultura.repositorio;

import org.springframework.data.repository.CrudRepository;
import com.casadelacultura.casadelacultura.entity.Video;

public interface VideoRepositorio extends CrudRepository<Video, Long> {
}
