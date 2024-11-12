package com.casadelacultura.casadelacultura.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVideo;

    @NonNull
    private String titulo;

    @NonNull
    private String urlVideo;
}
