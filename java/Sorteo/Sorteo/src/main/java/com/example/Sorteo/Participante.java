package com.example.Sorteo;


//Entity en algún sitio
public class Participante {
    private int id;
    private String dni;
    private String nombre;
    private String numeroTelefono;
    private String itemComprado;

    // Constructor
    public Participante(int id, String dni, String nombre, String numeroTelefono, String itemComprado) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
        this.itemComprado = itemComprado;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public String getItemComprado() {
        return itemComprado;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public void setItemComprado(String itemComprado) {
        this.itemComprado = itemComprado;
    }
}
