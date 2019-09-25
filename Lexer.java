package edu.arscompile.scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.arscompile.utilidades.EscritorDeArchivo;
import edu.arscompile.utilidades.LectorDeArchivo;
import edu.arscompile.scanner.Comparador;
import edu.arscompile.parser.Parser;
import edu.arscompile.modelos.Token;

public class Lexer {
    // Constructor

    public Lexer(){

    }

    private static Lexer instancia = new Lexer();
    
    public static Lexer getInstancia() {
        if(instancia == null) {
            instancia = new Lexer();
        }
        return instancia;
    }

    //Atributos aparte

    List<Token> tokens = new ArrayList<>(); //Contiene cada palabra o símbolo a ser tokenizado


    public void cargarPrograma(String nombreDelArchivo, boolean debug){
        // Encabezado y proceso
        System.out.println("Leyendo el archivo "+nombreDelArchivo);
        System.out.println("Etapa: ScAnNeR");
        

        

        // Lectura de archivo
        List<String> programa = LectorDeArchivo.getInstancia().leerArchivo(nombreDelArchivo); // Guarda el programa en una cadena
        for(int i = 0; i < programa.size(); i++){
            recorrerCadena(programa.get(i), i);
        }
        
        //System.out.println(programa); //Imprimir el programa


        /*---------------------------------------LECTURA DE TOKENS------------------------------------------------ */
        tokens.forEach((action) -> Comparador.getInstancia().categorizar(action)); //Determina el tipo de Token
        if(debug) {
            tokens.forEach((action) -> action.imprimirTokenBonitoLargo());
        }
        //
        EscritorDeArchivo.getInstancia().escribir("resultadosScanner", tokens);
        EscritorDeArchivo.getInstancia().escribirObjeto("resultadosScanner", tokens);

        if(!tokens.isEmpty() && !debug){ barraDeProceso();}

        Comparador.getInstancia().imprimirErrores();

        

        // tokens = LectorDeArchivo.getInstancia().leerTokens("resultadosScanner");

        // Parser.getInstancia().asignarTokens(tokens);
        // Parser.getInstancia().analizarTokens();

    }

    public void agregarALaLista(String entrada, int linea, int posicion) {
        if(!entrada.trim().isEmpty()){
            this.tokens.add(new Token(entrada.trim(), linea, posicion));
        }
        
    }

