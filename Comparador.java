package edu.arscompile.lexer;

// paquetes precompilados (https://www.brics.dk/automaton/)
import dk.brics.automaton.*;
import java.util.regex.*;

// librerías
import java.util.ArrayList;
import java.util.List;

import edu.arscompile.modelos.Symbol;
import edu.arscompile.modelos.Token;


public class Comparador {

    public Comparador() {
        inicializarLista();
    }

    private static Comparador instancia = new Comparador();
    
    public static Comparador getInstancia() {
        if(instancia == null) {
            instancia = new Comparador();
        }
        return instancia;
    }

    Automaton a = new RegExp("\""+"[.]*"+"\"").toAutomaton();

    List<Symbol> tokens = new ArrayList<>();

    List<Token> invalidos = new ArrayList<>();

    public void inicializarLista() {
        tokens.add(new Symbol(tokens.size(), "Token no v\u00e1lido", ""));
        tokens.add(new Symbol(tokens.size(), "class", "class"));
        tokens.add(new Symbol(tokens.size(), "Program", "Program"));
        tokens.add(new Symbol(tokens.size(), "{", "{"));
        tokens.add(new Symbol(tokens.size(), "}", "}"));
        tokens.add(new Symbol(tokens.size(), ",", ","));
        tokens.add(new Symbol(tokens.size(), "[", "["));
        tokens.add(new Symbol(tokens.size(), "]", "]"));
        tokens.add(new Symbol(tokens.size(), ";", ";"));
        tokens.add(new Symbol(tokens.size(), "type", "(boolean)|(int)"));
        tokens.add(new Symbol(tokens.size(), "void", "void"));
        tokens.add(new Symbol(tokens.size(), "if", "if"));
        tokens.add(new Symbol(tokens.size(), "(", "[(]"));
        tokens.add(new Symbol(tokens.size(), ")", "[)]"));
        tokens.add(new Symbol(tokens.size(), "else", "else"));
        tokens.add(new Symbol(tokens.size(), "for", "for"));
        tokens.add(new Symbol(tokens.size(), "return", "return"));
        tokens.add(new Symbol(tokens.size(), "break", "break"));
        tokens.add(new Symbol(tokens.size(), "continue", "continue"));
        tokens.add(new Symbol(tokens.size(), "asign_op", "(=)|(+=)|(-=)"));
        tokens.add(new Symbol(tokens.size(), "callout", "callout"));
        tokens.add(new Symbol(tokens.size(), "arit_op", "(-)|(+)|(*)|(/)|(%)"));
        tokens.add(new Symbol(tokens.size(), "rel_op", "(<)|(>)|(>=)|(<=)"));
        tokens.add(new Symbol(tokens.size(), "eq_op", "(==)|(!=)"));
        tokens.add(new Symbol(tokens.size(), "cond_op", "[\\|]{2}|[\\&]{2}"));
        tokens.add(new Symbol(tokens.size(), "bool_literal", "(true)|(false)"));
        tokens.add(new Symbol(tokens.size(), "char_literal", "'.'"));
        tokens.add(new Symbol(tokens.size(), "string_literal", ""));
        tokens.add(new Symbol(tokens.size(), "int_literal", ""));
        tokens.add(new Symbol(tokens.size(), "id", ""));
    }


    // Idea tomada de Stack Overflow
    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }

    public static boolean isAlphaNumeric(String str) { 
        for(int i = 0; i < str.length(); i++){
            if(Character.isLetter(str.charAt(i)) || Character.isDigit(str.charAt(i)) || (str.charAt(i) == 95)) {
            } else {
                return false;
            }
            
        }
        return true; 
    }

    public void categorizar(Token token){
        switch(token.getValue().toString()){
            case "class":
                token.setType(1);
                break;
            case "Program":
                token.setType(2);
                break;
            case "{":
                token.setType(3);
                break;
            case "}":
                token.setType(4);
                break;
            case ",":
                token.setType(5);
                break;
            case "[":
                token.setType(6);
                break;
            case "]":
                token.setType(7);
                break;
            case ";":
                token.setType(8);
                break;
            case "boolean":
                token.setType(9);
                break;
            case "int":
                token.setType(9);
                break;
            case "void":
                token.setType(10);
                break;
            case "if":
                token.setType(11);
                break;
            case "(":
                token.setType(12);
                break;
            case ")":
                token.setType(13);
                break;
            case "else":
                token.setType(14);
                break;
            case "for":
                token.setType(15);
                break;
            case "return":
                token.setType(16);
                break;
            case "continue":
                token.setType(17);
                break;
            case "=":
                token.setType(18);
                break;
            case "+=":
                token.setType(18);
                break;
            case "-=":
                token.setType(18);
                break;
            case "callout":
                token.setType(19);
                break;
            case "+":
                token.setType(20);
                break;
            case "-":
                token.setType(20);
                break;
            case "*":
                token.setType(20);
                break;
            case "/":
                token.setType(20);
                break;
            case "%":
                token.setType(20);
                break;
            case ">":
                token.setType(21);
                break;
            case "<":
                token.setType(21);
                break;
            case "<=":
                token.setType(21);
                break;
            case "=<":
                token.setType(21);
                break;
            case "==":
                token.setType(22);
                break;
            case "!=":
                token.setType(22);
                break;
            case "&&":
                token.setType(23);
                break;
            case "||":
                token.setType(24);
                break;
            case "true":
                token.setType(25);
                break;
            case "false":
                token.setType(25);
                break;
            default:

                if((token.getValue().toString().length() > 1) && token.getValue().toString().substring(0, 1).equals("\"") && token.getValue().toString().substring(token.getValue().toString().length()-1).equals("\"")){
                    token.setType(27);
                } else if((token.getValue().toString().length() > 1) && token.getValue().toString().substring(0, 1).equals("'") && token.getValue().toString().substring(token.getValue().toString().length()-1).equals("'")){
                    token.setType(26);
                } else if((token.getValue().toString().length() > 1) && token.getValue().toString().substring(0, 2).equals("0x")) {
                    try {
                        token.setValue(Integer.parseInt(token.getValue().toString().substring(2), 16));
                        token.setType(26);
                    } catch(NumberFormatException e) {
                    }
                } else if(isNumeric(token.getValue().toString())){
                    token.setType(28);
                } else if(Character.isLetter(token.getValue().toString().charAt(0))  && isAlphaNumeric(token.getValue().toString())) {
                    token.setType(29);
                } 
                if(token.getType() <1){
                    invalidos.add(token);
                }
                
                
                break;
        }
    }

    

    public void imprimirErrores() {
        System.out.println();
        invalidos.forEach((action) -> System.out.println("Error en la l\u00ednea: "+action.getLeft() +" y caracter: " +action.getRight() +".\n El elemento " +action.getValue().toString() + " es inv\u00e1lido. "));
    }



}