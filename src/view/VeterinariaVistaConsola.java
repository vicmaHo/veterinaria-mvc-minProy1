package view;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import controller.ControladorVetirinaria;
import controller.Operacion;
import model.Gato;
import model.Mascota;
import model.Perro;
import model.RazaPerro;

public class VeterinariaVistaConsola implements VistaVeterinaria {
    ControladorVetirinaria controlador;
    static Mascota mascotaCreada;
    
    // -----------Metodos para la consola------------------------------------------------
    public static void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void esperarEnter() {
        System.out.print("\nPresiona enter para continuar... ");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        // scanner.close();
    }
    //----------------------------------------------------------------

    @Override
    public void iniciar(ControladorVetirinaria controlador) {
        this.controlador = controlador; // le indico que el controlador que envian será igual al controlador que tengo como atributo
        limpiarConsola();
        Scanner sc  = new Scanner(System.in);
        byte opc=0;
        do {
            limpiarConsola();          
            System.out.print("\n---------MENU--------\n"
                               +"1. Insertar mascota\n"
                               +"2. Actualizar mascota\n"
                               +"3. Eliminar mascota\n"
                               +"4. Buscar mascota por nombre\n"
                               +"5. Listar todas las mascotas\n"
                               +"6. Ingresar al sub-menu\n"
                               +"0. Salir\n"
                               +"Ingrese una opcion: ");
            opc=sc.nextByte();
            switch (opc) {
                case 1:
                    insertarMascota();
                    controlador.setOperacion(Operacion.INSERTAR);
                    controlador.actionPerformed(null);
                    break;
                case 5:
                    controlador.setOperacion(Operacion.LISTAR);
                    controlador.actionPerformed(null);
                    break;
                default:
                    break;
            }
        }while (opc!=0);
    }

    @Override
    public void insertarMascota() {
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("\n------Agregando Mascota------\n");
            System.out.print("Que tipo de mascota desea agregar?\n" +
                            "1. Perro\n" + 
                            "2. Gato\n" + 
                            "0. Regresar\n" + 
                            "Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer del scanner
            switch(opcion) {
                case 1:
                    limpiarConsola(); agregarPerro();
                    break;
                case 2:
                    limpiarConsola(); agregarGato();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nError, seleccione una opcion correcta."); esperarEnter(); limpiarConsola(); 
                    break;
            }
        }while(opcion != 0);
       
    }

