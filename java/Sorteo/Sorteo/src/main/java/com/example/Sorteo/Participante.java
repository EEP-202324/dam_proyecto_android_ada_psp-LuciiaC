package com.example.Sorteo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "participantes_dos")
public class Participante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="dni")
    private String dni;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="numero_Telefono")
    private String numeroTelefono;

    @Column(name="item_Comprado", nullable = false)
    private String itemComprado;

    // Constructor
    public Participante() {}

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
