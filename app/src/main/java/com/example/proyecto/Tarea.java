package com.example.proyecto;

public class Tarea {

    private int cod;
    private String nombre;
    private String descripcion;
    private String fecha;
    private String prioridad;
    private Float precio;
    private int hecha;


    public Tarea(int cod, String nombre, String descripcion, String fecha, String prioridad, Float precio, int hecha) {
        this.cod = cod;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.prioridad = prioridad;
        this.precio = precio;
        this.hecha = hecha;
    }

    public int getHecha() {
        return hecha;
    }

    public int getCod() {
        return cod;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public void setHecha(int hecha) {
        this.hecha = hecha;
    }
}
