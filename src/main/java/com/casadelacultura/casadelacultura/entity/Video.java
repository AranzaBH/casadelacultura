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
    private Long id;
    private String url;
    private String titulo;
    /* 
    @ManyToOne
    @JoinColumn(name = "taller_id", nullable = false)
    private Taller taller;*/
}
