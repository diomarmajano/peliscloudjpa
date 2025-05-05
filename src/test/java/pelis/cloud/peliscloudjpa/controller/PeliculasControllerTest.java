package pelis.cloud.peliscloudjpa.controller;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import pelis.cloud.peliscloudjpa.service.PeliculasService;
import pelis.cloud.peliscloudjpa.model.Peliculas;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.stream.Collectors;


@WebMvcTest(PeliculasController.class)
public class PeliculasControllerTest {

     @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeliculasService peliculaServiceMock;

    @Test
    public void testGetPeliculas() throws Exception {
        Peliculas pelicula = new Peliculas();
        pelicula.setId(1L); // Ejemplo de ID
        pelicula.setTitulo("Harry Potter y la piedra filosofal");
        pelicula.setAño(2001);
        pelicula.setDirector("Chris Columbus");
        pelicula.setGenero("Fantasía");
        pelicula.setSinopsis("Un joven descubre que es un mago y asiste a una escuela de magia.");

        List<Peliculas> peliculas = List.of(pelicula);

        List<EntityModel<Peliculas>> entityModels = peliculas.stream()
                .map(peliculaItem -> EntityModel.of(peliculaItem))
                .collect(Collectors.toList());

        when(peliculaServiceMock.getAllPeliculas()).thenReturn(peliculas);

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculasList[0].titulo", Matchers.is("Harry Potter y la piedra filosofal")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculasList[0].año", Matchers.is(2001)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculasList[0].director", Matchers.is("Chris Columbus")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculasList[0].genero", Matchers.is("Fantasía")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.peliculasList[0].sinopsis", Matchers.is("Un joven descubre que es un mago y asiste a una escuela de magia.")));
    }
    
}
