package com.example.mapsqlite1.Entidades;

public class Pelicula {
    //encapsular
    private String id;
    private String name;
    private String description;
    private String year;
    private String time;
    private String gender;


    public Pelicula(String id, String name, String description, String year, String time, String gender) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.time = time;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return  "id='" + id +
                ", name='" + name +
                ", description='" + description +
                ", year='" + year +
                ", time='" + time +
                ", gender='" + gender
                ;
    }
}
