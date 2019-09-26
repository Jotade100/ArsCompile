package edu.arscompile.utilidades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.arscompile.modelos.Token; // para evitar confusiones

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;


public class LectorDeArchivo {

    // Constructor    
    public LectorDeArchivo() {
    }

    //Instancia y getInstancia()

    private static final LectorDeArchivo lectorDeArchivo = new LectorDeArchivo();

    public static LectorDeArchivo getInstancia() {
        return lectorDeArchivo;
    }

    public List<String> leerArchivo(String nombreArchivo){
        List<String> sb = new ArrayList<>();
        int contadorDeLineas = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.add(contadorDeLineas, line);
                contadorDeLineas++;
            }
        } catch (IOException e) {
            System.err.format("Descripci\u00f3n del error: %s%n", e);
        }
        return sb;
    }

    public List<Token> leerTokens(String nombreArchivo){
        List<Token> sb = new ArrayList<>();
        try {

            FileInputStream fi = new FileInputStream(new File(nombreArchivo + ".obj"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            boolean seguir = true;
            
            sb = (List<Token>) oi.readObject();
            // for (Token var : sb) {
            //     var.imprimirTokenBonitoLargo(); 
            // }
            System.out.println();

            oi.close();
			fi.close();
        
        } catch (Exception e) {
            System.err.format("Descripci\u00f3n del error: %s%n", e);
            e.printStackTrace();
        }
        return sb;
    }

}
