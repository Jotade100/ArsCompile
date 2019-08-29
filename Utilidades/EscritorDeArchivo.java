package edu.arscompile.utilidades;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import edu.arscompile.modelos.Token;


public class EscritorDeArchivo {

    // Constructor    
    public EscritorDeArchivo() {
    }

    //Instancia y getInstancia()

    private static final EscritorDeArchivo EscritorDeArchivo = new EscritorDeArchivo();

    public static EscritorDeArchivo getInstancia() {
        return EscritorDeArchivo;
    }

    public void escribir(String nombreDeArchivo, List<Token> tokens) {
        try{
            FileWriter fileWriter = new FileWriter(nombreDeArchivo);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            tokens.forEach((action) ->  printWriter.print(action.retornarTokenBonitoLargo()));
            printWriter.println();
            printWriter.println("El servicio fue traído a ustedes por Juan Diego");
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Hubo un error leyendo el archivo");
        }
        
        
    }

}
