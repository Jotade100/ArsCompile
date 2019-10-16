package edu.arscompile.modelos;

import java.io.Serializable;

import edu.arscompile.modelos.Token;
import edu.arscompile.modelos.Tipo;
import edu.arscompile.modelos.Objeto;

public class Simbolo implements Serializable {
    Tipo tipo;
    String type;
    String nombre;
    Object value;
    int scope;
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
            value = true;
        }
        this.scope = scope;
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

    public Objeto getObjeto(){
        return objeto;
    }

    public void imprimirSimboloBonito(){
        System.out.print("<Type: "+ type + ", Nombre: "+ nombre + ", Scope: "+ scope+ ", Valor: "+ value+">");
    }


}