    public void barraDeProceso(){
        char[] animationChars = new char[]{'|', '/', '-', '\\'};
        for (int i = 0; i <= 100; i++) {
            System.out.print("Procesando: " + i + "% " + animationChars[i % 4] + "\r");

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void recorrerCadena(String cadena, int linea) {
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
                        agregarALaLista(cadena.substring(inicio, i+1).trim(), linea, inicio); //Agrega desde el inicio hasta el último "
                        inicio = i+1; //incluido el actual "

                    } else if(!esString && !esChar) { // NO es string, se tiene que comenzar
                        esString = true;
                        agregarALaLista(cadena.substring(inicio, i).trim(), linea, inicio); //Corta lo anterior
                        inicio = i; //Para que incluya el actual "
                    }
                    break;
                case 39:
                    if(esChar) { // Si era Char así que está por terminarlo
                        esChar = false;
                        agregarALaLista(cadena.substring(inicio, i+1).trim(), linea, inicio); //Agrega desde el inicio hasta el último "
                        inicio = i+1; //incluido el actual '

                    } else if(!esChar && !esString) { // NO es char, se tiene que comenzar
                        esChar = true;
                        agregarALaLista(cadena.substring(inicio, i).trim(), linea, inicio); //Corta lo anterior
                        inicio = i; //Para que incluya el actual '
                    }
                    break;
                case ' ':
                    if(!esString && !esChar) {
                        agregarALaLista(cadena.substring(inicio, i).trim(), linea, inicio);
                        inicio = i;
                    }
                    break;
                case '(':
                    if(!esString && !esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim(), linea, inicio);
                        agregarALaLista(cadena.substring(i, i+1), linea, i);
                        inicio = i+1;
                    }
                    break;
                case ')':
                    if(!esString && !esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim(), linea, inicio);
                        agregarALaLista(cadena.substring(i, i+1), linea, i);
                        inicio = i+1;
                    }
                    break;
                case '[':
                    if(!esString && !esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim(), linea, inicio);
                        agregarALaLista(cadena.substring(i, i+1), linea, i);
                        inicio = i+1;
                    } 
                    
                    break;
                case ']':
                    if(!esString && !esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim(), linea, inicio);
                        agregarALaLista(cadena.substring(i, i+1), linea, i);
                        inicio = i+1;
                    }
                    break;
                case '{':
                    if(!esString && !esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim(), linea, inicio);
                        agregarALaLista(cadena.substring(i, i+1), linea, i);
                        inicio = i+1;
                    }
                    
                    break;
                case '}':
                    if(!esString && !esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim(), linea, inicio);
                        agregarALaLista(cadena.substring(i, i+1), linea, i);
                        inicio = i+1;
                    }
                    
                    break;
                case ';':
                    if(!esString && !esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim(), linea, inicio);
                        agregarALaLista(cadena.substring(i, i+1), linea, i);
                        inicio = i+1;
                    }
                    
                    break;
                case ',':
                    if(!esString && !esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim(), linea, inicio);
                        agregarALaLista(cadena.substring(i, i+1), linea, i);
                        inicio = i+1;
                    }
                    
                    break;
                // El punto no sirve para nada    
                // case '.':
                //     if(!esString && !esChar){
                //         agregarALaLista(cadena.substring(inicio, i).trim(), linea, inicio);
                //         agregarALaLista(cadena.substring(i, i+1), linea, i);
                //         inicio = i+1;
                //     }
                    
                    // break;
                case '%':
                    if(!esString && !esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim(), linea, inicio);
                        agregarALaLista(cadena.substring(i, i+1), linea, i);
                        inicio = i+1;
                    }
                    
                    break;
                case '*':
                    if(!esString && !esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim(), linea, inicio);
                        agregarALaLista(cadena.substring(i, i+1), linea, i);
                        inicio = i+1;
                    }
                    
                    break;
                case '/':
                    if(!esString && !esChar){
                        agregarALaLista(cadena.substring(inicio, i).trim(), linea, inicio);

                        if(cadena.charAt(i+1) == '/') {
                            i = largo;
                        } else{
                            agregarALaLista(cadena.substring(i, i+1), linea, i);
                            inicio = i+1;
                        }
                        
                    }
                    
                    break;
                // Casos + - < > = ! (Ya que todos estos pueden tener un igual al lado)
                case '+':
                    if(!esString && !esChar) {
                        agregarALaLista(cadena.substring(inicio, i), linea, inicio);
                        if(cadena.charAt(i+1) == '=') {  
                            agregarALaLista(cadena.substring(i, i+2), linea, i);
                            inicio = i+2;
                            i= i+2;
                        } else {
                            agregarALaLista(cadena.substring(i, i+1), linea, i);
                            inicio = i+1;
                        }
                    }
                    
                    break;
                case '-':
                    if(!esString && !esChar) {
                        agregarALaLista(cadena.substring(inicio, i), linea, inicio);
                        if(cadena.charAt(i+1) == '=') {  
                            agregarALaLista(cadena.substring(i, i+2), linea, i);
                            inicio = i+2;
                            i= i+2;
                        } else {
                            agregarALaLista(cadena.substring(i, i+1), linea, i);
                            inicio = i+1;
                        }
                    }
                    break;
                case '<':
                    if(!esString || !esChar) {
                        agregarALaLista(cadena.substring(inicio, i), linea, inicio);
                        if(cadena.charAt(i+1) == '=') {  
                            agregarALaLista(cadena.substring(i, i+2), linea, i);
                            inicio = i+2;
                            i= i+2;
                        } else {
                            agregarALaLista(cadena.substring(i, i+1), linea, i);
                            inicio = i+1;
                        }
                    }
                    break;
                case '>':
                    if(!esString && !esChar) {
                        agregarALaLista(cadena.substring(inicio, i), linea, inicio);
                        if(cadena.charAt(i+1) == '=') {  
                            agregarALaLista(cadena.substring(i, i+2), linea, i);
                            inicio = i+2;
                            i= i+2;
                        } else {
                            agregarALaLista(cadena.substring(i, i+1), linea, i);
                            inicio = i+1;
                        }
                    }
                    break;
                case '=':
                    if(!esString && !esChar) {
                        agregarALaLista(cadena.substring(inicio, i), linea, inicio);
                        if(cadena.charAt(i+1) == '=') {  
                            agregarALaLista(cadena.substring(i, i+2), linea, i);
                            inicio = i+2;
                            i= i+2;
                        } else {
                            agregarALaLista(cadena.substring(i, i+1), linea, i);
                            inicio = i+1;
                        }
                    }
                    break;
                case '!':
                    if(!esString && !esChar) {
                        agregarALaLista(cadena.substring(inicio, i), linea, inicio);
                        if(cadena.charAt(i+1) == '=') {  
                            agregarALaLista(cadena.substring(i, i+2), linea, i);
                            inicio = i+2;
                            i= i+2;
                        } else {
                            agregarALaLista(cadena.substring(i, i+1), linea, i);
                            inicio = i+1;
                        }
                    }
                    break;
                // Casos de && y ||
                case '&':
                    if(!esString && !esChar) {
                        agregarALaLista(cadena.substring(inicio, i), linea, inicio);
                        if(cadena.charAt(i+1) == '&') {  
                            agregarALaLista(cadena.substring(i, i+2), linea, i);
                            inicio = i+2;
                            i= i+2;
                        } else {
                            agregarALaLista(cadena.substring(i, i+1), linea, i);
                            inicio = i+1;
                        }
                    }
                    break;
                case '|':
                    if(!esString && !esChar) {
                        agregarALaLista(cadena.substring(inicio, i), linea, inicio);
                        if(cadena.charAt(i+1) == '|') {  
                            agregarALaLista(cadena.substring(i, i+2), linea, i);
                            inicio = i+2;
                            i= i+2;
                        } else {
                            agregarALaLista(cadena.substring(i, i+1), linea, i);
                            inicio = i+1;
                        }
                    }
                    break;
            
                default:
                    break;
            }
            if((i == (largo-1)) && (inicio != i)){
                agregarALaLista(cadena.substring(inicio, largo), linea, inicio);
                inicio = i+1;

            }
            
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

