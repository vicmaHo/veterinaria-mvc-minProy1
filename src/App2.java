import controller.ControladorVetirinaria;
import model.Modelo;
import view.VeterinariaVistaConsola;
import view.VistaVeterinaria;

public class App2 {
    public static void main(String[] args) {
        Modelo model = new Modelo();
        VistaVeterinaria vista = new VeterinariaVistaConsola();
        ControladorVetirinaria controlador = new ControladorVetirinaria(model, vista);
        controlador.inicializacion();

    }
}
