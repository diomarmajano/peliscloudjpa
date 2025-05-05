package pelis.cloud.peliscloudjpa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pelis.cloud.peliscloudjpa.repository.PeliculasRepository;
import pelis.cloud.peliscloudjpa.model.Peliculas;

@ExtendWith(MockitoExtension.class)
public class PeliculasServiceTest {

    @InjectMocks
     private PeliculasServiceImpl peliculasServiceImpl;

     @Mock
     private PeliculasRepository peliculasRepositoryMock;

     @Test
     public void savePeliculaTest() {
         Peliculas pelicula = new Peliculas();
         pelicula.setTitulo("Harry potter");

         when(peliculasRepositoryMock.save(any(Peliculas.class))).thenReturn(pelicula);

         Peliculas resultado = peliculasServiceImpl.createPelicula(pelicula);

         assertEquals("Harry potter", resultado.getTitulo());
     }
    
}
