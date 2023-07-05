package view;

import java.util.ArrayList;

import controller.ControladorVetirinaria;
import model.Mascota;

public interface VistaVeterinaria {
    public void iniciar(ControladorVetirinaria controladorVetirinaria);
    public Mascota getMascota();
    public void insertarMascota();
    public void actualizarMascota(ArrayList<Mascota> mascotas);
    public void eliminarMascota(ArrayList<Mascota> mascotas);
    public void buscarMascota(ArrayList<Mascota> mascotas);
    public void listarMascotas(ArrayList<Mascota> mascotas);
    public void SubMenu(ArrayList<Mascota> mascotas);
    public void guardarDatos();
    public void cargarDatos();
}
