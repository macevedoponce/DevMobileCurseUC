package acevedo.EvalPar.org.Entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Pelicula {
    private String id;
    private String title;
    private String description;
    private String time;
    private String year;
    private String director;
    private String category;
    private Bitmap imagen;
    private String ruta;

    public Pelicula() {

    }

    public Pelicula(String id, String title, String description, String time, String year, String director, String category, Bitmap imagen, String ruta) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.time = time;
        this.year = year;
        this.director = director;
        this.category = category;
        this.imagen = imagen;
        this.ruta = ruta;
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

    public Bitmap getImagen() {
        return imagen;
    }

    public void setDataImagen(String imagen){
        //adaptar a Bitmap
        try{
            byte[] bytecode = Base64.decode(imagen, Base64.DEFAULT);
            this.imagen = BitmapFactory.decodeByteArray(bytecode,0,bytecode.length);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setImagen(Bitmap imagen) {

    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", time='" + time + '\'' +
                ", year='" + year + '\'' +
                ", director='" + director + '\'' +
                ", category='" + category + '\'' +
                ", imagen=" + imagen +
                ", ruta='" + ruta + '\'' +
                '}';
    }
}
