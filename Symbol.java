package edu.arscompile.modelos;

public class Symbol {
    int type;
    String nombre;

    public Symbol(){}

    public Symbol(int type, String nombre) {
        this.type = type;
        this.nombre = nombre;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getType(){
        return type;
    }

    public String getNombre(){
        return nombre;
    }

    public void imprimirSimboloBonito(){
        System.out.print("<Type: "+ type + ", Nombre: "+ nombre+">");
    }


}