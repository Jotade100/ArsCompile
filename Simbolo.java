package edu.arscompile.modelos;

import java.io.Serializable;

import edu.arscompile.modelos.Token;
import edu.arscompile.modelos.Tipo;
import edu.arscompile.modelos.Objeto;

public class Simbolo implements Serializable {
    Tipo tipo;
    String type;
    String nombre;
    Object value = "null";
    int scope;
    String alcance;
    Objeto objeto;


    public Simbolo(){}

    public Simbolo(Tipo tipo, String type, String nombre, Object value, int scope, Objeto objeto) {
        this.tipo = tipo;
        this.type = type;
        this.nombre = nombre;
        this.value = value;
        this.scope = scope;
        this.objeto = objeto;

    }


    public Simbolo(Tipo tipo, String type, String nombre, int scope, Objeto objeto) {
        this.tipo = tipo;
        this.type = type;
        this.nombre = nombre;
        if(type.equals("int")){
            value = 0;
        } else if(type.equals("boolean")){
            value = false; // es false segun la documentación de decaf
        }
        this.scope = scope;
        this.objeto = objeto;

    }

    public Simbolo(Tipo tipo, String type, String nombre, int scope, String alcance, Objeto objeto) {
        this.tipo = tipo;
        this.type = type;
        this.nombre = nombre;
        if(type.equals("int")){
            value = 0;
        } else if(type.equals("boolean")){
            value = false;
        }
        this.scope = scope;
        this.alcance = alcance;
        this.objeto = objeto;

    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType(){
        return type;
    }

    public String getNombre(){
        return nombre;
    }

    public Tipo getTipo(){
        return tipo;
    }

    public Object getValue(){
        return value;
    }

    public int getScope(){
        return scope;
    }

    public String getAlcance(){
        return alcance;
    }

    public Objeto getObjeto(){
        return objeto;
    }

    public void imprimirSimboloBonito(){
        System.out.print("<Type: "+ type + ", Nombre: "+ nombre + ", Scope: "+ scope+ ", Valor: "+ value+">");
    }

    public void imprimirSimbolo(){
        System.out.print("\u2551 "+ type + longitud2(type)+"\u2551 "+ tipo.getNombre() + longitud3(tipo.getNombre())+"\u2551 "+ nombre + longitud3(nombre) +"\u2551 "+ alcance+  longitud3(alcance) +"\u2551 "+ value + longitud3(value.toString())+"\u2551");
    }

    public String longitud2(String cadena) {
        if(cadena.length() > 6) {
            return "\t";
        } else {
            return "\t\t";
        }
    }
    
    public String longitud3(String cadena) {
        if (cadena.length() >= 24) {
            return "\t";
        }else if(cadena.length() >= 13) {
            return "\t\t";
        } else if(cadena.length() >= 6) {
            return "\t\t\t";
        }  else {
            return "\t\t\t\t";
        }
    }


}