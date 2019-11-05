package edu.arscompile.irt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.arscompile.utilidades.EscritorDeArchivo;
import edu.arscompile.utilidades.LectorDeArchivo;
import edu.arscompile.scanner.Comparador;
import edu.arscompile.parser.Parser;
import edu.arscompile.modelos.IrtItem;
import edu.arscompile.modelos.Token;
import edu.arscompile.modelos.Objeto;
import edu.arscompile.modelos.IrtItem;

public class Irt {
    // Constructor

    Objeto cabeza = new Objeto(); //árbol de parseo

    IrtItem raiz = new IrtItem();


    public Irt(){}

    private static Irt instancia = new Irt();

    public static Irt getInstancia() {
        if(instancia == null) {
            instancia = new Irt();
        }
        return instancia;
    }

    
    public void casoDeInstruccion(Objeto objeto) {
        IrtItem nodo = new IrtItem();
        String cadena;
        switch (objeto.getType().getNombre()) {
            case "FieldDec":
                nodo.setTipo("FieldDec");
                if(objeto.getTokens().get(0).getValue().toString().equalsIgnoreCase("int")) {
                    nodo.setValor(objeto.getTokens().get(1).getValue().toString() + ": .word 0"); //declaración de tipo int
                } else { 
                    nodo.setValor(objeto.getTokens().get(1).getValue().toString() + ": .word 0"); //declaración de boolean
                }
                break;
            case "FieldArrayDec":
                nodo.setTipo("FieldArrayDec");
                cadena = objeto.getTokens().get(1).getValue().toString() +" .word 0";
                for (int i = 1; i < Integer.parseInt(objeto.getTokens().get(3).getValue().toString()); i++) {
                    cadena = cadena + ", 0";
                    
                }
                
                nodo.setValor(cadena);
                break;
            case "MethodDec":
                nodo.setTipo("MethodDec");
                break;

            case "Block":
                nodo.setTipo("Block");
                break;
            case "Variable":
                nodo.setTipo("Variable");
                break;
            case "LocationExpresion":
                nodo.setTipo("LocationExpresion");
                cadena = "lw $t0, " + objeto.getTokens().get(0).getValue().toString() ;
                break;
            case "ArrayLocationExpresion":
                nodo.setTipo("ArrayLocationExpresion");
                cadena = "lw $t0, " + objeto.getTokens().get(0).getValue().toString() ;
                break;
            default:
                break;
        }

    }
    
    
    
    public void recorrerArbolParseo(Objeto objeto, int tab, boolean largo){
        for (int i = 0; i < tab; i++) {
            System.out.print("\t");
        }
        if(largo){
            objeto.imprimirTokenBonitoLargo();
        } else {
            objeto.imprimirTokenBonitoCorto();
        }
        
        System.out.println();
        for (Objeto var : objeto.getHijos()) {
            recorrerArbolParseo(var, tab+1, largo);
        }

    }

    



}