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
    String clase = "NULL";
    String scope = "";

    public Objeto(){}

    public Objeto(Tipo type, String nombre, Object value){
        this.type = type;
        this.nombre = nombre;
        this.value = value;
    }

    public Objeto(Tipo type, String nombre, Object value, String clase){
        this.type = type;
        this.nombre = nombre;
        this.value = value;
        this.clase = clase;
    }

    public Objeto(Tipo type, String nombre){
        this.type = type;
        this.nombre = nombre;
    }

    public Objeto(Tipo type, String nombre, String clase){
        this.type = type;
        this.nombre = nombre;
        this.clase = clase;
    }

    public Objeto(Tipo type){
        this.type = type;
    }


    public void setType(Tipo type) {
        this.type = type;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public void setScope(String scope) {
        this.scope = scope;
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

    public String getClase(){
        return clase;
    }

    public String getScope(){
        return scope;
    }



    public void imprimirTokenBonitoCorto(){
        String cadena = "<Type: "+ type.getNombre();
        if (hijos.size() > 0) {
            cadena += ", No. Hijos: "+ hijos.size();
        }
        if (tokensAsociados.size() > 0) {
            cadena += ", No. Tokens: "+ tokensAsociados.size();
        }
        if(!clase.equalsIgnoreCase("NULL")){
            cadena += ", Class: "+ clase;
        }
        if(scope.length() != 0){
            cadena += ", Alcance: "+ scope;
        }
        cadena += ">";
        System.out.print(cadena);
        
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