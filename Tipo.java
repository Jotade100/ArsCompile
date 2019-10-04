package edu.arscompile.modelos;

import java.io.Serializable;

public class Tipo implements Serializable {
    String nombre;

    public Tipo(){}

    public Tipo(String nombre) {
        this.nombre = nombre;

    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }


    public void imprimirTipoBonito(){
        System.out.print("{"+ nombre+"}");
    }


}