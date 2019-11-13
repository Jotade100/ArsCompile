package edu.arscompile.modelos;

import edu.arscompile.modelos.Symbol; // agregado para evitar confusión
import edu.arscompile.utilidades.Excentricidades;

import java.io.Serializable;

public class Token implements Serializable {

    Symbol type = new Symbol(0, "Token no v\u00e1lido", "");
    int left;
    int right;
    Object value;

    public Token(){}

    public Token(Symbol type, Object value){
        this.type = type;
        this.value = value;
    }

    public Token(Object value, int left, int right){
        this.value = value;
        this.left = left;
        this.right = right;
    }

    

    public Token(Symbol type, Object value, int left, int right){
        this.type = type;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public void setType(Symbol type) {
        this.type = type;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Symbol getType(){
        return type;
    }

    public Object getValue(){
        return value;
    }

    public int getLeft(){
        return left;
    }

    public int getRight(){
        return right;
    }


    public void imprimirTokenBonitoCorto(){
        System.out.print("<Type: "+ type.getNombre() + ", Value: "+ value+">");
    }

    public void imprimirTokenBonitoLargo(){
        System.out.print("<Type: "+ Excentricidades.ANSI_CYAN + type.getNombre() + Excentricidades.ANSI_RESET + ", Value: "+ Excentricidades.ANSI_YELLOW + value + " "+ Excentricidades.ANSI_RESET + ", L\u00ednea: "+ left+", Posici\u00f3n: "+ right+ ">");
    }

    public String retornarTokenBonitoCorto(){
        return "<Type: "+ type.getNombre() + ", Value: "+ value+">";
    }

    public String retornarTokenBonitoLargo(){
        return "<Type: "+ type.getNombre() + ", Value: "+ value+ ", L\u00ednea: "+ left+", Posici\u00f3n: "+ right+ ">";
    }

}