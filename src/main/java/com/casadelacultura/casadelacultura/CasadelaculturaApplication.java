package com.casadelacultura.casadelacultura;

//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;

//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import com.casadelacultura.casadelacultura.entity.TipoTaller;
//import com.casadelacultura.casadelacultura.repositorio.TipoTallerRepositorio;

@SpringBootApplication
public class CasadelaculturaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasadelaculturaApplication.class, args);

    }


    /* 
    @Bean
    CommandLineRunner runner(TipoTallerRepositorio tipoTallerRepositorio) {
        return args -> {

            List<TipoTaller> tipoTaller = Arrays.asList(
                    new TipoTaller("Música", "Curso de música", LocalDateTime.now()),
                    new TipoTaller("Danza", "Practicando danza", LocalDateTime.now()),
                    new TipoTaller("Canto", "Curso de canto", LocalDateTime.now()),
                    new TipoTaller("Canto2 ", "Curso de canto", LocalDateTime.now()));
            tipoTallerRepositorio.saveAll(tipoTaller);

        };

    }*/
}