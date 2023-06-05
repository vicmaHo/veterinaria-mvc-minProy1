package main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import model.Gato;
import model.Mascota;
import model.Perro;
import model.RazaPerro;

public class OldApp {

    // Array que contiene las mascotas 
    static ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

    //----------------------------Metodos de Consola--------------------------------------------------
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

    //----------------------------Menus--------------------------------------------------
    public static void MenuPrincipal() {
        limpiarConsola();
        Scanner sc= new Scanner(System.in);
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
            switch(opc){
                case 1: limpiarConsola(); insertarMascota();
                break;
                case 2: limpiarConsola(); actualizarMascota();
                break;
                case 3: limpiarConsola(); eliminarMascota();
                break;
                case 4: limpiarConsola(); buscarMascotaPorNombre();
                break;
                case 5: limpiarConsola(); listarMascotas();
                break;
                case 6: limpiarConsola(); SubMenu();
                break;
                case 0: limpiarConsola(); System.out.println("Gracias por usar el programa.");
                break;
                default: System.out.println("\nError, seleccione una opcion correcta."); esperarEnter();
                break;
            } 
        } while (opc!=0);  
    }

    //Sub menu para gestionar algunos datos estadisticos de las mascotas 
    public static void SubMenu() {
        limpiarConsola();
        Scanner sc= new Scanner(System.in);
        byte opc=0;
        do {
            limpiarConsola();          
            System.out.print("\n---------SUB MENU--------\n"
                               +"1. Que mascota tiene la vacuna malota\n"
                               +"2. Cual es el top 5 de las mascotas mas costosas\n"
                               +"3. Que mascotas no tienen país de origen en latinoamérica\n"
                               +"0. Volver al menu principal\n"
                               +"Ingrese una opcion: ");
            opc=sc.nextByte();
            switch(opc){
                case 1: limpiarConsola(); mascotasConVacunaMalota();
                break;
                case 2: limpiarConsola(); topMascotasCostosas();
                break;
                case 3: limpiarConsola(); MascotasConOrigenExtranjero();
                break;
                case 0: limpiarConsola(); 
                break;
                default: System.out.println("\nError, seleccione una opcion correcta."); esperarEnter();
            } 
        } while (opc!=0);  
        
    }

