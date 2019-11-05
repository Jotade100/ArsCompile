package edu.arscompile.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.arscompile.modelos.Token;
import edu.arscompile.modelos.Tipo;
import edu.arscompile.modelos.Objeto;

public class IrtItem implements Serializable {

    String tipo;
    String valor;
    List<IrtItem> hijos = new ArrayList<>();
    String scope = "";


    public IrtItem(){}

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setHijo(IrtItem element) {
        this.hijos.add(element);
    }

}