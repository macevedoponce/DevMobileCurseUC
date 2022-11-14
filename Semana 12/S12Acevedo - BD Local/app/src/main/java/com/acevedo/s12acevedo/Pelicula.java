package com.acevedo.s12acevedo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Pelicula {
    private int id;
    private String title;
    private String description;
    private String time;
    private String year;
    private String director;
    private String category;
    private String ruta_imagen;

    public Pelicula(int id, String title, String description, String time, String year, String director, String category, String ruta_imagen) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.time = time;
        this.year = year;
        this.director = director;
        this.category = category;
        this.ruta_imagen = ruta_imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRuta_imagen() {
        return ruta_imagen;
    }

    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }


}