//--------------------------------------Metodos del Menu Principal------------------------------------------------

    /* Menú que permite seleccionar alguna de las mascotas y posteriormente insertarla en la lista de Mascotas */
    public static void insertarMascota() {
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

    /* funcion que permite agregar un objeto tipo Perro. Pide los datos necesarios, los cuales son almacenados en
    variables, las cuales posteriormente se usan como argumentos para crear un objeto de tipo Perro, el cual es
    almacenado en la lista de mascotas */ 
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
        mascotas.add(nuevoPerro);
        
        limpiarConsola();
        System.out.printf("\nLa mascota %s se ha agregado con exito\n", nuevoPerro.getNombre());
        esperarEnter();
        limpiarConsola();
    }

    /*Asignar la raza de un perro requiere una comprobacion adicional para verificar que lo ingresado sea igual a
        uno de los valores del Enum de Razas. La funcion crea una lista de las razas transformandolas a Strings,
        imprime estas en pantalla, posteriormente pide al usuario el ingreso del nombre de alguna de estas razas
        y verifica si esa raza se encuentra en la lista de strings. Cuando el valor se encuentre en la lista
        se convierte ese valor en su correspondiente en el Enum de razas y se retorna esta raza */
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

    /*Asigna las vacunas a cada mascota. Creamos una array que posteriormente sera al que se agregaran vacunas y el cual sera 
     * retornado, entramos en un do while el cual permite agregar mas de una vacuna preguntando al usuario si desea agregar más
     * cada vacuna es agregada al arraylist, al finalizar se retorna un arrayList que será el atributo de la mascota.
     */
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
        mascotas.add(nuevoGato);

        limpiarConsola();
        System.out.printf("\nLa mascota %s se ha agregado con exito\n", nuevoGato.getNombre());
        esperarEnter();
        limpiarConsola();
    }

    /* Funcion que permite actualziar la informacion de una mascota existente. Verificamos si la mascota existe y de que tipo es
        posteriormente pedimos los datos nuevos, haciendo uso de variables, y luego usamos los setters de la clase para modificar
        los atributos anteriores por los nuevos.
     */
    public static void actualizarMascota(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--------Actualizar Mascota--------");
        System.out.print("Ingrese el nombre de la mascota que desea actualizar: ");
        String nombreMascota = sc.nextLine();
        boolean mascotaExiste = false;
        limpiarConsola();
        for (Mascota mascota : mascotas) {
            if (mascota instanceof Perro) { 
                Perro perro = (Perro) mascota; 
                if (perro.getNombre().equals(nombreMascota)) {

                    //Proceso de actualizacion
                    System.out.println("--Mascota seleccionada " + nombreMascota + "--");
                    mascota.mostrarInformacion();
                    System.out.print("\nIngrese el nuevo nombre del perro: ");
                    String nombre = sc.nextLine();

                    //actualizacion de las vacunas
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

                    System.out.print("Ingrese el nuevo precio del perro: ");
                    double precio = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Ingrese el pais de origen del perro: ");
                    String paisOrigen = sc.nextLine();
                    // Agrego todo a perro con los setters
                    perro.setNombre(nombre);
                    perro.setVacunas(vacunas);
                    perro.setPrecio(precio);
                    perro.setPaisOrigen(paisOrigen);
                    limpiarConsola();
                    System.out.println("La mascota se ha actualizaddo con exito.");
                    esperarEnter();
                    mascotaExiste = true;
                    break;
                }
            }else if(mascota instanceof Gato){
                Gato gato = (Gato) mascota; // Hace un cast de la mascota a un objeto de tipo Gato
                if (gato.getNombre().equals(nombreMascota)) {
                    System.out.println("--Mascota seleccionada " + nombreMascota + "--");
                    mascota.mostrarInformacion();
                    System.out.print("\nIngrese el nuevo nombre del gato: ");
                    String nombre = sc.nextLine();
                      //actualizacion de las vacunas
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

                    System.out.print("Ingrese el nuevo precio del gato: ");
                    double precio = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Ingrese el pais de origen del gato: ");
                    String paisOrigen = sc.nextLine();
                    // Agrego todo a perro con los setters
                    gato.setNombre(nombre);
                    gato.setVacunas(vacunas);
                    gato.setPrecio(precio);
                    gato.setPaisOrigen(paisOrigen);
                    limpiarConsola();
                    System.out.println("La mascota se ha actualizaddo con exito.");
                    esperarEnter();
                    mascotaExiste = true;
                    break;
                }
            }           
        }         
        if (!mascotaExiste){
            System.out.println("La mascota " + nombreMascota + " no existe.");
            esperarEnter();
        }  
    }

    /* Listo todas las mascotas y sus datos correspondientes. La funcion recorre toda la lista de mascotas y por cada mascota
     llama el metodo mostraInformacion() el cual tiene un formato predefinido para mostrar todos los datos del objeto.
     */
    public static void listarMascotas(){
        System.out.println("\n-----Lista de todas las mascotas-----");
        for (Mascota mascota : mascotas) {
            System.out.println("--------------------");
            mascota.mostrarInformacion();
        }
        esperarEnter();
    }
    public static void eliminarMascota(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--------Eliminar Mascota--------");
        System.out.print("Ingrese el nombre de la mascota a eliminar: ");
        String buscar = sc.nextLine();
        boolean mascotaExiste = false;
        limpiarConsola();

        for (Mascota mascota : mascotas) {
            if (mascota instanceof Perro) { // Verifica que la mascota sea un perro
                Perro perro = (Perro) mascota; // Hace un cast de la mascota a un objeto de tipo Perro
                if (perro.getNombre().equals(buscar)) {
                    System.out.println("---Datos de la mascota " + buscar+ "---");
                    mascota.mostrarInformacion();          
                    mascotaExiste = true;
                    if(mascotaExiste == true){
                        System.out.println("\n¿Esta seguro de eliminar mascota "+buscar+"?\n"+
                        "1 -> [Si]  2-> [No]");
                        byte opcion = sc.nextByte(); //opcion para consultar si desea eliminar
                        if(opcion == 1){
                            mascotas.remove(perro); //elimina el objecto perro de la lista mascotas
                            System.out.println("!Se ha eliminado mascota con exito!");
                        }else{
                            break;
                        }
                    }
                    break;
                }
            }else if(mascota instanceof Gato){
                Gato gato = (Gato) mascota; // Hace un cast de la mascota a un objeto de tipo Gato
                if (gato.getNombre().equals(buscar)) {
                    System.out.println("---Datos de la mascota " + buscar + "---");
                    mascota.mostrarInformacion();
                    mascotaExiste = true;
                    if(mascotaExiste == true){
                        System.out.println("\n¿Esta seguro de eliminar mascota "+buscar+"?\n"+
                        "1 -> [Si]  2-> [No]");
                        byte opcion2 = sc.nextByte();  //opcion para consultar si desea eliminar
                        if(opcion2 == 1){
                            mascotas.remove(gato); //elimina el objecto gato de la lista mascotas
                            System.out.println("!Se ha eliminado mascota con exito!");
                        }else{
                            break;
                        }
                    }
                    break;   
                }
            }           
        }         
        if (!mascotaExiste){
            System.out.println("La mascota " + buscar + " no existe.");}  
        esperarEnter();          
    }

    /* Busco mascotas por su nombre e imprimo los datos de esta misma. La funcion recorre toda la lista de mascotas y comprueba uno por 
        los nombres de las mascotas, si esta equivale al nombre digitado, imprime los datos de lo contrario dara un mensaja con que el nombre de la mascota 
        no existe.
     */
    public static void buscarMascotaPorNombre(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--------Buscando mascota por nombre--------");
        System.out.print("Ingrese el nombre de la mascota: ");
        String nombreMascota = sc.nextLine();
        boolean mascotaExiste = false;
        limpiarConsola();
        for (Mascota mascota : mascotas) {
            if (mascota instanceof Perro) { // Verifica que la mascota sea un perro
                Perro perro = (Perro) mascota; // Hace un cast de la mascota a un objeto de tipo Perro
                if (perro.getNombre().equals(nombreMascota)) {
                    System.out.println("--Datos de la mascota " + nombreMascota + "--");
                    mascota.mostrarInformacion();                    
                    mascotaExiste = true;
                    break;
                }
            }else if(mascota instanceof Gato){
                Gato gato = (Gato) mascota; // Hace un cast de la mascota a un objeto de tipo Gato
                if (gato.getNombre().equals(nombreMascota)) {
                    System.out.println("--Datos de la mascota " + nombreMascota + "--");
                    mascota.mostrarInformacion();
                    mascotaExiste = true;
                    break;   
                }
            }           
        }         
        if (!mascotaExiste){
            System.out.println("La mascota " + nombreMascota + " no existe.");}  
        esperarEnter();          
    }

    //--------------------------------------Metodos del Sub Menu------------------------------------------------
    
    /* Realiza un top 5 de las mascotas más costosas. Primero creo un array vacio que funcionara para alamacenar las mascotas
     posteriormente le asigno las mascotas sin ordenar y despues lo ordeno haciendo uso de .sort de la clase Collections 
     que se conecta con el compareto que se implemento en la clase Mascota, uso el reverseOrder para que se ordenen de mayor a menor
     y por ultimo imprimo el array ordenado
    */
    public static void topMascotasCostosas(){
        ArrayList<Mascota> mascotasOrdenadas = new ArrayList<>();
        mascotasOrdenadas = mascotas;
        System.out.println("--Top 5 Mascotas mas costosas--");
        Collections.sort(mascotasOrdenadas, Collections.reverseOrder());
        // Recorro el array ordenado y lo imprimo
        for (int i = 1; i <= 5; i++) {
            System.out.println("\n--Top " + i + "--");
            mascotasOrdenadas.get(i-1).mostrarInformacion();
        }
        esperarEnter();
    }

    /* Esta funcion permite comprobar que mascotas tienen la vacuna malota, la funcion recorre todas las macotas y en cada mascota 
    recorre los nombres de la vacunas de esta misma, y comprueba una por mi si alguna de las vacunas de las mascota es la vacuna
    malota, en caso de que asi sea se imprimira la informacion de la mascota, y en el caso de que no, se imprimira que no hay ninguna
    mascota con la vacuna malota.
     * 
     */
    public static void mascotasConVacunaMalota() {
        System.out.println("\n--------Mascotas con la vacuna malota--------");
        boolean alMenosUnaMascotaConVacunaMalota = false;
        for (Mascota mascota : mascotas) {
            if (mascota instanceof Perro) {
                Perro perro = (Perro) mascota;
                boolean tieneVacunaMalota = false;
                for (String vacuna : perro.getVacunas()) { 
                    if (vacuna.equalsIgnoreCase("malota")) {
                        tieneVacunaMalota = true;
                        
                    }
                }
                if (tieneVacunaMalota) {
                    System.out.println("La mascota con la vacuna 'malota' es: ");
                    perro.mostrarInformacion();
                    System.out.println();
                    alMenosUnaMascotaConVacunaMalota = true;
                    
                }
            }
            else if(mascota instanceof Gato) {
                Gato gato = (Gato) mascota;
                boolean tieneVacunaMalota = false;
                for (String vacuna : gato.getVacunas()) { 
                    if (vacuna.equalsIgnoreCase("malota")) {
                        tieneVacunaMalota = true;
                        
                    }
                }
                if (tieneVacunaMalota) {
                    System.out.println("La mascota con la vacuna 'malota' es:");
                    gato.mostrarInformacion();
                    System.out.println();
                    alMenosUnaMascotaConVacunaMalota = true;
                    
                }
            }
        if (!alMenosUnaMascotaConVacunaMalota) {
            System.out.println("No hay ninguna mascota con la vacuna malota.");
        }
        }
        esperarEnter();
    }


    /* Metodo que imprime las mascotas que son extranjeras, no originarias de Latinoamerica*/
    public static void MascotasConOrigenExtranjero() {
        
        byte contador = 0;
        ArrayList<String> paisesLatinos = new ArrayList<>(); //Arraylist con los paises latinoamericanos
        paisesLatinos.addAll(Arrays.asList("Argentina", "Bolivia", "Brasil", "Chile", "Colombia", "Costa Rica", "Cuba", "Ecuador", "El Salvador", "Guatemala", "Haiti", "Honduras", "Jamaica", "Mexico", "Nicaragua", "Panama", "Paraguay", "Peru", "Puerto Rico", "Republica Dominicana", "Uruguay", "Venezuela"));
        System.out.println("\n--------Mascotas no originarias en latinoamerica--------");
        for (Mascota mascota : mascotas) {
            if (mascota instanceof Perro) { // Verifica que la mascota sea un perro
                Perro perro = (Perro) mascota; // Hace un cast de la mascota a un objeto de tipo Perro
                if (!paisesLatinos.contains(perro.getPaisOrigen())) {  // Verifica si el país de origen del perro no está en el ArrayList "paisesLatinos"
                    mascota.mostrarInformacion();                 
                    System.out.println("________________________________");
                    contador++;
                }
            }else if(mascota instanceof Gato){
                Gato gato = (Gato) mascota; // Hace un cast de la mascota a un objeto de tipo Gato
                if (!paisesLatinos.contains(gato.getPaisOrigen())) {  // Verifica si el país de origen del gato no está en el ArrayList "paisesLatinos"
                    mascota.mostrarInformacion();                 
                    System.out.println("________________________________");
                    contador++;
                }
            }    
        } 
        
        System.out.print("\nTotal de mascotas extranjeras: "+contador);

        esperarEnter();  
    }
    public static void main(String[] args) throws Exception {

        //Agregando mascotas para tener datos de antemano
        Perro kaiser = new Perro("Kaiser", RazaPerro.Pastor, 1_000_000d, "Alemania");
        ArrayList<String> vacunasKaiser = new ArrayList<String>();
        vacunasKaiser.addAll(Arrays.asList("malota", "buenota", "leptospira"));
        kaiser.setVacunas(vacunasKaiser);
        mascotas.add(kaiser);

        Gato pancho = new Gato("Pancho", 543_000d, "Colombia");
        ArrayList<String> vacunasPancho = new ArrayList<String>();
        vacunasPancho.addAll(Arrays.asList("fea", "malota", "burgdorferi", "cariñosa"));
        pancho.setVacunas(vacunasPancho);
        mascotas.add(pancho);

        Perro baki = new Perro("Baki", RazaPerro.Labrador,985_900d, "Francia");
        ArrayList<String> vacunasbaki = new ArrayList<String>();
        vacunasbaki.addAll(Arrays.asList("buenota", "bronchiseptica", "burgdorferi"));
        baki.setVacunas(vacunasbaki);
        mascotas.add(baki);


        Perro alfred = new Perro("Alfred", RazaPerro.Tacita, 655_000d, "USA");
        ArrayList<String> vacunasAlfred = new ArrayList<String>();
        vacunasAlfred.addAll(Arrays.asList("buenota", "bronchiseptica", "burgdorferi", "jols", "tetanus"));
        alfred.setVacunas(vacunasAlfred);
        mascotas.add(alfred);


        Gato mono = new Gato("Mono",1_000_000_000d, "Sudan");
        ArrayList<String> vacunasMono = new ArrayList<String>();
        vacunasMono.addAll(Arrays.asList());
        mono.setVacunas(vacunasMono);
        mascotas.add(mono);

        MenuPrincipal();    
        
    } 
}
