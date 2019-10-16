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
        scope++;
        for (Objeto var : cabeza.getHijos()) {
            tablaSimbolos(var, scope);
        }
    }

    public void crearTablaSimbolos(boolean debug) {
        int scope = 0;
        tablaSimbolos(this.raiz, scope);
        if(debug){
            for (Simbolo var : tablaSimbolos) {
                System.out.print("\t\t\t\t");
                var.imprimirSimboloBonito();
                System.out.println();
                
            }
            System.out.println();

        }

        
    }

    public void unicidad(){
        for (int i = 0; i < tablaSimbolos.size(); i++) {
            for(int j = 0; j < tablaSimbolos.size(); j++){
                if((i != j) && (tablaSimbolos.get(j).getScope() >= tablaSimbolos.get(i).getScope()) ) {
                    if((tablaSimbolos.get(j).getNombre().equals(tablaSimbolos.get(i).getNombre()))&& (tablaSimbolos.get(j).getScope() == tablaSimbolos.get(j).getScope())) {
                        System.out.println("Hay dos variables que colisionan con nombre '" + tablaSimbolos.get(j).getNombre() + "' en la l\u00ednea "+ tablaSimbolos.get(i).getObjeto().getTokens().get(0).getLeft()+1);
                    }
                }
            }
        }
    }


}