package edu.arscompile.modelos;

import edu.arscompile.modelos.Symbol; // agregado para evitar confusión
import edu.arscompile.modelos.Token; // agregado para evitar confusión

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.lang.Cloneable;

public class Objeto implements Serializable, Cloneable {

    Tipo type = new Tipo("NULL");
    String nombre;
    List<Token> tokensAsociados = new ArrayList<>();
    List<Objeto> hijos = new ArrayList<>();
    Object value;

    public Objeto(){}

    public Objeto(Tipo type, String nombre, Object value){
        this.type = type;
        this.nombre = nombre;
        this.value = value;
    }

    public Objeto(Tipo type, String nombre){
        this.type = type;
        this.nombre = nombre;
    }

    public Objeto(Tipo type){
        this.type = type;
    }


    public void setType(Tipo type) {
        this.type = type;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setToken(Token element) {
        this.tokensAsociados.add(element);
    }

    public void setObjeto(Objeto element) {
        this.hijos.add(element);
    }

    public void setTokens(List<Token> element) {
        this.tokensAsociados =element;
    }

    public void setObjetos(List<Objeto> element) {
        this.hijos =element;
    }

    public Tipo getType(){
        return type;
    }

    public Object getValue(){
        return value;
    }

    public String getNombre(){
        return nombre;
    }

    public List<Objeto> getHijos(){
        return hijos;
    }

    public List<Token> getTokens(){
        return tokensAsociados;
    }



    public void imprimirTokenBonitoCorto(){
        System.out.print("<Type: "+ type.getNombre() + ", No. Hijos: "+ hijos.size()+ ", No. Tokens: "+ tokensAsociados.size()+">");
    }

    public void imprimirToken(){
        System.out.print("<Type: "+ type + ", Value: "+ value+", Hijos: "+ hijos+", Tokens Asociados: "+ tokensAsociados+ ">");
    }

    public void imprimirTokenBonitoLargo(){
        System.out.print("<Type: "+ type.getNombre() + 
        ", Value: "+ value+ 
        ", Hijos: "+ hijos+
        ", Tokens Asociados: "+ tokensAsociados+ ">");
    }

    public String retornarTokenBonitoCorto(){
        return "<Type: "+ type.getNombre() + ", Value: "+ value+">";
    }

    public String retornarTokenBonitoLargo(){
        return "<Type: "+ type.getNombre() + ", Value: "+ value+ ", Hijos: "+ hijos+", Tokens Asociados: "+ tokensAsociados+ ">";
    }

}