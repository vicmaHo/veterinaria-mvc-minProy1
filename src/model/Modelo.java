package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Modelo {
    // Atributo
    ArrayList<Mascota> listaDeMascotas = new ArrayList<Mascota>();

   public Modelo() {
        // Perro kaiser = new Perro("Kaiser", RazaPerro.Pastor, 1_000_000d, "Alemania");
        // ArrayList<String> vacunasKaiser = new ArrayList<String>();
        // vacunasKaiser.addAll(Arrays.asList("malota", "buenota", "leptospira"));
        // kaiser.setVacunas(vacunasKaiser);
        // listaDeMascotas.add(kaiser);

        // Gato pancho = new Gato("Pancho", 543_000d, "Colombia");
        // ArrayList<String> vacunasPancho = new ArrayList<String>();
        // vacunasPancho.addAll(Arrays.asList("fea", "malota", "burgdorferi", "cariñosa"));
        // pancho.setVacunas(vacunasPancho);
        // listaDeMascotas.add(pancho);

        // Perro baki = new Perro("Baki", RazaPerro.Labrador,985_900d, "Francia");
        // ArrayList<String> vacunasbaki = new ArrayList<String>();
        // vacunasbaki.addAll(Arrays.asList("buenota", "bronchiseptica", "burgdorferi"));
        // baki.setVacunas(vacunasbaki);
        // listaDeMascotas.add(baki);


        // Perro alfred = new Perro("Alfred", RazaPerro.Tacita, 655_000d, "USA");
        // ArrayList<String> vacunasAlfred = new ArrayList<String>();
        // vacunasAlfred.addAll(Arrays.asList("buenota", "bronchiseptica", "burgdorferi", "jols", "tetanus"));
        // alfred.setVacunas(vacunasAlfred);
        // listaDeMascotas.add(alfred);


        // Gato mono = new Gato("Mono",1_000_000_000d, "Sudan");
        // ArrayList<String> vacunasMono = new ArrayList<String>();
        // vacunasMono.addAll(Arrays.asList());
        // mono.setVacunas(vacunasMono);
        // listaDeMascotas.add(mono);
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

    public void limpiarListaMascotras(){
        listaDeMascotas.clear();
    }

    // metodos para cargar o guardar los datos en archivos
    public void guardarDatos(){
        try {
            RandomAccessFile archivo = new RandomAccessFile("files/datos.txt", "rw");
            for (Mascota mascota : listaDeMascotas) {
                if(mascota instanceof Gato){
                    Gato gato = (Gato) mascota;
                    archivo.writeBytes("gato," + gato.getNombre() + "," + corregirFormatoPrecio(gato.getPrecio()) + "," + gato.getPaisOrigen() + "," );
                    for (String vacuna : gato.getVacunas()) {
                        archivo.writeBytes(vacuna);
                        archivo.writeBytes(",");
                    }
                    archivo.writeBytes("\n");
                }else{
                    Perro perro = (Perro) mascota;
                    archivo.writeBytes("perro," + perro.getNombre() + ","+ perro.getRaza().name() + "," + corregirFormatoPrecio(perro.getPrecio()) + "," + perro.getPaisOrigen() + ",");
                    for (String vacuna : perro.getVacunas()) {
                        archivo.writeBytes(vacuna);
                        archivo.writeBytes(",");
                    }
                    archivo.writeBytes("\n");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cargarDatos(){
        try {
            RandomAccessFile archivo = new RandomAccessFile("files/datos.txt", "r");
            String linea = "";
             while ((linea = archivo.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(linea, ",");
                if(tokenizer.nextToken().equals("gato")){
                    Gato gato = new Gato(tokenizer.nextToken(), Double.parseDouble(tokenizer.nextToken()), tokenizer.nextToken());
                    // String[] vacunas = tokenizer.nextToken().split(",");
                    ArrayList<String> vacunas = new ArrayList<String>();
                    while(tokenizer.hasMoreTokens()){
                        vacunas.add(tokenizer.nextToken());
                    }
                    gato.setVacunas(vacunas);
                    listaDeMascotas.add(gato);
                }else{
                    Perro perro = new Perro(tokenizer.nextToken(),RazaPerro.valueOf(tokenizer.nextToken()), Double.parseDouble(tokenizer.nextToken()), tokenizer.nextToken());

                    ArrayList<String> vacunas = new ArrayList<String>();
                    while(tokenizer.hasMoreTokens()){
                        vacunas.add(tokenizer.nextToken());
                    }
                    perro.setVacunas(vacunas);
                    listaDeMascotas.add(perro);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String corregirFormatoPrecio(double precioSinFormato){
        String numeroFormateado = String.format("%.0f", precioSinFormato);
        return numeroFormateado;
    }


}
