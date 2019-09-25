package edu.arscompile.parser;

import java.util.ArrayList;
import java.util.List;

import edu.arscompile.modelos.Token;
import edu.arscompile.modelos.CeldaParser;
import jdk.nashorn.internal.ir.BreakableNode;

public class Parser {

    public Parser() {}

    private static Parser instancia = new Parser();

    public static Parser getInstancia() {
        if(instancia == null) {
            instancia = new Parser();
        }
        return instancia;
    }

    List<Token> tokens = new ArrayList<>();

    int contador = 0;

    //List<Token> stack = new ArrayList<>();

    //List<List<CeldaParser>> tabla = new ArrayList<>();

    //CeldaParser[][] tablita = new CeldaParser[10][10];

    // new CeldaParser("reduce", 2)


    // public void push(Token element) {
        // stack.add(element);
    // }
// 
    // public void pop(int numero) {
        // while(numero!=0){
            // stack.remove((stack.size()- 1));
            // numero--;
        // }
        // 
    // }
// 
    // public void shift(Token element){
        // push(element);
    // }
// 
    // public void reduce(int numero){
        // pop(numero);
    // }
// 
    // public void accept(){
        // pop(1);
        // if(stack.isEmpty()){
        //    System.out.println("Terminado exitosamente"); 
        // } else {
            // System.out.println("Completado con errores"); 
        // }
    // }
// 
    public void asignarTokens(List<Token> tokens) {
        getInstancia().tokens = tokens;
    }

    public void analizarTokens() {
        if((tokens.get(0).getType().getType() != 1) || (tokens.get(1).getType().getType() != 2)) {
            System.out.println("Error en la nomenclatura class Program, a partir de la l\u00ednea " + tokens.get(0).getLeft() );
            return;
        }
        for(int i = 0; i<tokens.size(); i++) {
            push(tokens.get(i));
        }
    }

    public void error(Token elemento) {
        System.out.println("Error en la l\u00ednea " + elemento.getLeft() + " y caracter " + elemento.getRight() + ", palabra " + elemento.getType() +"no hace sentido");
    }

    public void classToken(){

    }


    


}