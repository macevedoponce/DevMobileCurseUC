package Utilidades;

public class UtilPelicula {
    // var statica - constante - compartir entre las actividades
    public static final String TABLA_USUARIO = "pelicula"; // constante, forma de compartir clase de manera global en toda la aplicación
    public static final String CAMPO_ID ="id";
    public static final String CAMPO_NOMBRE ="name";
    public static final String CAMPO_DESCRIPCION ="description";
    public static final String CAMPO_YEAR ="year";
    public static final String CAMPO_DURACION ="duration";
    public static final String CAMPO_GENERO ="gender";
    public static final String CREAR_TABLA_USUARIO ="CREATE TABLE "
            + TABLA_USUARIO + " ( " + CAMPO_ID + " INTEGER, " + CAMPO_NOMBRE + " TEXT, " + CAMPO_DESCRIPCION + " TEXT )"
            + CAMPO_YEAR + " TEXT )" + CAMPO_DURACION + " TEXT )" + CAMPO_GENERO + " TEXT )";

}