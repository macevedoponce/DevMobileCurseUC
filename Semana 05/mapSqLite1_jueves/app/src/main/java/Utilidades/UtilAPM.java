package Utilidades;

public class UtilAPM {
    // var statica - constante - compartir entre las actividades
    public static final String BASE_DATOS = "bd_usuario";
    public static final String TABLA_USUARIO = "usuario"; // constante, forma de compartir clase de manera global en toda la aplicación
    public static final String CAMPO_ID ="id";
    public static final String CAMPO_NOMBRE ="nombre";
    public static final String CAMPO_TELEFONO ="telefono";
    public static final String CREAR_TABLA_USUARIO ="CREATE TABLE "
            + TABLA_USUARIO + " ( " + CAMPO_ID + " INTEGER, " + CAMPO_NOMBRE + " TEXT, " + CAMPO_TELEFONO + " TEXT )";

}
