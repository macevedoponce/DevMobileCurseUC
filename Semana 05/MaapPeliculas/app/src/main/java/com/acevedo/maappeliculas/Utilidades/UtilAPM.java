package com.acevedo.maappeliculas.Utilidades;

public class UtilAPM {
    //variable statica constante para compartir entre las diferetnes actividades
    public static final String BASE_DATOS = "bd_pelicula";
    public static final String TABLA_PELICULA = "pelicula";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_TITLE = "title";
    public static final String CAMPO_DESCRIPTION = "description";
    public static final String CAMPO_DURATION = "duration";
    public static final String CAMPO_YEAR = "year";
    public static final String CAMPO_DIRECTOR = "director";
    public static final String CAMPO_CATEGORY = "category";
    public static final String CAMPO_IMAGE = "image";
    public static final String CREAR_TABLA_PELICULA = "CREATE TABLE " + TABLA_PELICULA +
            " ( " + CAMPO_ID + " INTEGER, " + CAMPO_TITLE + " TEXT, " + CAMPO_DESCRIPTION + " TEXT, "
            + CAMPO_CATEGORY + " TEXT, " + CAMPO_DIRECTOR + " TEXT, " + CAMPO_YEAR + " TEXT, " +
            CAMPO_DURATION + " TEXT, " + CAMPO_IMAGE + " TEXT )";
}
