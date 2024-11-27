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
    @Size(max = 100, message = "El Nombre del Autor no puede tener más de 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombreAutor;

    @NonNull
    @NotBlank(message = "El Apellido Paterno del Autor es obligatorio")
    @Size(max = 100, message = "El Apellido paterno del Autor no puede tener más de 100 caracteres")
    @Column(nullable = false, length = 100)
    private String apellidoPaterno;

    @Size(max = 100, message = "El apellido materno del Autor no puede tener más de 100 caracteres")
    @Column(length = 100)
    private String apellidoMaterno;

    @NonNull
    private LocalDate fechaNacimiento;

    @NonNull
    private LocalDate fechaFallecimiento;


}
