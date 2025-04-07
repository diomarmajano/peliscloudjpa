package pelis.cloud.peliscloudjpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pelis.cloud.peliscloudjpa.repository.PeliculasRepository;
import pelis.cloud.peliscloudjpa.model.Peliculas;

@Service
public class PeliculasServiceImpl implements PeliculasService {
    // Implementación de los métodos de la interfaz PeliculasService
   @Autowired
   private PeliculasRepository peliculasRepository;

   //metodos para obtener resultados
    @Override
    public List<Peliculas> getAllPeliculas() {
        return peliculasRepository.findAll();
    }

    @Override
    public Optional<Peliculas> getPeliculaById(Long id) {
        return peliculasRepository.findById(id);
    }

    //metodo para crear, y acttualizar, y eliminar
    @Override
    public Peliculas createPelicula(Peliculas pelicula) {
        return peliculasRepository.save(pelicula);
    }

    @Override
    public Peliculas updatePelicula(Long id, Peliculas pelicula) {
        if (peliculasRepository.existsById(id)) {
            pelicula.setId(id);
            return peliculasRepository.save(pelicula);
        } else {
            return null;
        }
    }

    @Override
    public void deletePelicula(Long id) {
        peliculasRepository.deleteById(id);
    }
}