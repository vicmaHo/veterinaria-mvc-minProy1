package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Modelo {
    // Atributo
    ArrayList<Mascota> listaDeMascotas = new ArrayList<Mascota>();

   public Modelo() {
        Perro kaiser = new Perro("Kaiser", RazaPerro.Pastor, 1_000_000d, "Alemania");
        ArrayList<String> vacunasKaiser = new ArrayList<String>();
        vacunasKaiser.addAll(Arrays.asList("malota", "buenota", "leptospira"));
        kaiser.setVacunas(vacunasKaiser);
        listaDeMascotas.add(kaiser);

        Gato pancho = new Gato("Pancho", 543_000d, "Colombia");
        ArrayList<String> vacunasPancho = new ArrayList<String>();
        vacunasPancho.addAll(Arrays.asList("fea", "malota", "burgdorferi", "cariñosa"));
        pancho.setVacunas(vacunasPancho);
        listaDeMascotas.add(pancho);

        Perro baki = new Perro("Baki", RazaPerro.Labrador,985_900d, "Francia");
        ArrayList<String> vacunasbaki = new ArrayList<String>();
        vacunasbaki.addAll(Arrays.asList("buenota", "bronchiseptica", "burgdorferi"));
        baki.setVacunas(vacunasbaki);
        listaDeMascotas.add(baki);


        Perro alfred = new Perro("Alfred", RazaPerro.Tacita, 655_000d, "USA");
        ArrayList<String> vacunasAlfred = new ArrayList<String>();
        vacunasAlfred.addAll(Arrays.asList("buenota", "bronchiseptica", "burgdorferi", "jols", "tetanus"));
        alfred.setVacunas(vacunasAlfred);
        listaDeMascotas.add(alfred);


        Gato mono = new Gato("Mono",1_000_000_000d, "Sudan");
        ArrayList<String> vacunasMono = new ArrayList<String>();
        vacunasMono.addAll(Arrays.asList());
        mono.setVacunas(vacunasMono);
        listaDeMascotas.add(mono);
   }

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
