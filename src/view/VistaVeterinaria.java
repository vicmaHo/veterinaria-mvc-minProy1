package view;

import java.util.ArrayList;

import controller.ControladorVetirinaria;
import model.Mascota;

public interface VistaVeterinaria {
    public void iniciar(ControladorVetirinaria controladorVetirinaria);
    public void insertarMascota();
    public Mascota getMascota();
    public void actualizarMascota(ArrayList<Mascota> mascotas);
    public void eliminarMascota();
    public void buscarMascota();
    public void listarMascotas(ArrayList<Mascota> mascotas);
}
