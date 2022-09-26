package com.acevedo.maappeliculas.Entidades;

public class Pelicula {
    //encapsular

    private String id;
    private String title;
    private String description;
    private String duration;
    private String year;
    private String director;
    private String category;
    //private String image;

   // public Pelicula(String id, String title, String description, String duration, String year, String director, String category, String image) {
    public Pelicula(String id, String title, String description, String duration, String year, String director, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.year = year;
        this.director = director;
        this.category = category;
       // this.image = image;
    }
    public Pelicula() {
        this.id = "n";
        this.title = "nnnnn";
        this.description = "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn";
        this.duration = "nn:nn:nn";
        this.year = "nn/nn/nnnn";
        this.director = "nnnnnnnnnnn";
        this.category = "nnnnnnnnnnnnnnnn";
       // this.image = "nnn.jpg";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

//    public String getImage() {
//        return image;
//    }

//    public void setImage(String image) {
//        this.image = image;
//    }

    @Override
    public String toString() {
        return  "id='" + id  +
                ", title='" + title  +
                ", description='" + description  +
                ", duration='" + duration  +
                ", year='" + year +
                ", director='" + director  +
                ", category='" + category ;
               // + ", image='" + image ;
    }
}
