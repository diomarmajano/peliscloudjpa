package pelis.cloud.peliscloudjpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import pelis.cloud.peliscloudjpa.model.Peliculas;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculasRepositoryTest {
    @Autowired
    private PeliculasRepository peliculasRepository;

    @Test
    public void savePeliculaTest() {
        Peliculas pelicula= new Peliculas();
        pelicula.setTitulo("Harry potter");

        Peliculas resultado = peliculasRepository.save(pelicula);

        assertNotNull(resultado.getId());
        assertEquals("Harry potter", resultado.getTitulo());
    }

}
