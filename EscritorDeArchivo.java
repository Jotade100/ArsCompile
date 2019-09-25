package edu.arscompile.utilidades;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;

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

    public void escribirObjeto(String nombreDeArchivo, List<Token> tokens) {
        try{
            FileOutputStream f = new FileOutputStream(new File(nombreDeArchivo+ ".obj"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            for (Token var : tokens) {
                o.writeObject(var);
            }    
            

            o.close();
			f.close();
        } catch (Exception e) {
            System.out.println("Hubo un error escribiendo en el archivo objeto");
        }
    }

    public void escribir(String nombreDeArchivo, List<Token> tokens) {
        try{
            FileWriter fileWriter = new FileWriter(nombreDeArchivo+".txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            tokens.forEach((action) ->  printWriter.print(action.retornarTokenBonitoLargo() + "\n"));
            printWriter.println();
            printWriter.println("THE END");
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Hubo un error escribiendo en el archivo texto");
        }

        
        
        
    }

}
