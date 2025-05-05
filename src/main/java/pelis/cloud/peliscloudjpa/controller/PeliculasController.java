package pelis.cloud.peliscloudjpa.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pelis.cloud.peliscloudjpa.model.Peliculas;
import pelis.cloud.peliscloudjpa.service.PeliculasService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.slf4j.Logger;


@RestController
@RequestMapping("/peliculas")
@CrossOrigin(origins = "*")
public class PeliculasController {
    
    private static final Logger log = LoggerFactory.getLogger(PeliculasController.class);

    @Autowired
    private PeliculasService peliculasService;

    @GetMapping
    public CollectionModel<EntityModel<Peliculas>> getAllPeliculas() {
        List<Peliculas> peliculas = peliculasService.getAllPeliculas();
        log.info("GET /peliculas");
        log.info("Retornando todas las películas");
    
        List<EntityModel<Peliculas>> peliculasResources = peliculas.stream()
            .map(peliculaItems -> EntityModel.of(peliculaItems,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(peliculaItems.getId())).withSelfRel()
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas());
        CollectionModel<EntityModel<Peliculas>> resources = CollectionModel.of(peliculasResources, linkTo.withRel("peliculaItems"));

        return resources;
    }
    
    @GetMapping("/{id}")
    public EntityModel<Peliculas>getPeliculaById(@PathVariable Long id) {
        Optional<Peliculas> pelicula = peliculasService.getPeliculaById(id);
        if (pelicula.isPresent()) {
            return EntityModel.of(pelicula.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas()).withRel("peliculas"));
        } else {
            throw new PeliculasNotFoundException("Pelicula  no encontrada: " + id);
        }
    }

    @PostMapping
    public Peliculas createPelicula(@RequestBody Peliculas pelicula) {
        return peliculasService.createPelicula(pelicula);
    }
    
    @PutMapping("/{id}")
    public Peliculas updatePelicula(@PathVariable Long id, @RequestBody Peliculas pelicula) {
        return peliculasService.updatePelicula(id, pelicula);
    }

    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable Long id) {
        peliculasService.deletePelicula(id);
    }
}
