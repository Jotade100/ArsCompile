package edu.arscompile.utilidades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
public class LectorDeArchivo {

    // Constructor    
    public LectorDeArchivo() {
    }

    //Instancia y getInstancia()

    private static final LectorDeArchivo lectorDeArchivo = new LectorDeArchivo();

    public static LectorDeArchivo getInstancia() {
        return lectorDeArchivo;
    }

    public String leerArchivo(String nombreArchivo){
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(" ");
            }
        } catch (IOException e) {
            System.err.format("Descripción del error: %s%n", e);
        }
        return sb.toString();
    }

}
