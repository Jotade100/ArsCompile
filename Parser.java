package edu.arscompile.parser;

import java.util.ArrayList;
import java.util.List;

import edu.arscompile.modelos.Token;
import edu.arscompile.modelos.CeldaParser;

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

    boolean detener = false;

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
        classToken();
    }

    public void error(Token elemento) {
        System.out.println("Error en la l\u00ednea " + elemento.getLeft() + " y caracter " + elemento.getRight() + ", palabra " + elemento.getValue() + " del tipo " + elemento.getType().getNombre() +" no hace sentido, se esperaba un elemento diferente");
        System.exit(1);
    }

    public void classToken(){
        if(tokens.get(contador).getType().getType()==1){
            contador++;
            programa();
        } else {error(tokens.get(contador));}

    }

    public void programa(){
        if(tokens.get(contador).getType().getType()==2){
            contador++;
            llaveAPrograma();
        } else {error(tokens.get(contador));}
    }

    public void llaveAPrograma(){
        if(tokens.get(contador).getType().getType()==3){
            contador++;
            fieldDecl();
            methodDec();
            llaveCPrograma();
        } else {error(tokens.get(contador));}
    }

    public void fieldDecl(){
        // estructura do-while que equivale a un go-to
        boolean goTo = true;
        do {
            switch(tokens.get(contador).getType().getType()){
                case 9:
                    contador++;
                    boolean goTo2 = true;
                    do {
                        if(tokens.get(contador).getType().getType()==29){
                            contador++;
                        
                            switch(tokens.get(contador).getType().getType()){
                                case 8:
                                    contador++;
                                    //nada vaya a goTo
                                    goTo2 = false; // se ha acabado
                                    break;
                                case 5:
                                    contador++;
                                    //nada vaya a goTo
                                    break;
                                case 6:
                                    contador++;
                                    if(tokens.get(contador).getType().getType()==28){contador++;} else {goTo = false; goTo2 = false; error(tokens.get(contador));}
                                    if(tokens.get(contador).getType().getType()==7){contador++;} else {goTo = false; goTo2 = false; error(tokens.get(contador));}
                                    if(tokens.get(contador).getType().getType()==5){ // coma
                                        goTo2 = true;
                                    } else if(tokens.get(contador).getType().getType()==8) { // punto y coma
                                        goTo2 = false;
                                    } else {
                                        goTo2 = false;
                                        goTo = false;
                                        error(tokens.get(contador));}

                                    break; // nada vaya a goTo
                                case 12:
                                    contador = contador -2;
                                    goTo = false;
                                    goTo2 = false;


                            }
                        } else {goTo = false; goTo2 = false; error(tokens.get(contador));}
                    } while(goTo2);
                    break;
                case 10:
                    goTo = false;
                    break;
                case 4:
                    goTo = false;
                    break;
                default:
                    goTo = false;
                    error(tokens.get(contador));
                    break;
                    
                
                    
            }
        } while(goTo);
        
    }

    public void methodDec(){

    }

    public void llaveCPrograma(){
        if(tokens.get(contador).getType().getType()==3){
            System.out.println("qwertyuiop");
        } else {
            error(tokens.get(contador));
        }


    }

    


}