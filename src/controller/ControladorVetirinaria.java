package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Mascota;
import model.Modelo;
import view.VistaVeterinaria;
public class ControladorVetirinaria implements ActionListener {
    Modelo model; // agrego el modelo
    Operacion operacion;
    VistaVeterinaria vista; // agrego la interface de vista
    
    //  Constructor que hace uso del modelo y de la vista
    public ControladorVetirinaria(Modelo model, VistaVeterinaria vista) {
        this.model = model;
        this.vista = vista;
    }

    // setters y getters
    public Operacion getOperacion() {
        return operacion;
    }

    public void setOperacion(Operacion operacion) {
        this.operacion = operacion;
    }

    //Metodos

    //Inicializzo la vista
    public void inicializacion(){
        vista.iniciar(this);
    }

    // Desencadenantes de acciones 
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (operacion) {
            case INSERTAR:
                Mascota nuevaMascota = vista.getMascota();
                model.insertarMascotaAlista(nuevaMascota);
                break;
            case ACTUALIZAR:
                vista.actualizarMascota(model.getListaMascotasDisponibles());
                break;
            case LISTAR:
                vista.listarMascotas(model.getListaMascotasDisponibles());
                break;
            default:
                break;
        }
    }    
}
