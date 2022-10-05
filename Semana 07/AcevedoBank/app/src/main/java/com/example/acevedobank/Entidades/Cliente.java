package com.example.acevedobank.Entidades;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Cliente {
    private Integer idcliente;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private Bitmap imagen;
    private String ruta;

    public Cliente() {
    }

    public Cliente(Integer idcliente, String nombres, String apellidos, String email, String telefono, Bitmap imagen, String ruta) {
        this.idcliente = idcliente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.imagen = imagen;
        this.ruta = ruta;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        return "Cliente{" +
                "idcliente=" + idcliente +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", imagen=" + imagen +
                ", ruta='" + ruta + '\'' +
                '}';
    }
}
