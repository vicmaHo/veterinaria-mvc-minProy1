package model;

import java.util.ArrayList;

public abstract class Mascota implements Comparable<Mascota> {
    // valores por defecto
    public static final double PRECIO_MASCOTA_POR_DEFECTO = 0d;
    public static final String PAIS_POR_DEFECTO = "NN";

    // Atribuos
    private int id;
    private ArrayList<String> vacunas;
    private double precio;
    private String paisOrigen;

    //Contador para el id
    private static int contadorID = 1;

    // Constructores 
    // 2 argumentos
    public Mascota(double precio, String paisOrigen) {
        vacunas = new ArrayList<String>();
        this.precio = precio;
        this.paisOrigen = paisOrigen;
        this.id = contadorID++; // agrego el valor del id y lo aumento en 1
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getContadorID(){
        return contadorID;
    }

    public void setContadorID(int numId){
        this.contadorID = numId;
    }

    public void setVacunas(ArrayList<String> vacunas){
        this.vacunas = vacunas;
    }

    public void mostrarVacunas(){
        System.out.println("-- Vacunas -- ");
        for (String vacuna : vacunas) {
            System.out.println(vacuna);
        }
    }


    public int getCantidadVacunas(){
        int cantVacunas =  vacunas.size();
        return cantVacunas;
    }

    public ArrayList<String> getVacunas(){
        return vacunas;
    }
    
    /* Implemento la interface Comparable para realziar una comparacoin en los precios de las mascotas
    lo que posteriormente me servira para hacer el top de mascotas por precios.
     */
    @Override
    public int compareTo(Mascota otraMascota) {
        return Double.compare(this.precio, otraMascota.getPrecio());
    }

    // Metodos
    public abstract void mostrarInformacion();

}
