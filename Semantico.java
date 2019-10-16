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

    public void tablaSimbolos(Objeto cabeza, int scope) {
        if(cabeza.getType().getNombre().equalsIgnoreCase("FieldDec")){
            tablaSimbolos.add(new Simbolo(cabeza.getType(), cabeza.getTokens().get(0).getValue().toString(), cabeza.getTokens().get(1).getValue().toString(), scope, cabeza));
        } else if(cabeza.getType().getNombre().equalsIgnoreCase("MethodDec")) {
            tablaSimbolos.add(new Simbolo(cabeza.getType(), cabeza.getTokens().get(0).getValue().toString(), cabeza.getTokens().get(1).getValue().toString(), scope, cabeza));
        } else if(cabeza.getType().getNombre().equalsIgnoreCase("ParamDec")) {
            tablaSimbolos.add(new Simbolo(cabeza.getType(), cabeza.getTokens().get(0).getValue().toString(), cabeza.getTokens().get(1).getValue().toString(), scope, cabeza));
        } else if(cabeza.getType().getNombre().equalsIgnoreCase("Variable")) {
            tablaSimbolos.add(new Simbolo(cabeza.getType(), cabeza.getTokens().get(0).getValue().toString(), cabeza.getTokens().get(1).getValue().toString(), scope, cabeza));
        }
        if(cabeza.getType().getNombre().contains("MethodCall")) {
            if(!buscarSiExiste(cabeza.getTokens().get(0).getValue().toString())){
                System.out.println("Variable no inicializada con nombre '" + cabeza.getTokens().get(0).getValue().toString() + "' en la l\u00ednea "+ cabeza.getTokens().get(0).getLeft());
            }
        } else if(cabeza.getType().getNombre().contains("Location")) {
            if(!buscarSiExiste(cabeza.getTokens().get(0).getValue().toString())){
                System.out.println("Variable no inicializada con nombre '" + cabeza.getTokens().get(0).getValue().toString() + "' en la l\u00ednea "+ cabeza.getTokens().get(0).getLeft());
            }
        }
        scope++;
        for (Objeto var : cabeza.getHijos()) {
            tablaSimbolos(var, scope);
        }
    }

    public boolean buscarSiExiste(String nombre) {
        boolean resultado = false;
        for (Simbolo var : tablaSimbolos) {
            if(var.getNombre().equals(nombre)){
                resultado = true;
            }
            
            
        }
        return resultado;
    }

    public void crearTablaSimbolos(boolean debug) {
        int scope = 0;
        tablaSimbolos(this.raiz, scope);
        if(debug){
            System.out.println("\t\t\t\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2566\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2566\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2566\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557");
            System.out.println("\t\t\t\u2551\tTipo\t\u2551\tNombre del ID\t\t\u2551 Scope \u2551\t\t Valor \t\t\u2551");
            System.out.println("\t\t\t\u2560\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u256C\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u256C\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u256C\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2563");
            
            for (Simbolo var : tablaSimbolos) {
                System.out.print("\t\t\t");
                var.imprimirSimbolo();
                System.out.println();
                
            }
            System.out.println("\t\t\t\u255A\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2569\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2569\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2569\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255D");
            System.out.println();

        }

        
    }

    public void unicidad(){
        for (int i = 0; i < tablaSimbolos.size(); i++) {
            for(int j = i+1; j < tablaSimbolos.size(); j++){
                if(tablaSimbolos.get(j).getScope() < tablaSimbolos.get(i).getScope()) {
                    break;
                }
                if((tablaSimbolos.get(j).getScope() >= tablaSimbolos.get(i).getScope()) ) {//buscar que al menor para de usar scopes mayores
                    
                    if((tablaSimbolos.get(j).getNombre().equals(tablaSimbolos.get(i).getNombre()))&& (tablaSimbolos.get(j).getScope() == tablaSimbolos.get(j).getScope())) {
                        System.out.println("Hay dos variables que colisionan con nombre '" + tablaSimbolos.get(j).getNombre() + "' en la l\u00ednea "+ tablaSimbolos.get(i).getObjeto().getTokens().get(0).getLeft()+1);
                    }
                     
                }
            }
        }
    }


}