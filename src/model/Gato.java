package model;

public class Gato extends Mascota implements MejorAmigo {
    // Atributos por defecto - constantes
    public static final String NOMBRE_POR_DEFECTO = "NN";
    // Atributos
    private String nombre;

    // Constructores
    // 3 argumentos
    public Gato(String nombre, double precio, String paisOrigen){
        super(precio, paisOrigen);
        this.nombre = nombre;
    }

    // Getters - setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Metodos
    @Override
    public String saludar() {
       return String.format("Miauu!, soy %s", getNombre());
    }


    @Override
    public String apodo() {
        return String.format("Michi :D");
    }


    @Override
    public String tributo() {
        return String.format("Rata :(");
    }

    @Override
    public void mostrarInformacion() {
        System.out.printf("ID: %d%n" +
                            "Tipo: Gato%n" + 
                            "Nombre: %s%n" +  
                            "Cantidad de vacunas: %d%n" + 
                            "Precio: %.2f%n" + 
                            "paisOrigen: %s%n",
                            super.getId(), getNombre(), getCantidadVacunas(), super.getPrecio(), super.getPaisOrigen());

    }
    
}
