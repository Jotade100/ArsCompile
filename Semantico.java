package edu.arscompile.semantic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.arscompile.modelos.Token;
import edu.arscompile.modelos.Tipo;
import edu.arscompile.modelos.Objeto;
import edu.arscompile.modelos.Simbolo;
import edu.arscompile.modelos.CeldaParser;
import edu.arscompile.utilidades.EscritorDeArchivo;
import edu.arscompile.utilidades.LectorDeArchivo;
import edu.arscompile.parser.Parser;

public class Semantico {

    Objeto raiz;
    

    List<Simbolo> tablaSimbolos = new ArrayList<>();

    public Semantico(Objeto cabeza) {
        this.raiz = cabeza;
    }

    public Semantico() {

    }

    public void tablaSimbolos(Objeto cabeza, int scope, String alcance) {
        if(cabeza.getType().getNombre().equalsIgnoreCase("FieldDec")){
            tablaSimbolos.add(new Simbolo(cabeza.getType(), cabeza.getTokens().get(0).getValue().toString(), cabeza.getTokens().get(1).getValue().toString(), scope, alcance.substring(0, alcance.length()-2), cabeza));
        } else if(cabeza.getType().getNombre().equalsIgnoreCase("MethodDec")) {
            tablaSimbolos.add(new Simbolo(cabeza.getType(), cabeza.getTokens().get(0).getValue().toString(), cabeza.getTokens().get(1).getValue().toString(), scope, alcance.substring(0, alcance.length()-2), cabeza));
        } else if(cabeza.getType().getNombre().equalsIgnoreCase("ParamDec")) {
            tablaSimbolos.add(new Simbolo(cabeza.getType(), cabeza.getTokens().get(0).getValue().toString(), cabeza.getTokens().get(1).getValue().toString(), scope, alcance.substring(0, alcance.length()-2), cabeza));
        } else if(cabeza.getType().getNombre().equalsIgnoreCase("Variable")) {
            tablaSimbolos.add(new Simbolo(cabeza.getType(), cabeza.getTokens().get(0).getValue().toString(), cabeza.getTokens().get(1).getValue().toString(), scope, alcance.substring(0, alcance.length()-2), cabeza));
        }
        if(cabeza.getType().getNombre().contains("MethodCall")) {
            if(!buscarSiExiste(cabeza.getTokens().get(0).getValue().toString(), alcance.substring(0, alcance.length()-2))){
                System.out.println("Variable no inicializada con nombre '" + cabeza.getTokens().get(0).getValue().toString() + "' en la l\u00ednea "+ (cabeza.getTokens().get(0).getLeft()+1));
            }
        } else if(cabeza.getType().getNombre().contains("Location")) {
            if(!buscarSiExiste(cabeza.getTokens().get(0).getValue().toString(), alcance.substring(0, alcance.length()-2))){
                System.out.println("Variable no inicializada con nombre '" + cabeza.getTokens().get(0).getValue().toString() + "' en la l\u00ednea "+ (cabeza.getTokens().get(0).getLeft()+1));
            }
        } else if(cabeza.getType().getNombre().contains("Variable")) {
            if(!buscarSiExiste(cabeza.getTokens().get(1).getValue().toString(), alcance.substring(0, alcance.length()-2))){
                System.out.println("Variable no inicializada con nombre '" + cabeza.getTokens().get(1).getValue().toString() + "' en la l\u00ednea "+ (cabeza.getTokens().get(0).getLeft()+1));
            }
        }
        scope++;
        for (int i = 0; i < cabeza.getHijos().size(); i++) {
            tablaSimbolos(cabeza.getHijos().get(i), scope, alcance+"."+i);
            
        }
    }

    public boolean buscarSiExiste(String nombre, String alcance) {
        boolean resultado = false;
        //System.out.println(nombre+ alcance);
        for (Simbolo var : tablaSimbolos) {
            if(var.getNombre().equals(nombre)){
                
                for (int i = alcance.length(); i >= 1; i=i-2) {
                    if(var.getAlcance().equals(alcance.substring(0, i))){
                        resultado = true;
                        return resultado;
                    }
                }
                
            }
            
            
        }
        return resultado;
    }

    public void crearTablaSimbolos(boolean debug) {
        int scope = 0;
        tablaSimbolos(this.raiz, scope, "1");
        if(debug){
            System.out.println("\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2566\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2566\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2566\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2566\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557");
            System.out.println("\u2551\tTipo\t\u2551\t\tClase\t\t\u2551\tNombre del ID\t\t\u2551\t\tAlcance\t\t\u2551\t\t Valor \t\t\u2551");
            System.out.println("\u2560\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u256C\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u256C\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u256C\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u256C\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2563");
            
            for (Simbolo var : tablaSimbolos) {
                System.out.print("");
                var.imprimirSimbolo();
                System.out.println();
                
            }
            System.out.println("\u255A\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2569\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2569\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2569\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2569\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255D");
            System.out.println();

        }

        
    }

    public void unicidad(){
        for (int i = 0; i < tablaSimbolos.size(); i++) {
            for(int j = i+1; j < tablaSimbolos.size(); j++){
                if(tablaSimbolos.get(j).getNombre().equals(tablaSimbolos.get(i).getNombre()) && (tablaSimbolos.get(j).getAlcance().equals(tablaSimbolos.get(i).getAlcance())) ) {//buscar que al menor para de usar scopes mayores
                    
                    System.out.println("Hay dos variables que colisionan con nombre '" + tablaSimbolos.get(j).getNombre() + "' en la l\u00ednea "+ (tablaSimbolos.get(i).getObjeto().getTokens().get(0).getLeft()+1));
                
                     
                }
            }
        }
    }

    // public void chequeoReturn(Objeto cabeza){
    //     for (Objeto var : cabeza.getHijos()) {
    //         if(var.getType().getNombre().equalsIgnoreCase("MethodDec")) {
    //             if(!(var.getTokens().get(0).getValue().equals("void"))) {
    //                 boolean contieneRetorno = false;
    //                 for (Objeto item : var.getHijos()) {
    //                     if(buscarReturn(item)){
    //                         contieneRetorno = true;
    //                         break;
    //                     }
    //                 }
    //                 if(!contieneRetorno){
    //                     System.out.println();
    //                 }
    //             }
                
    
    //         }
    //     }
        

    // }

    // public boolean buscarReturn(Objeto metodo){
    //     if(metodo.getType().getNombre().contains("Statement")){
    //         if(metodo.getTokens().get(0).getValue().equals("return")) {
    //             return true;
    //         } else {
    //             return false;
    //         }
    //     } else {
    //         for (Objeto item : metodo.getHijos()) {
    //             if(buscarReturn(item)){
    //                 return true;
    //             } else {
    //                 return buscarReturn(item);
    //             }
    //         }
    //     }

    // }


}