    @Override
    public void actualizarMascota() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarMascota'");
    }

    @Override
    public void eliminarMascota() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarMascota'");
    }

    @Override
    public void buscarMascota() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarMascota'");
    }

    @Override
    public void listarMascotas(ArrayList<Mascota> mascotas){
        System.out.println("\n-----Lista de todas las mascotas-----");
        for (Mascota mascota : mascotas) {
            System.out.println("--------------------");
            mascota.mostrarInformacion();
        }
        esperarEnter();
    }

    @Override
    public Mascota getMascota() {
        return mascotaCreada;
    }

    // Metodos auxiliares para insertar la mascota
    public static void agregarPerro(){
        System.out.println("----Agregando Perro----");
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre del perro: ");
        String nombrePerro = sc.nextLine();
        // Menu para ingresar Raza
        RazaPerro raza = asignarRaza();

        // Asignacion de vacunas
        ArrayList<String> vacunas = new ArrayList<String>();
        byte opcVacunas = 0;
        System.out.print("Desea agregar vacunas a la mascota\n" + 
                            "1. Si\n"+
                            "2. No\n" +
                            "Ingrese el numero de la opcion que desea: ");
        opcVacunas = sc.nextByte();
        if(opcVacunas == 1){
            vacunas = menuIngresarVacunas();
        }

        System.out.print("\nIngrese el precio del perro: ");
        double precioPerro = sc.nextDouble();
        sc.nextLine(); // limpio entrada
        System.out.print("Ingrese el pais de origen del perro: ");
        String paisOrigenP = sc.nextLine();

        //Creo el objeto Perro y lo agrego a la lista de mascotas
        Perro nuevoPerro = new Perro(nombrePerro, raza, precioPerro, paisOrigenP);
        nuevoPerro.setVacunas(vacunas); // agrego el array de vacunas
        mascotaCreada = nuevoPerro; // la mascota que acabo de crear se alamacena en mascotaCreada de la vista
        
        limpiarConsola();
        System.out.printf("\nLa mascota %s se ha agregado con exito\n", nuevoPerro.getNombre());
        esperarEnter();
        limpiarConsola();
    }

    private static RazaPerro asignarRaza() {
        System.out.println("\n-- Ingresando raza del perro --\nRazas:");
        RazaPerro razaSeleccionada = RazaPerro.Otro;
    
        //Crear una lista de razas, usando el enum, esta lista sera String
        List<String> razasList = new ArrayList<>();
        for (RazaPerro raza : RazaPerro.values()) {
            System.out.printf("--> %s\n", raza); //  Muestro las razas
            razasList.add(raza.name()); // añado la raza a la lista de razas
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre de una de las razas disponibles: ");
        //Comprobacion
        String seleccion = sc.nextLine();
        while (!razasList.contains(seleccion)){ // si el lo que escribo no se encuenta dentro de la lista entonces se ejecuta
            System.out.printf("\nError, la raza que ha ingresado %s no existe. Ingrese una de las establecidas.\n"+
            "Ingrese el nombre de una de las razas disponibles: ", seleccion);
            seleccion = sc.nextLine();
        }
        razaSeleccionada = RazaPerro.valueOf(seleccion);      
        return razaSeleccionada;
    }

    public static ArrayList<String> menuIngresarVacunas(){
        ArrayList<String> vacunas = new ArrayList<String>();
        byte opc = 0; 
        Scanner sc = new Scanner(System.in);
        do{
            String vacuna = "";
            System.out.println("\n-- Agregando vacuna--");
            System.out.print("Digite el nombre de la vacuna: ");
            vacuna = sc.nextLine();
            vacunas.add(vacuna);
            System.out.print("\nDesea agregar otra vacuna a la mascota?\n" + 
                                "1. Si\n"+
                                "2. No\n" +
                                "Ingrese el numero de la opcion que desea: ");
            opc = sc.nextByte();
            sc.nextLine();
        }while(opc < 2 );
        return vacunas;
    }
    
    /*Funcion que permite agregar un Gato, cuenta con el mismo funcionamiento de la funcion para agregar Perro */
    public static void agregarGato(){
        System.out.println("----Agregando Gato----");
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre del gato: ");
        String nombreGato = sc.nextLine();

        // Asignacion de vacunas
        ArrayList<String> vacunas = new ArrayList<String>();
        byte opcVacunas = 0;
        System.out.print("Desea agregar vacunas a la mascota\n" + 
                            "1. Si\n"+
                            "2. No\n" +
                            "Ingrese el numero de la opcion que desea: ");
        opcVacunas = sc.nextByte();
        if(opcVacunas == 1){
            vacunas = menuIngresarVacunas();
        }

        System.out.print("\nIngrese el precio del gato: ");
        double precioGato = sc.nextDouble();
        sc.nextLine(); // limpio entrada
        System.out.print("Ingrese el pais de origen del gato: ");
        String paisOrigenG = sc.nextLine();
      
        //Creo el objeto Gato y lo agrego a la lista de mascotas
        Gato nuevoGato = new Gato(nombreGato, precioGato, paisOrigenG );
        nuevoGato.setVacunas(vacunas);
        mascotaCreada = nuevoGato;

        limpiarConsola();
        System.out.printf("\nLa mascota %s se ha agregado con exito\n", nuevoGato.getNombre());
        esperarEnter();
        limpiarConsola();
    }
    
}
