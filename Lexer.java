package edu.arscompile.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.arscompile.utilidades.LectorDeArchivo;

public class Lexer {

    public Lexer(){

    }

    private static Lexer instancia = new Lexer();
    
    public static Lexer getInstancia() {
        if(instancia == null) {
            instancia = new Lexer();
        }
        return instancia;
    }

    List<String> preTokens = new ArrayList<>(); //Contiene cada palabra o símbolo a ser tokenizado

    public void cargarPrograma(String nombreDelArchivo){
        String programa = LectorDeArchivo.getInstancia().leerArchivo(nombreDelArchivo); // Guarda el programa en una cadena
        recorrerCadena(programa);
        System.out.print(preTokens);

    }

    public void agregarALaLista(String entrada) {
        if(!entrada.trim().isEmpty()){
            this.preTokens.add(entrada.trim());
        }
        
    }

    public void recorrerCadena(String cadena) {
        int largo = cadena.length();
        int inicio = 0;
        // Excepciones de lectura
        boolean esString = false;
        boolean esChar = false;
        //Proceso de identificación de caracteres
        for(int i = 0; i < largo; i++){
            switch (cadena.charAt(i)) {
                case '"':
                    if(esString) { // Si era String así que está por terminarlo
                        esString = false;
                        agregarALaLista(cadena.substring(inicio, i+1).trim()); //Agrega desde el inicio hasta el último "
                        inicio = i+1; //incluido el actual "

                    } else { // NO es string, se tiene que comenzar
                        esString = true;
                        agregarALaLista(cadena.substring(inicio, i).trim()); //Corta lo anterior
                        inicio = i; //Para que incluya el actual "
                    }
                    break;
                case 39:
                    if(esChar) { // Si era String así que está por terminarlo
                        esChar = false;
                        agregarALaLista(cadena.substring(inicio, i+1).trim()); //Agrega desde el inicio hasta el último "
                        inicio = i+1; //incluido el actual '

                    } else { // NO es string, se tiene que comenzar
                        esChar = true;
                        agregarALaLista(cadena.substring(inicio, i).trim()); //Corta lo anterior
                        inicio = i; //Para que incluya el actual '
                    }
                    break;
                case ' ':
                    if(!esString || esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim());
                        inicio = i+1;
                    }
                    break;
                case '(':
                    if(!esString || esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim());
                        agregarALaLista(cadena.substring(i, i+1));
                        inicio = i+1;
                    }
                    break;
                case ')':
                    if(!esString || esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim());
                        agregarALaLista(cadena.substring(i, i+1));
                        inicio = i+1;
                    }
                    break;
                case '[':
                    if(!esString || esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim());
                        agregarALaLista(cadena.substring(i, i+1));
                        inicio = i+1;
                    } 
                    
                    break;
                case ']':
                    if(!esString || esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim());
                        agregarALaLista(cadena.substring(i, i+1));
                        inicio = i+1;
                    }
                    break;
                case '{':
                    if(!esString || esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim());
                        agregarALaLista(cadena.substring(i, i+1));
                        inicio = i+1;
                    }
                    
                    break;
                case '}':
                    if(!esString || esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim());
                        agregarALaLista(cadena.substring(i, i+1));
                        inicio = i+1;
                    }
                    
                    break;
                case ';':
                    if(!esString || esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim());
                        agregarALaLista(cadena.substring(i, i+1));
                        inicio = i+1;
                    }
                    
                    break;
                case ',':
                    if(!esString || esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim());
                        agregarALaLista(cadena.substring(i, i+1));
                        inicio = i+1;
                    }
                    
                    break;
                case '.':
                    if(!esString || esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim());
                        agregarALaLista(cadena.substring(i, i+1));
                        inicio = i+1;
                    }
                    
                    break;
                case '%':
                    if(!esString || esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim());
                        agregarALaLista(cadena.substring(i, i+1));
                        inicio = i+1;
                    }
                    
                    break;
                case '*':
                    if(!esString || esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim());
                        agregarALaLista(cadena.substring(i, i+1));
                        inicio = i+1;
                    }
                    
                    break;
                case '/':
                    if(!esString || esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim());
                        agregarALaLista(cadena.substring(i, i+1));
                        inicio = i+1;
                    }
                    
                    break;
                // Casos + - < > = ! (Ya que todos estos pueden tener un igual al lado)
                case '+':
                    if(!esString || esChar) {
                        agregarALaLista(cadena.substring(inicio, i));
                        if(cadena.charAt(i+1) == '=') {  
                            agregarALaLista(cadena.substring(i, i+2));
                            inicio = i+2;
                            i= i+2;
                        } else {
                            agregarALaLista(cadena.substring(i, i+1));
                            inicio = i+1;
                        }
                    }
                    
                    break;
                case '-':
                    if(!esString || esChar) {
                        agregarALaLista(cadena.substring(inicio, i));
                        if(cadena.charAt(i+1) == '=') {  
                            agregarALaLista(cadena.substring(i, i+2));
                            inicio = i+2;
                            i= i+2;
                        } else {
                            agregarALaLista(cadena.substring(i, i+1));
                            inicio = i+1;
                        }
                    }
                    break;
                case '<':
                    if(!esString || esChar) {
                        agregarALaLista(cadena.substring(inicio, i));
                        if(cadena.charAt(i+1) == '=') {  
                            agregarALaLista(cadena.substring(i, i+2));
                            inicio = i+2;
                            i= i+2;
                        } else {
                            agregarALaLista(cadena.substring(i, i+1));
                            inicio = i+1;
                        }
                    }
                    break;
                case '>':
                    if(!esString || esChar) {
                        agregarALaLista(cadena.substring(inicio, i));
                        if(cadena.charAt(i+1) == '=') {  
                            agregarALaLista(cadena.substring(i, i+2));
                            inicio = i+2;
                            i= i+2;
                        } else {
                            agregarALaLista(cadena.substring(i, i+1));
                            inicio = i+1;
                        }
                    }
                    break;
                case '=':
                    if(!esString || esChar) {
                        agregarALaLista(cadena.substring(inicio, i));
                        if(cadena.charAt(i+1) == '=') {  
                            agregarALaLista(cadena.substring(i, i+2));
                            inicio = i+2;
                            i= i+2;
                        } else {
                            agregarALaLista(cadena.substring(i, i+1));
                            inicio = i+1;
                        }
                    }
                    break;
                case '!':
                    if(!esString || esChar) {
                        agregarALaLista(cadena.substring(inicio, i));
                        if(cadena.charAt(i+1) == '=') {  
                            agregarALaLista(cadena.substring(i, i+2));
                            inicio = i+2;
                            i= i+2;
                        } else {
                            agregarALaLista(cadena.substring(i, i+1));
                            inicio = i+1;
                        }
                    }
                    break;
                // Casos de && y ||
                case '&':
                    if(!esString || esChar) {
                        agregarALaLista(cadena.substring(inicio, i));
                        if(cadena.charAt(i+1) == '&') {  
                            agregarALaLista(cadena.substring(i, i+2));
                            inicio = i+2;
                            i= i+2;
                        } else {
                            agregarALaLista(cadena.substring(i, i+1));
                            inicio = i+1;
                        }
                    }
                    break;
                case '|':
                    if(!esString || esChar) {
                        agregarALaLista(cadena.substring(inicio, i));
                        if(cadena.charAt(i+1) == '|') {  
                            agregarALaLista(cadena.substring(i, i+2));
                            inicio = i+2;
                            i= i+2;
                        } else {
                            agregarALaLista(cadena.substring(i, i+1));
                            inicio = i+1;
                        }
                    }
                    break;
            
                default:
                    break;
            }
            Scanner leer = new Scanner(System.in);
        }
    }

    public String separarCaracteresEspeciales(String cadena) {
        cadena.replace("(", " ( ");
        cadena.replace(")", " ) ");
        cadena.replace("{", " { ");
        cadena.replace("}", " } ");
        cadena.replace("[", " [ ");
        cadena.replace("]", " ] ");
        cadena.replace("'", " ' ");
        cadena.replace("]", " ] ");
        cadena.replace(";", " ; ");
        cadena.replace(",", " , ");
        cadena.replace(".", " . ");
        cadena.replace("*", " * ");
        cadena.replace("+", " + ");
        cadena.replace("-", " - ");
        cadena.replace("<", " < ");
        cadena.replace(">", " > ");
        cadena.replace("=", " = ");

        return cadena;
    }
}

