package com.example.Sorteo;


import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "participantes")
public class Participante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String dni;

    private String nombre;
    
    private String numeroTelefono;
    
    @Column(nullable = false)
    private String itemComprado;

    // Constructor
    public Participante() {
    }

    public Participante(int id, String dni, String nombre, String numeroTelefono, String itemComprado) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
        this.itemComprado = itemComprado;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getItemComprado() {
        return itemComprado;
    }

    public void setItemComprado(String itemComprado) {
        this.itemComprado = itemComprado;
    }
}
