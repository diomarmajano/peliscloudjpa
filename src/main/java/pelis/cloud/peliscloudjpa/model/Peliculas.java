package pelis.cloud.peliscloudjpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//Creacion de la tabla peliculas en la base de datos
@Entity
@Table(name = "peliculas")
public class Peliculas {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="año")
    private int año;

    @Column(name="titulo")
    private String titulo;

    @Column(name="director")
    private String director;

    @Column(name="genero")
    private String genero;

    @Column(name="sinopsis")
    private String sinopsis;

    //Creacion de getters y setters para cada uno de los atributos de la clase
    public Long getId() {
        return id;
    }   
    public void setId(Long id) {
        this.id = id;
    }
    public int getAño() {
        return año;
    }
    public void setAño(int año) {
        this.año = año;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getSinopsis() {
        return sinopsis;
    }
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    } 
}
