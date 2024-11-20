package com.casadelacultura.casadelacultura.entity;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutor;

    @NonNull
    @Column(nullable = false)
    private String nombreAutor;

    @NonNull
    @Column(nullable = false)
    private String apellidoPaterno;

    
    private String apellidoMaterno;

    
    private LocalDate fechaNacimiento;

    
    private LocalDate fechaFallecimiento;


}
