package com.casadelacultura.casadelacultura.entity;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;
import javax.validation.constraints.*;

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
    @NotBlank(message = "El nombre del Autor es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombreAutor;

    @NonNull
    @NotBlank(message = "El Apellido Paterno del Autor es obligatorio")
    @Column(nullable = false, length = 100)
    private String apellidoPaterno;

    
    private String apellidoMaterno;

    
    private LocalDate fechaNacimiento;

    
    private LocalDate fechaFallecimiento;


}
