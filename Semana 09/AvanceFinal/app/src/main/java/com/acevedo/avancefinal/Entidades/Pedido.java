package com.acevedo.avancefinal.Entidades;

public class Pedido {
    private int id;
    private String destinatario;
    private String descripcion;
    private String tipo;
    private double latitud;
    private double longitud;
    private String estado;
    private String fechaRegistro;


    public Pedido() {
    }

    public Pedido(int id, String destinatario, String descripcion, String tipo, double latitud, double longitud, String estado, String fechaRegistro) {
        this.id = id;
        this.destinatario = destinatario;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.latitud = latitud;
        this.longitud = longitud;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", destinatario='" + destinatario + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo='" + tipo + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", estado='" + estado + '\'' +
                ", fechaRegistro='" + fechaRegistro + '\'' +
                '}';
    }
}


