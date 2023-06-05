package model;

import java.util.ArrayList;

public class Modelo {
    // Atributo
    ArrayList<Mascota> listaDeMascotas = new ArrayList<Mascota>();

    // //constructor
    // public Modelo(){
    // }


    //Metodos
    public void insertarMascotaAlista(Mascota mascota){
        listaDeMascotas.add(mascota);
    }
    
    public ArrayList<Mascota> getListaMascotasDisponibles(){
        return listaDeMascotas;
    }
    
    public void eliminarMascota(String nombre){

    }

}
