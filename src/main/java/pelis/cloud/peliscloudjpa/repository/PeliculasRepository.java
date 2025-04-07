package pelis.cloud.peliscloudjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pelis.cloud.peliscloudjpa.model.Peliculas;

public interface PeliculasRepository extends JpaRepository<Peliculas, Long> {

}