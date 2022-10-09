package acevedo.EvalPar.org.Entidades;

import android.graphics.Bitmap;

public class Comentario {
    private Integer id;
    private Integer calificacion;
    private String comentario;
    private Integer id_pelicula;

    public Comentario() {
    }

    public Comentario(Integer id, Integer calificacion, String comentario, Integer id_pelicula) {
        this.id = id;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.id_pelicula = id_pelicula;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(Integer id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", calificacion=" + calificacion +
                ", comentario='" + comentario + '\'' +
                ", id_pelicula=" + id_pelicula +
                '}';
    }


}

