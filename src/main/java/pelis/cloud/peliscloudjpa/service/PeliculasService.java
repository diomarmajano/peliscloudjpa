package pelis.cloud.peliscloudjpa.service;

import java.util.List;
import java.util.Optional;

import pelis.cloud.peliscloudjpa.model.Peliculas;

public interface PeliculasService {
    List<Peliculas> getAllPeliculas();
    Optional<Peliculas> getPeliculaById(Long id);
    Peliculas createPelicula(Peliculas pelicula);
    Peliculas updatePelicula(Long id, Peliculas pelicula);
    void deletePelicula(Long id);
}
