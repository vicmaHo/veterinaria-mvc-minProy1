package model;

public class Pulga {
    // Atributos
    private String nombre;
    private int cantidadSangre;


    // constructor
    public Pulga(String nombre, int cantidadSangre) {
        this.nombre = nombre;
        this.cantidadSangre = cantidadSangre;
    }

    // Setters Getters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadSangre() {
        return cantidadSangre;
    }

    public void setCantidadSangre(int cantidadSangre) {
        this.cantidadSangre = cantidadSangre;
    }
}
