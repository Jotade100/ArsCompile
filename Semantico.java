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
import edu.arscompile.utilidades.Excentricidades;
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

    public void tablaSimbolos(Objeto cabeza, int scope, String alcance) {
        if(cabeza.getType().getNombre().equalsIgnoreCase("Programa")) {
            cabeza.setScope("0");
        } 
        else {
            //System.out.println(alcance);
            cabeza.setScope(alcance.substring(0, alcance.length()-2));
        }
        
        
        if(cabeza.getType().getNombre().equalsIgnoreCase("FieldDec")){
            tablaSimbolos.add(new Simbolo(cabeza.getType(), cabeza.getTokens().get(0).getValue().toString(), cabeza.getTokens().get(1).getValue().toString(), scope, alcance.substring(0, alcance.length()-2), cabeza));
        } else if(cabeza.getType().getNombre().equalsIgnoreCase("FieldArrayDec")) {
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
                System.out.println("El m\u00E9todo utilizado en la l\u00ednea "+ (cabeza.getTokens().get(0).getLeft()+1)+ ", con nombre '" + cabeza.getTokens().get(0).getValue().toString() + "', no se encuentra inicializado." );
            } else if(cabeza.getClase().equalsIgnoreCase("NULL")) {
                cabeza.setClase(buscarSiExiste2(cabeza.getTokens().get(0).getValue().toString(), alcance.substring(0, alcance.length()-2)).getType());
            }
        } else if(cabeza.getType().getNombre().contains("FieldArrayDec")) {
            if(!buscarSiExiste(cabeza.getTokens().get(1).getValue().toString(), alcance.substring(0, alcance.length()-2))){
                System.out.println("Variable no inicializada con nombre '" + cabeza.getTokens().get(1).getValue().toString() + "' en la l\u00ednea "+ (cabeza.getTokens().get(0).getLeft()+1));
            } else if(cabeza.getClase().equalsIgnoreCase("NULL")) {
                cabeza.setClase(buscarSiExiste2(cabeza.getTokens().get(0).getValue().toString(), alcance.substring(0, alcance.length()-2)).getType());
            }
            if(!cabeza.getTokens().get(3).getType().getNombre().equals("int_literal")) {
                //System.out.println(cabeza.getTokens().get(3).getType().getNombre());
                System.out.println("Ingresar un valor entero en el array con nombre '"+cabeza.getTokens().get(1).getType().getNombre().toString() +"'");
                //System.out.println(cabeza.getTokens().get(3).getValue());
            } else if (Integer.parseInt(cabeza.getTokens().get(3).getValue().toString())<=0){
                System.out.println("Ingresar un valor mayor a cero en la declaraci\u00f3n del arreglo '"+cabeza.getTokens().get(1).getValue().toString() +"' en la l\u00ednea "+ (cabeza.getTokens().get(0).getLeft()+1)+".");
            }
            if (Integer.parseInt(cabeza.getTokens().get(3).getValue().toString())<=0){
                System.out.println("Ingresar un valor mayor a cero en la declaraci\u00f3n del arreglo '"+cabeza.getTokens().get(1).getValue().toString() +"' en la l\u00ednea "+ (cabeza.getTokens().get(0).getLeft()+1)+".");
            } 
        } else if(cabeza.getType().getNombre().contains("Location")) {
            //System.out.println(cabeza.getType().getNombre()+ " - "+ cabeza.getTokens().toString());
            if(!buscarSiExiste(cabeza.getTokens().get(0).getValue().toString(), alcance.substring(0, alcance.length()-2))){
                System.out.println("Variable no inicializada con nombre '" + cabeza.getTokens().get(0).getValue().toString() + "' en la l\u00ednea "+ (cabeza.getTokens().get(0).getLeft()+1));

            } else if(cabeza.getClase().equalsIgnoreCase("NULL")) {
                cabeza.setClase(buscarSiExiste2(cabeza.getTokens().get(0).getValue().toString(), alcance.substring(0, alcance.length()-2)).getType());
            }
            if (cabeza.getType().getNombre().contains("Array")){
                if(!scopeGeneral(cabeza.getTokens().get(0).getValue().toString(),alcance.substring(0,alcance.length()-2))){
                    System.out.println("La variable de tipo array con nombre '"+cabeza.getTokens().get(0).getValue().toString()+"' utilizada en la l\u00ednea "+ (cabeza.getTokens().get(0).getLeft()+1) +", debe estar definida en el scope global (a nivel de clase).");
                } 
                //System.out.println(cabeza.getType().getNombre());
            }
        } else if(cabeza.getType().getNombre().contains("Variable")) {
            if(!buscarSiExiste(cabeza.getTokens().get(1).getValue().toString(), alcance.substring(0, alcance.length()-2))){
                System.out.println("Variable no inicializada con nombre '" + cabeza.getTokens().get(1).getValue().toString() + "' en la l\u00ednea "+ (cabeza.getTokens().get(0).getLeft()+1));
            } else if(cabeza.getClase().equalsIgnoreCase("NULL")) {
                cabeza.setClase(buscarSiExiste2(cabeza.getTokens().get(0).getValue().toString(), alcance.substring(0, alcance.length()-2)).getType());
            }
        }
        scope++;
        for (int i = 0; i < cabeza.getHijos().size(); i++) {
            tablaSimbolos(cabeza.getHijos().get(i), scope, alcance+"."+i);
            
        }
    }


    public void asignarClaseALocation(Objeto cabeza) {
        for (Objeto var : cabeza.getHijos()) {
            
        }


    }

    public boolean buscarSiExiste(String nombre, String alcance) {
        boolean resultado = false;
        //System.out.println(nombre+ alcance);
        for (Simbolo var : tablaSimbolos) {
            if(var.getNombre().equals(nombre)){
                
                for (int i = alcance.length(); i >= 1; i=i-2) {
                    if(var.getAlcance().equals(alcance.substring(0, i))){
                        //System.out.println(var.getAlcance()+"-"+(alcance.substring(0, i)));
                        //System.out.println(var.getAlcance().equals("1"));
                        resultado = true;
                        return resultado;
                    }
                }
                
            }
            
            
        }
        return resultado;
    }

    public Simbolo buscarSiExiste2(String nombre, String alcance) {
        Simbolo resultado = new Simbolo();
        //System.out.println(nombre+ alcance);
        for (Simbolo var : tablaSimbolos) {
            if(var.getNombre().equals(nombre)){
                
                for (int i = alcance.length(); i >= 1; i=i-2) {
                    if(var.getAlcance().equals(alcance.substring(0, i))){
                        //System.out.println(var.getAlcance()+"-"+(alcance.substring(0, i)));
                        //System.out.println(var.getAlcance().equals("1"));
                        resultado = var;
                        return resultado;
                    }
                }
                
            }
            
            
        }
        return resultado;
    }

    public boolean scopeGeneral(String nombre, String alcance) {
        boolean resultado = false;
        //System.out.println(nombre+ alcance);
        for (Simbolo var : tablaSimbolos) {
            if(var.getNombre().equals(nombre)){
                
                for (int i = alcance.length(); i >= 1; i=i-2) {
                    if(var.getAlcance().equals("1")){
                        //System.out.println(var.getAlcance()+"-"+(alcance.substring(0, i)));
                        //System.out.println(var.getAlcance().equals("1"));
                        resultado = true;
                        return resultado;
                    }
                }
                
            }
            
            
        }
        return resultado;
    }

    

    public void unicidad(){
        for (int i = 0; i < tablaSimbolos.size(); i++) {
            for(int j = i+1; j < tablaSimbolos.size(); j++){
                if(tablaSimbolos.get(j).getNombre().equals(tablaSimbolos.get(i).getNombre()) && (tablaSimbolos.get(j).getAlcance().equals(tablaSimbolos.get(i).getAlcance())) ) {//buscar que al menor para de usar scopes mayores
                    
                    System.out.println("Hay dos variables que colisionan con nombre '" + tablaSimbolos.get(j).getNombre() + "' en las l\u00edneas "+ (tablaSimbolos.get(i).getObjeto().getTokens().get(0).getLeft()+1) + " y "+(tablaSimbolos.get(j).getObjeto().getTokens().get(0).getLeft()+1) );
                
                     
                }
            }
        }
    }

    public boolean metodoMain(){
        for (Simbolo var : tablaSimbolos) {
            if(var.getNombre().equals("main") && var.getTipo().getNombre().contains("MethodDec") && var.getType().equalsIgnoreCase("void")){
                
                for(Objeto i: var.getObjeto().getHijos()){
                    if(i.getType().getNombre().contains("ParamDec")) {
                        return false;
                    }
                }
                return true;

            }
            
        }
        return false;
    }

    public void chequeoMetodoMain(){
        if(metodoMain()) {

        } else{
            System.out.println("Hay problemas con el m\u00e9todo 'main'. Revise su c\u00f3digo usando de referencia los siguientes consejos. \n\t1) Vea que el m\u00e9todo 'main' est\u00e9 presente. \n\t2) Revise que sea tipo 'void'. \n\t3) Verifique que no tenga par\u00e1metros de entrada.");
        }
    }


    public int numeroDeParametros(Objeto objeto){
        int retorno = 0;
        for (Objeto var : objeto.getHijos()) {
            if(var.getType().getNombre().contains("ParamDec")) {
                retorno++;
            }
            
        }
        return retorno;

    }

    public Objeto buscarSimboloPorNombre(String nombre){
        for (Simbolo var : tablaSimbolos) {
            if(var.getNombre().equals(nombre) && var.getTipo().getNombre().equals("MethodDec")){
                return var.getObjeto();
            }
        }
        return (new Simbolo()).getObjeto();

    }


    public Simbolo buscarSimboloPorNombre2(String nombre){
        for (Simbolo var : tablaSimbolos) {
            if(var.getNombre().equals(nombre) && var.getTipo().getNombre().equals("MethodDec")){
                return var;
            }
        }
        return (new Simbolo());

    }

    public void chequeoNumeroArgumentosMetodo(Objeto objeto){
        if(objeto.getType().getNombre().contains("MethodCall")){
            try {
                if(objeto.getHijos().size() == numeroDeParametros(buscarSimboloPorNombre(objeto.getTokens().get(0).getValue().toString()))){
                    Simbolo comparador = buscarSimboloPorNombre2(objeto.getTokens().get(0).getValue().toString());
                    objeto.setClase(comparador.getType());
                    for (int i = 0; i < objeto.getHijos().size(); i++) {
                        //lo veremos luego
                        //objeto.getHijos().get(i) == comparador.getObjeto().getHijos().get(i).getTokens().get(0).getValue().toString()
                        if(objeto.getHijos().get(i).getClase().contains(comparador.getObjeto().getHijos().get(i).getClase())) {
                            //no pasa nada
                            //ya pasa algo XD chequea que sean parámetros verdaderos
                            
                        } else {
                            System.out.println("El par\u00e1metro de '" + objeto.getTokens().get(0).getValue().toString() + "' no coincide con el tipo en la declaraci\u00f3n inicial del m\u00e9todo.");
                        }
                    }
                } else {
                    System.out.println("El n\u00famero de par\u00e1metros no coincide para el m\u00e9todo '"+objeto.getTokens().get(0).getValue()+ "' en la l\u00ednea " + (objeto.getTokens().get(0).getLeft()+1));
                }

            } catch(Exception e) {
                System.out.println("El m\u00e9todo '" + objeto.getTokens().get(0).getValue() + "' usado en la l\u00ednea "+ (objeto.getTokens().get(0).getLeft()+1) + " no existe. Si no existe \u00BFpara qu\u00e9 se molest\u00f3 en ponerle par\u00e1metros?");
            }
            
        }
        for (Objeto var : objeto.getHijos()) {
            chequeoNumeroArgumentosMetodo(var);
            
        }
    }

    public void chequeoReturn(Objeto cabeza){
        for (Objeto var : cabeza.getHijos()) {
            if(var.getType().getNombre().equalsIgnoreCase("MethodDec")) {
                
                boolean contieneRetorno = false;
                for (Objeto item : var.getHijos()) {
                    //System.out.println(item.getType().getNombre());
                    if(buscarReturn(item, var.getClase())){
                        contieneRetorno = true;
                        break;
                    }
                }
                if(!contieneRetorno){
                    System.out.println("El m\u00e9todo '" + var.getTokens().get(1).getValue().toString() + "' carece de  enunciado 'return' o la expresi\u00f3n tras el enunciado no es del mismo tipo al definido en el m\u00e9todo.");
                }
                    
            }
        }
        

    }

    public void asignarTipoReturnStatement(Objeto cabeza) {
        for (Objeto var : cabeza.getHijos()) {
            asignarTipoReturnStatement(var);
        }
        if(cabeza.getType().getNombre().contains("Return")){
            if(cabeza.getHijos().size() == 0) {
                cabeza.setClase("void");
            } else {
                cabeza.setClase(cabeza.getHijos().get(0).getClase());
            }
        }
    }

    public boolean buscarReturn(Objeto metodo, String clase){
        boolean variableRetorno = false;
        
        for (Objeto item : metodo.getHijos()) {
            //System.out.println(item.getType().getNombre());
            if(item.getType().getNombre().contains("Return")){
                if (item.getClase().equals(clase)) {
                    variableRetorno =  true;
                    break;
                }
                
            } else {
                variableRetorno = buscarReturn(item, clase);
                if(variableRetorno){
                    break;
                }
            }
            
        }

        return variableRetorno;
    }

    public void dandoClaseAExpresiones(Objeto cabeza){
        for (Objeto var : cabeza.getHijos()) {
            dandoClaseAExpresiones(var);    
        }
        if(cabeza.getType().getNombre().equalsIgnoreCase("Expresion")) {
            if(cabeza.getClase().equalsIgnoreCase("NULL")){
                if(cabeza.getHijos().size()==1) { //Expresiones unarias -expr !expr (expr)
                    if(cabeza.getHijos().get(0).getClase().equalsIgnoreCase("NULL")) { // no está definida la clase del hijo
                        System.out.println("Hubo un error determinando la clase de la expresi\u00f3n en la l\u00ednea " + (cabeza.getTokens().get(0).getLeft()+1));
                    } else {
                        cabeza.setClase(cabeza.getHijos().get(0).getClase());
                        if(cabeza.getTokens().size() == 1) {
                            if(cabeza.getTokens().get(0).getType().getType() == 31) {
                                if(!cabeza.getClase().contains("boolean")) {
                                    System.out.println("La expresi\u00f3n de la l\u00ednea " + (cabeza.getTokens().get(0).getLeft()+1) + " no es de tipo 'boolean'.");
                                }
                            } else if(cabeza.getTokens().get(0).getType().getType() == 30) {
                                if(!cabeza.getClase().contains("int")) {
                                    System.out.println("La expresi\u00f3n de la l\u00ednea " + (cabeza.getTokens().get(0).getLeft()+1) + " no es de tipo 'int'.");
                                }
                            }
                        }
                        
                    }
                } else if(cabeza.getHijos().size()==3) { // Expresiones terciarias
                    //chequear que los dos hijos tengan clase
                    if(cabeza.getHijos().get(0).getClase().equalsIgnoreCase("NULL") || cabeza.getHijos().get(2).getClase().equalsIgnoreCase("NULL")) {
                        System.out.println("Hubo un error determinando la clase de la expresi\u00f3n con hijos " + cabeza.getHijos().get(0).getClase() + " y " + cabeza.getHijos().get(2).getClase());

                    }
                    //chequear que ambas clases sean iguales
                    if(cabeza.getHijos().get(0).getClase().equalsIgnoreCase(cabeza.getHijos().get(2).getClase())) {
                        
                    } else {
                        System.out.println("Los dos valores de la expresi\u00f3n, en la l\u00ednea "+(cabeza.getHijos().get(0).getTokens().get(0).getLeft()+1)+", no son iguales: ("  + cabeza.getHijos().get(0).getClase() + " y " + cabeza.getHijos().get(2).getClase()+")");
                    }
                    //chequear que cumplan con el tipo del operador
                    if(cabeza.getHijos().get(0).getClase().contains("int")){
                        if(cabeza.getHijos().get(1).getType().getNombre().contains("Arith") || cabeza.getHijos().get(1).getType().getNombre().contains("Rel") || cabeza.getHijos().get(1).getType().getNombre().contains("Eq")) {
                            //cabeza.setClase(cabeza.getHijos().get(0).getClase());
                            if(cabeza.getHijos().get(1).getType().getNombre().contains("Rel") || cabeza.getHijos().get(1).getType().getNombre().contains("Eq")) {
                                cabeza.setClase("boolean");
                            } else {
                                cabeza.setClase(cabeza.getHijos().get(0).getClase());
                            }
                        } else {
                            //System.out.println(cabeza.getHijos().get(2).getType().getNombre() +cabeza.getHijos().get(2).getType().getNombre().contains("Arith") +""+ cabeza.getHijos().get(2).getType().getNombre().contains("Rel") +"" + cabeza.getHijos().get(2).getType().getNombre().contains("Eq"));
                            
                            System.out.println("Operador no v\u00e1lido en la l\u00ednea "+ (cabeza.getHijos().get(1).getTokens().get(0).getLeft()+1));
                        }
                    } else if(cabeza.getHijos().get(0).getClase().contains("bool")){
                        if(cabeza.getHijos().get(1).getType().getNombre().contains("Con") ||  cabeza.getHijos().get(1).getType().getNombre().contains("Eq")) {
                            cabeza.setClase(cabeza.getHijos().get(0).getClase());
                        } else {
                            System.out.println("Operador no v\u00e1lido en la l\u00ednea "+ (cabeza.getHijos().get(2).getTokens().get(0).getLeft()+1));
                        }
                    }

                }

            }
        }
    }

    public void chequeandoExpresionesIf(Objeto cabeza){
        for (Objeto var : cabeza.getHijos()) {
            chequeandoExpresionesIf(var);    
        }
        if((cabeza.getType().getNombre().equalsIgnoreCase("IfStatement"))){
            if(cabeza.getHijos().get(0).getClase().contains("bool")) {
                //todo bien
            } else {
                System.out.println("La expresi\u00f3n IF de la l\u00ednea "  + (cabeza.getTokens().get(0).getLeft()+1) + " no es binaria o booleana");
            }
        }
    }

    public void chequeandoExpresionesFor(Objeto cabeza) {
        for (Objeto var : cabeza.getHijos()) {
            chequeandoExpresionesFor(var);    
        }
        if((cabeza.getType().getNombre().equalsIgnoreCase("ForStatement"))){
            if(cabeza.getHijos().get(0).getClase().contains("int")) {
                //todo bien
            } else {
                System.out.println("La expresi\u00f3n '" + cabeza.getHijos().get(0).getTokens().get(0).getValue().toString()+"' del For de la l\u00ednea "  + (cabeza.getTokens().get(0).getLeft()+1) + " no es v\u00e1lida.");
            }
            if(cabeza.getHijos().get(1).getClase().contains("int")) {
                //todo bien
            } else {
                System.out.println("La asignaci\u00f3n de la expresi\u00f3n For de la l\u00ednea "  + (cabeza.getTokens().get(0).getLeft()+1) + " no es entera. ");
            }
            if(cabeza.getHijos().get(2).getClase().contains("bool")) {
                //todo bien
            } else {
                System.out.println("La expresi\u00f3n evaluadora For de la l\u00ednea "  + (cabeza.getTokens().get(0).getLeft()+1) + " no es entera.");
            }
        }
    }

    public void chequeandoLocalizacionesArrayYStatement(Objeto cabeza) {
        for (Objeto var : cabeza.getHijos()) {
            chequeandoLocalizacionesArrayYStatement(var);    
        }
        if((cabeza.getType().getNombre().equalsIgnoreCase("LocationStatement"))){
            if(cabeza.getClase().contains(cabeza.getHijos().get(0).getClase())) {
                //Sin problemas
            } else {
                System.out.println("El tipo de la expresi\u00f3n 'referenciadora' de la l\u00ednea "  + (cabeza.getTokens().get(0).getLeft()+1) + " no coincide con su asignaci\u00f3n consiguiente.");
            }
        } else if((cabeza.getType().getNombre().equalsIgnoreCase("ArrayLocationStatement"))){
            if(cabeza.getClase().equalsIgnoreCase(cabeza.getHijos().get(1).getClase())) {
                //Sin problemas
            } else {
                System.out.println("El tipo de la expresi\u00f3n 'referenciadora' de la l\u00ednea "  + (cabeza.getTokens().get(0).getLeft()+1) + " no coincide con su asignaci\u00f3n consiguiente. Se esperaba un '" + cabeza.getClase()+ "'.");
            }
            if(cabeza.getHijos().get(0).getClase().contains("int")) {
                //Sin problemas
            } else {
                System.out.println("El arreglo de la expresi\u00f3n 'referenciadora' de la l\u00ednea "  + (cabeza.getTokens().get(0).getLeft()+1) + " no posee un \u00edndice de tipo 'int'.");
            }
        }


    }

    public void comprobacionBreakContinueEnFor(Objeto cabeza) {
        if(cabeza.getType().getNombre().contains("Break") || cabeza.getType().getNombre().contains("Continue")){
            boolean siRespeta = false;
            for (int i = cabeza.getScope().length()-2; i >= 1; i=i-2) {
                // Importante: alcance.substring(0, i) es todos los scopes válidos
                siRespeta = buscarForSegunScope(this.raiz, cabeza.getScope().substring(0, i));    
                if(siRespeta) {
                    break;
                }    
                
            }
            if(!siRespeta) {
                System.out.println("El enunciado '" + cabeza.getType().getNombre() + "' de la l\u00ednea " + (cabeza.getTokens().get(0).getLeft()+1) + " no est\u00e1 dentro de una sentencia 'For'.");
            }
            
        }
        for (Objeto var : cabeza.getHijos()) {
            
            comprobacionBreakContinueEnFor(var);
            
            
        }

        
    }

    public boolean buscarForSegunScope(Objeto cabeza, String scope) {
        for (Objeto item : cabeza.getHijos()) {
            boolean retorno = buscarForSegunScope(item, scope);
            if(retorno) {
                return true;
            }           
        }
        if(cabeza.getType().getNombre().contains("For") && cabeza.getScope().equalsIgnoreCase(scope))  {
            return true;
        } else {
            return false;
        }
    }


}