package com.casadelacultura.casadelacultura.entity;


import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class ObrasPorAutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObrasPorAutor;

    @OneToOne
    @JoinColumn(name = "idAutores", nullable = false)
    private Autor autor;

    @OneToOne
    @JoinColumn(name = "idObra", nullable = false)
    private Obra obra;
    

}
