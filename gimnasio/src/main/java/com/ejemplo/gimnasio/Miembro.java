package com.ejemplo.gimnasio;

public class Miembro {
    private String nombre;
    private int edad;

    public Miembro(String nombre, int edad) {
        this.nombre = nombre;
        setEdad(edad);
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad > 0) {
            this.edad = edad;
        }
    }

    public double cuotaMensual() {
        return 50000;
    }

    // Para que la cuota salga en el JSON de /miembros
    public double getCuotaMensual() {
        return cuotaMensual();
    }

    public String descripcion() {
        return nombre + " (" + edad + " años)";
    }
}