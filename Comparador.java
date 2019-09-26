package edu.arscompile.scanner;

// paquetes precompilados (https://www.brics.dk/automaton/)
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

    List<Symbol> tokens = new ArrayList<>();

    List<Token> invalidos = new ArrayList<>();

    public void inicializarLista() {
        tokens.add(new Symbol(0, "Token no v\u00e1lido", ""));
        tokens.add(new Symbol(1, "class", "class"));
        tokens.add(new Symbol(2, "Program", "Program"));
        tokens.add(new Symbol(3, "{", "{"));
        tokens.add(new Symbol(4, "}", "}"));
        tokens.add(new Symbol(5, ",", ","));
        tokens.add(new Symbol(6, "[", "["));
        tokens.add(new Symbol(7, "]", "]"));
        tokens.add(new Symbol(8, ";", ";"));
        tokens.add(new Symbol(9, "type", "(boolean)|(int)"));
        tokens.add(new Symbol(10, "void", "void"));
        tokens.add(new Symbol(11, "if", "if"));
        tokens.add(new Symbol(12, "(", "[(]"));
        tokens.add(new Symbol(13, ")", "[)]"));
        tokens.add(new Symbol(14, "else", "else"));
        tokens.add(new Symbol(15, "for", "for"));
        tokens.add(new Symbol(16, "return", "return"));
        tokens.add(new Symbol(17, "break", "break"));
        tokens.add(new Symbol(18, "continue", "continue"));
        tokens.add(new Symbol(19, "asign_op", "(+=)|(-=)"));
        tokens.add(new Symbol(20, "callout", "callout"));
        tokens.add(new Symbol(21, "arit_op", "(+)|(*)|(/)|(%)"));
        tokens.add(new Symbol(22, "rel_op", "(<)|(>)|(>=)|(<=)"));
        tokens.add(new Symbol(23, "eq_op", "(==)|(!=)"));
        tokens.add(new Symbol(24, "cond_op", "[\\|]{2}|[\\&]{2}"));
        tokens.add(new Symbol(25, "bool_literal", "(true)|(false)"));
        tokens.add(new Symbol(26, "char_literal", "'.'"));
        tokens.add(new Symbol(27, "string_literal", ""));
        tokens.add(new Symbol(28, "int_literal", ""));
        tokens.add(new Symbol(29, "id", ""));
        tokens.add(new Symbol(30, "minus_op", "-"));
        tokens.add(new Symbol(31, "exclamation_op", "!"));
        tokens.add(new Symbol(32, "equal_op", "="));
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
                token.setType(tokens.get(1));
                break;
            case "Program":
                token.setType(tokens.get(2));
                break;
            case "{":
                token.setType(tokens.get(3));
                break;
            case "}":
                token.setType(tokens.get(4));
                break;
            case ",":
                token.setType(tokens.get(5));
                break;
            case "[":
                token.setType(tokens.get(6));
                break;
            case "]":
                token.setType(tokens.get(7));
                break;
            case ";":
                token.setType(tokens.get(8));
                break;
            case "boolean":
                token.setType(tokens.get(9));
                break;
            case "int":
                token.setType(tokens.get(9));
                break;
            case "void":
                token.setType(tokens.get(10));
                break;
            case "if":
                token.setType(tokens.get(11));
                break;
            case "(":
                token.setType(tokens.get(12));
                break;
            case ")":
                token.setType(tokens.get(13));
                break;
            case "else":
                token.setType(tokens.get(14));
                break;
            case "for":
                token.setType(tokens.get(15));
                break;
            case "return":
                token.setType(tokens.get(16));
                break;
            case "continue":
                token.setType(tokens.get(18));
                break;
            case "break":
                token.setType(tokens.get(17));
                break;
            case "=":
                token.setType(tokens.get(32));
                break;
            case "+=":
                token.setType(tokens.get(19));
                break;
            case "-=":
                token.setType(tokens.get(19));
                break;
            case "callout":
                token.setType(tokens.get(20));
                break;
            case "+":
                token.setType(tokens.get(21));
                break;
            case "-":
                token.setType(tokens.get(30));
                break;
            case "!":
                token.setType(tokens.get(31));
                break;
            case "*":
                token.setType(tokens.get(21));
                break;
            case "/":
                token.setType(tokens.get(21));
                break;
            case "%":
                token.setType(tokens.get(21));
                break;
            case ">":
                token.setType(tokens.get(22));
                break;
            case "<":
                token.setType(tokens.get(22));
                break;
            case "<=":
                token.setType(tokens.get(22));
                break;
            case "=<":
                token.setType(tokens.get(22));
                break;
            case "==":
                token.setType(tokens.get(23));
                break;
            case "!=":
                token.setType(tokens.get(23));
                break;
            case "&&":
                token.setType(tokens.get(24));
                break;
            case "||":
                token.setType(tokens.get(24));
                break;
            case "true":
                token.setType(tokens.get(25));
                break;
            case "false":
                token.setType(tokens.get(25));
                break;
            default:

                if((token.getValue().toString().length() > 1) && token.getValue().toString().substring(0, 1).equals("\"") && token.getValue().toString().substring(token.getValue().toString().length()-1).equals("\"")){
                    token.setType(tokens.get(27));
                } else if((token.getValue().toString().length() > 1) && token.getValue().toString().substring(0, 1).equals("'") && token.getValue().toString().substring(token.getValue().toString().length()-1).equals("'")){
                    token.setType(tokens.get(26));
                } else if((token.getValue().toString().length() > 1) && token.getValue().toString().substring(0, 2).equals("0x")) {
                    try {
                        token.setValue(Integer.parseInt(token.getValue().toString().substring(2), 16));
                        token.setType(tokens.get(28));
                    } catch(NumberFormatException e) {
                    }
                } else if(isNumeric(token.getValue().toString())){
                    token.setType(tokens.get(28));
                } else if(Character.isLetter(token.getValue().toString().charAt(0))  && isAlphaNumeric(token.getValue().toString())) {
                    token.setType(tokens.get(29));
                } 
                if(token.getType().getType() <1){
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