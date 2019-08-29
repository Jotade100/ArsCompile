package edu.arscompile.modelos;

public class Symbol {
    int type;
    String nombre;
    String regEx;

    public Symbol(){}

    public Symbol(int type, String nombre, String regEx) {
        this.type = type;
        this.nombre = nombre;
        this.regEx = regEx;

    }

    public void setType(int type) {
        this.type = type;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRegEx(String regEx) {
        this.regEx = regEx;
    }

    public int getType(){
        return type;
    }

    public String getNombre(){
        return nombre;
    }

    public String getRegEx(){
        return regEx;
    }

    public void imprimirSimboloBonito(){
        System.out.print("<Type: "+ type + ", Nombre: "+ nombre+">");
    }


}