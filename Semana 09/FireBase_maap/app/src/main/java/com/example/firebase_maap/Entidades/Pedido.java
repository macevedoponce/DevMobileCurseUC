package com.example.firebase_maap.Entidades;

public class Pedido {
    private int id;
    private String destinatario;
    private String direccion;
    private String descripcion;
    private String tipo;
    private String distrito;
    private String estado;
    private String fechaRegistro;


    public Pedido() {
    }

    public Pedido(int id, String destinatario, String direccion, String descripcion, String tipo, String distrito, String estado, String fechaRegistro) {
        this.id = id;
        this.destinatario = destinatario;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.distrito = distrito;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", destinatario='" + destinatario + '\'' +
                ", direccion='" + direccion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo='" + tipo + '\'' +
                ", distrito='" + distrito + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaRegistro='" + fechaRegistro + '\'' +
                '}';
    }
}
