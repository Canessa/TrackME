package com.example.practicaexamen.model;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.Blob;

public class Pelicula implements Serializable {
    private int id;
    private String Nombre;
    private String Director;
    private String Genero;
    private Double Duracion;

    public Pelicula() {
    }

    public Pelicula(int id, String nombre, String director, String genero, Double duracion) {
        this.id = id;
        Nombre = nombre;
        Director = director;
        Genero = genero;
        Duracion = duracion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public Double getDuracion() {
        return Duracion;
    }

    public void setDuracion(Double duracion) {
        this.Duracion = duracion;
    }
}
