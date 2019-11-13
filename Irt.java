  package edu.arscompile.irt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.arscompile.utilidades.EscritorDeArchivo;
import edu.arscompile.utilidades.LectorDeArchivo;
import edu.arscompile.scanner.Comparador;
import edu.arscompile.parser.Parser;
import edu.arscompile.modelos.IrtItem;
import edu.arscompile.modelos.Token;
import edu.arscompile.modelos.Objeto;
import edu.arscompile.modelos.IrtItem;
import edu.arscompile.modelos.Simbolo;

public class Irt {
    // Constructor

    Objeto cabeza = new Objeto(); //árbol de parseo

    List<IrtItem> raiz = new ArrayList<>();

    List<Simbolo> tablaSimbolos = new ArrayList<>(); //tabla de símbolos [Será de utilidad para los métodos]

    String banderaFor = "";

    public Objeto buscarMetodoEnLaTablaDeSimbolos(String nombre) {
        Objeto retorno = new Objeto();
        for (Simbolo var : tablaSimbolos) {
            if(var.getNombre().equalsIgnoreCase(nombre)){
                if(var.getTipo().getNombre().contains("Method")) {
                    return var.getObjeto();
                }
            }
            
        }
        return retorno;
    }

    public Irt(){
        raiz.add(new IrtItem("Programa",".data") );
    }

    public void setTablaSimbolos(List<Simbolo> tablaSimbolos) {
        this.tablaSimbolos = tablaSimbolos;
    }


    private static Irt instancia = new Irt();

    public static Irt getInstancia() {
        if(instancia == null) {
            instancia = new Irt();
        }
        return instancia;
    }

    public List<IrtItem> getRaiz() {
        return raiz;
    }
    
    public boolean verificarSiExiste(String cadena) {
        for (IrtItem var : raiz) {
            if(var.getValor().equalsIgnoreCase(cadena)){
                return true;
            }
        }
        return false;
    }


    public void construirStrings(List<Token> lista) {
        for (Token token : lista) {
            raiz.add(new IrtItem("String", token.toString().replace('@', '.') +": .asciiz " + token.getValue().toString()));
        }
    }

    public void construirVariables(Objeto objeto){
        String cadena;
        switch (objeto.getType().getNombre()) {
            case "FieldDec": 
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("FieldDec");
                    if(objeto.getTokens().get(0).getValue().toString().equalsIgnoreCase("int")) {
                        nodo.setValor(objeto.getTokens().get(1).getValue().toString() + ": .word 0"); //declaración de tipo int
                    } else { 
                        nodo.setValor(objeto.getTokens().get(1).getValue().toString() + ": .word 0"); //declaración de boolean
                    }
                    if(!verificarSiExiste(nodo.getValor())){
                        raiz.add(nodo);
                    }
                    
                    } 
                
                break;
            case "FieldArrayDec":
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("FieldArrayDec");
                    cadena = objeto.getTokens().get(1).getValue().toString() +": .word 0";
                    for (int i = 1; i < Integer.parseInt(objeto.getTokens().get(3).getValue().toString()); i++) {
                        cadena = cadena + ", 0";
                        
                    }
                    nodo.setValor(cadena);
                    if(!verificarSiExiste(nodo.getValor())){
                        raiz.add(nodo);
                    }
                }
                break;
            case "ParamDec":
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("ParamDec");
                    cadena = objeto.getTokens().get(1).getValue().toString() + ": .word 0" ;
                    nodo.setValor(cadena);
                    if(!verificarSiExiste(nodo.getValor())){
                        raiz.add(nodo);
                    }
                }
                break;
            case "Variable":
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("Variable");
                    cadena = objeto.getTokens().get(1).getValue().toString() + ": .word 0";
                    nodo.setValor(cadena);
                    if(!verificarSiExiste(nodo.getValor())){
                        raiz.add(nodo);
                    }
                }
                break;
            }
    }


    public void casoDeInstruccion(Objeto objeto) {
        //IrtItem nodo = new IrtItem();
        String cadena;
        switch (objeto.getType().getNombre()) {
            case "FieldDec": 
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("FieldDec");
                    if(objeto.getTokens().get(0).getValue().toString().equalsIgnoreCase("int")) {
                        nodo.setValor(objeto.getTokens().get(1).getValue().toString() + ": .word 0"); //declaración de tipo int
                    } else { 
                        nodo.setValor(objeto.getTokens().get(1).getValue().toString() + ": .word 0"); //declaración de boolean
                    }
                    raiz.add(nodo);
                    } 
                
                break;
            case "FieldArrayDec":
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("FieldArrayDec");
                    cadena = objeto.getTokens().get(1).getValue().toString() +": .word 0";
                    for (int i = 1; i < Integer.parseInt(objeto.getTokens().get(3).getValue().toString()); i++) {
                        cadena = cadena + ", 0";
                        
                    }
                    nodo.setValor(cadena);
                    raiz.add(nodo);
                }
                break;
            case "MethodDec":
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("MethodDec");
                    nodo.setValor(objeto.getTokens().get(1).getValue().toString()+":");
                    raiz.add(nodo);
                    raiz.add(new IrtItem("MethodDec", "# guardar la $ra para que no se pierda")); 
                    raiz.add(new IrtItem("MethodDec", "sub $sp, $sp,4"));  //push  $ra
                    raiz.add(new IrtItem("MethodDec", "sw $ra, ($sp)")); 
                    // System.out.println();
                    for (Objeto var : objeto.getHijos()) {
                        recorrerArbolParseo(var);
                    }

                    if(objeto.getTokens().get(1).getValue().toString().trim().equalsIgnoreCase("main")) {
                       // excepciones al método main 
                    }
                }
                break;
            case "ParamDec":
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("ParamDec");
                    cadena = "lw $t0, " + objeto.getTokens().get(1).getValue().toString() ;
                    nodo.setValor(cadena);
                    raiz.add(nodo);
                }
                break;
            case "Block":
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("Block");
                    cadena = "# inicio de bloque";
                    nodo.setValor(cadena);
                    raiz.add(nodo);
                    // System.out.println();

                    /// Guardando las variables de un scope diferente
                    // raiz.add(new IrtItem("Block", "# guardando las variables en stack "));
                    // for (int i = 0; i < objeto.getHijos().size(); i++) {
                    //     if(objeto.getHijos().get(i).getType().getNombre().contains("Variable")){
                    //         // push de la variable nueva
                    //         raiz.add(new IrtItem("Block", "# guardando la variable " + objeto.getHijos().get(i).getTokens().get(1).getValue().toString()));
                    //         raiz.add(new IrtItem("Block", "lw $t0, " + objeto.getHijos().get(i).getTokens().get(1).getValue().toString()));
                    //         raiz.add(new IrtItem("Block", "sub $sp,$sp,4"));  //push $t1
                    //         raiz.add(new IrtItem("Block", "sw $t0,($sp)"));
                    //     }
                    // }
                    // raiz.add(new IrtItem("Block", "# terminé de guardar las variables en stack "));
                    for (Objeto var : objeto.getHijos()) {
                        if(var.getType().getNombre().contains("Return")) {

                        }
                        recorrerArbolParseo(var);
                    }
                    // raiz.add(new IrtItem("Block", "# leyendo las variables en stack "));
                    // for (int i = objeto.getHijos().size() -1; i >= 0; i--) {
                    //     if(objeto.getHijos().get(i).getType().getNombre().contains("Variable")){
                    //         // push de la variable nueva
                    //         raiz.add(new IrtItem("Block", "# leyendo la variable " + objeto.getHijos().get(i).getTokens().get(1).getValue().toString()));
                    //         raiz.add(new IrtItem("Block", "lw $t0,($sp)")); //pop $t1
                    //         raiz.add(new IrtItem("Block", "addiu $sp,$sp,4"));
                    //         raiz.add(new IrtItem("Block", "sw $t0, "+ objeto.getHijos().get(i).getTokens().get(1).getValue().toString() ));  
                    //     }
                    // }
                    // raiz.add(new IrtItem("Block", "# terminé de leer las variables en stack "));
                }
                break;
            case "Variable":
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("Variable");
                    cadena = "lw $t0, " + objeto.getTokens().get(1).getValue().toString() ;
                    nodo.setValor(cadena);
                    raiz.add(nodo);
                }
                break;
            case "LocationExpresion": //técnicamente completo---
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("LocationExpresion");
                    cadena = "lw $s0, " + objeto.getTokens().get(0).getValue().toString() ;
                    nodo.setValor(cadena);
                    raiz.add(nodo);
                }
                break;
            case "LocationStatement": // idea tomada de https://stackoverflow.com/questions/9107093/mips-store-a-variable-in-another-variable
                {
                    //IrtItem nodo = new IrtItem();
                    //nodo.setTipo("LocationStatement");
                    // ------------------- Expresión
                    casoDeInstruccion(objeto.getHijos().get(0));
                    // el resultado de la expresión se guarda en $s0
                    // Llamando a la variable
                    raiz.add(new IrtItem("LocationStatement", "la $t0, " + objeto.getTokens().get(0).getValue().toString() ));
                    switch (objeto.getTokens().get(1).getValue().toString()) {
                        case "=":
                            raiz.add(new IrtItem("LocationStatement" ,"sw $s0, " +objeto.getTokens().get(0).getValue().toString() ));
                            break;
                        case "+=":
                            raiz.add(new IrtItem("LocationStatement" ,"lw $t1, " +objeto.getTokens().get(0).getValue().toString())); //en t1 se guardará el nuevo valor de la variable
                            raiz.add(new IrtItem("LocationStatement" ,"add $t1, $s0, $t1"));
                            raiz.add(new IrtItem("LocationStatement" ,"sw $t1, "+ objeto.getTokens().get(0).getValue().toString()));
                            break;
                        case "-=":
                            raiz.add(new IrtItem("LocationStatement" ,"lw $t1, " + objeto.getTokens().get(0).getValue().toString())); //en t1 se guardará el nuevo valor de la variable
                            raiz.add(new IrtItem("LocationStatement" ,"sub $t1, $t1, $s0"));
                            raiz.add(new IrtItem("LocationStatement" ,"sw $t1, " + objeto.getTokens().get(0).getValue().toString()));
                            break;
                        default:
                            break;
                    }

                    //nodo.setValor(cadena);
                    //raiz.add(nodo);
                }
                break;
            case "ArrayLocationExpresion": //idea tomada de http://people.cs.pitt.edu/~xujie/cs447/AccessingArray.htm
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("ArrayLocationExpresion");
                    raiz.add(new IrtItem("ArrayLocationExpresion", "# resolviendo array" + objeto.getTokens().get(0).getValue().toString()));
                    //Primero se debe de resolver la expresión
                    casoDeInstruccion(objeto.getHijos().get(0));
                    //El resultado de la operación expresión siempre se guarda en $s0
                    raiz.add(new IrtItem("ArrayLocationExpresion", "la $t3,"+ objeto.getTokens().get(0).getValue().toString()));
                    raiz.add(new IrtItem("ArrayLocationExpresion", "move $t2, $s0"));
                    raiz.add(new IrtItem("ArrayLocationExpresion", "add $t2, $t2, $t2"));
                    raiz.add(new IrtItem("ArrayLocationExpresion", "add $t2, $t2, $t2"));
                    raiz.add(new IrtItem("ArrayLocationExpresion", "add $t1, $t2, $t3"));
                    raiz.add(new IrtItem("ArrayLocationExpresion", "# en $s0 se guarda el valor a retornar de la expresión"));
                    raiz.add(new IrtItem("ArrayLocationExpresion", "lw $s0, 0($t1)")); //en # $s0 se guarda el valor a retornar de la expresión
                    //Ahora a operar la expresión
                    //raiz.add(nodo);
                }
                

                break;
            case "ArrayLocationStatement": // id[expr] assign_op expr    como hijos tiene las expresiones posteriores
                { // idea tomada de http://people.cs.pitt.edu/~xujie/cs447/AccessingArray.htm
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("ArrayLocationStatement");
                    //cadena = "lw $t0, " + objeto.getTokens().get(0).getValue().toString() ;
                    //Ahora a operar la expresión 1
                    casoDeInstruccion(objeto.getHijos().get(0));
                    //post operar la expresión tenemos el resultado en $s0
                    raiz.add(new IrtItem("ArrayLocationStatement", "la $t3,"+ objeto.getTokens().get(0).getValue().toString()));
                    raiz.add(new IrtItem("ArrayLocationStatement", "move $t2, $s0"));
                    raiz.add(new IrtItem("ArrayLocationStatement", "add $t2, $t2, $t2"));
                    raiz.add(new IrtItem("ArrayLocationStatement", "add $t2, $t2, $t2"));
                    raiz.add(new IrtItem("ArrayLocationStatement", "add $t1, $t2, $t3"));
                    raiz.add(new IrtItem("ArrayLocationStatement", "# en $s0 se guarda el valor a retornar de la expresión"));
                    //hasta aquí tenemos la posición en el array id[expr] guardada en $t1
                    //guardaremos en el stack $t1 para evitar su pérdida
                    raiz.add(new IrtItem("ArrayLocationStatement", "sub $sp,$sp,4"));  //push $t1
                    raiz.add(new IrtItem("ArrayLocationStatement", "sw $t1,($sp)"));
                    //ahora a operar la expresión 2
                    casoDeInstruccion(objeto.getHijos().get(0));
                    //post operar la expresión tenemos el resultado en $s0
                    raiz.add(new IrtItem("ArrayLocationStatement", "lw $t1,($sp)")); //pop $t1
                    raiz.add(new IrtItem("ArrayLocationStatement", "addiu $sp,$sp,4"));  
                    
                    //post operar la expresión tenemos el resultado en $s0
                    switch (objeto.getTokens().get(3).getValue().toString()) { //se hace algo distinto según el signo
                        case "+=":
                            raiz.add(new IrtItem("ArrayLocationStatement", "lw $s1, 0($t1)"));
                            raiz.add(new IrtItem("ArrayLocationStatement", "addiu $s1, $s1, $s0"));
                            raiz.add(new IrtItem("ArrayLocationStatement", "s1 $s0, 0($t1)"));
                            break;
                        
                        case "-=":
                            raiz.add(new IrtItem("ArrayLocationStatement", "lw $s1, 0($t1)"));
                            raiz.add(new IrtItem("ArrayLocationStatement", "sub $s1, $s1, $s0"));
                            raiz.add(new IrtItem("ArrayLocationStatement", "s1 $s0, 0($t1)"));
                            break;

                        case "=":
                            raiz.add(new IrtItem("ArrayLocationStatement", "sw $s0, 0($t1)"));
                            break;
                    
                        default:
                            break;
                    }

                    
                    //raiz.add(nodo);
                }

                break;
            case "Expresion":
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("Expresion");
                    //cadena = "lw $t0, " + objeto.getTokens().get(0).getValue().toString() ;
                    if(objeto.getHijos().size()==1) { // - ! o ()
                        if(objeto.getTokens().size() == 1) { // - !
                            if(objeto.getTokens().get(0).getValue().toString().equalsIgnoreCase("-")){
                                raiz.add(new IrtItem("Expresion", "# - expr"));
                                casoDeInstruccion(objeto.getHijos().get(0));
                                raiz.add(new IrtItem("Expresion", "sub $s0, $zero, $s0"));
                                raiz.add(new IrtItem("Expresion", "# en $s0 queda el resultado negado"));
                            } else if(objeto.getTokens().get(0).getValue().toString().equalsIgnoreCase("!")){
                                raiz.add(new IrtItem("Expresion", "# ! expr"));
                                casoDeInstruccion(objeto.getHijos().get(0));
                                raiz.add(new IrtItem("Expresion", "nor $s0, $s0, $s0"));
                                raiz.add(new IrtItem("Expresion", "# en $s0 queda el resultado negado"));
                            }
                        }   else { // ()
                            raiz.add(new IrtItem("Expresion", "# (expr)"));
                            casoDeInstruccion(objeto.getHijos().get(0)); 
                            raiz.add(new IrtItem("Expresion", "# en $s0 queda el resultado de la expresión en paréntesis"));
                        }

                    } else { //EXP BINOP EXP
                        raiz.add(new IrtItem("Expresion", "# expr binop expr"));
                        // Primera expresión, el resultado se guardará en $t0
                        casoDeInstruccion(objeto.getHijos().get(0)); 
                        raiz.add(new IrtItem("Expresion", "sub $sp,$sp,4"));  //push $s0 querá $t0 por si se altera
                        raiz.add(new IrtItem("Expresion", "sw $s0,($sp)"));
                        // Segunda expresión, el resultado se guardará en $t1
                        casoDeInstruccion(objeto.getHijos().get(2));
                        raiz.add(new IrtItem("Expresion", "move $t1, $s0"));
                        raiz.add(new IrtItem("Expresion", "lw $t0,($sp)")); //pop $t0
                        raiz.add(new IrtItem("Expresion", "addiu $sp,$sp,4"));  
                        switch (objeto.getHijos().get(1).getTokens().get(0).getValue().toString()) { //se hace algo distinto según el signo
                            case "+":
                                raiz.add(new IrtItem("Expresion", "add $s0, $t0, $t1")); 
                                break;
                            
                            case "-":
                                raiz.add(new IrtItem("Expresion", "sub $s0, $t0, $t1")); 
                                break;
    
                            case "*":
                                raiz.add(new IrtItem("Expresion", "mult $t0, $t1")); 
                                raiz.add(new IrtItem("Expresion", "mflo $s0")); 
                                break;
                            case "/":
                                raiz.add(new IrtItem("Expresion", "div $t0, $t1")); 
                                raiz.add(new IrtItem("Expresion", "mflo $s0")); 
                                break;
                            case "%":
                                raiz.add(new IrtItem("Expresion", "div $t0, $t1")); 
                                raiz.add(new IrtItem("Expresion", "mfhi $s0")); 
                                break;
                            case ">":
                                raiz.add(new IrtItem("Expresion", "slt $s0, $t1, $t0")); 
                                break;
                            case "<":
                                raiz.add(new IrtItem("Expresion", "slt $s0, $t0, $t1")); 
                                break;
                            case ">=":
                                // menor que
                                raiz.add(new IrtItem("Expresion", "slt $s1, $t1, $t0"));
                                //igual 
                                raiz.add(new IrtItem("Expresion", "slt $s2, $t0, $t1"));
                                raiz.add(new IrtItem("Expresion", "slt $s0, $t1, $t0"));
                                raiz.add(new IrtItem("Expresion", "or $s0, $s0, $s2")); 
                                raiz.add(new IrtItem("Expresion", "addi $s0, $s0, -1")); 
                                raiz.add(new IrtItem("Expresion", "li $s3, -1")); 
                                raiz.add(new IrtItem("Expresion", "mult $s0, $s3")); 
                                raiz.add(new IrtItem("Expresion", "mflo $s0"));   

                                                    // raiz.add(new IrtItem("Expresion", "sub $s0, $t0, $t1"));
                                                    // raiz.add(new IrtItem("Expresion", "nor $s0, $s0, $s0"));
                                // Comparación entre menor que e igual
                                raiz.add(new IrtItem("Expresion", "or $s0, $s0, $s1")); 
                                break;
                            case "<=":
                                raiz.add(new IrtItem("Expresion", "slt $s1, $t0, $t1"));
                                
                                //
                                raiz.add(new IrtItem("Expresion", "slt $s2, $t0, $t1"));
                                raiz.add(new IrtItem("Expresion", "slt $s0, $t1, $t0"));
                                raiz.add(new IrtItem("Expresion", "or $s0, $s0, $s2")); 
                                raiz.add(new IrtItem("Expresion", "addi $s0, $s0, -1")); 
                                raiz.add(new IrtItem("Expresion", "li $s3, -1")); 
                                raiz.add(new IrtItem("Expresion", "mult $s0, $s3")); 
                                raiz.add(new IrtItem("Expresion", "mflo $s0"));  

                                //
                                raiz.add(new IrtItem("Expresion", "or $s0, $s0, $s1")); 
                                break;
                            case "==":
                                // raiz.add(new IrtItem("Expresion", "sub $s0, $t0, $t1"));
                                // raiz.add(new IrtItem("Expresion", "nor $s0, $s0, $zero")); 

                                raiz.add(new IrtItem("Expresion", "slt $s2, $t0, $t1"));
                                raiz.add(new IrtItem("Expresion", "slt $s0, $t1, $t0"));
                                raiz.add(new IrtItem("Expresion", "or $s0, $s0, $s2")); 
                                raiz.add(new IrtItem("Expresion", "addi $s0, $s0, -1")); 
                                raiz.add(new IrtItem("Expresion", "li $s3, -1")); 
                                raiz.add(new IrtItem("Expresion", "mult $s0, $s3")); 
                                raiz.add(new IrtItem("Expresion", "mflo $s0"));  
                                break;
                            case "!=":
                                raiz.add(new IrtItem("Expresion", "slt $s2, $t0, $t1"));
                                raiz.add(new IrtItem("Expresion", "slt $s0, $t1, $t0"));
                                raiz.add(new IrtItem("Expresion", "or $s0, $s0, $s2")); 
                                break;
                            case "&&":
                                raiz.add(new IrtItem("Expresion", "and $s0, $t0, $t1")); 
                                break;
                            case "||":
                                raiz.add(new IrtItem("Expresion", "or $s0, $t0, $t1")); 
                                break;
                            default:
                                break;
                        }


                    }
                    raiz.add(nodo);
                }
                break;
            case "MethodCall": //llamada a un método tipo statement
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("MethodCall");
                    //en el token 1 está el nombre del método
                    //en los hijos están los parámetros
                    Objeto retorno = buscarMetodoEnLaTablaDeSimbolos(objeto.getTokens().get(0).getValue().toString());
                    raiz.add(new IrtItem("MethodCall", "# se guardan los valores antes de llamar al método"));
                    for (int i = 0; i < objeto.getHijos().size(); i++) {
                        raiz.add(new IrtItem("MethodCall", "# push de " + retorno.getHijos().get(i).getTokens().get(1).getValue().toString()));
                        raiz.add(new IrtItem("MethodCall", "lw $s0," + retorno.getHijos().get(i).getTokens().get(1).getValue().toString()));
                        raiz.add(new IrtItem("MethodCall", "sub $sp, $sp,4"));  //push 
                        raiz.add(new IrtItem("MethodCall", "sw $s0, ($sp)")); 
                        //ahora se mandan los parámetros
                        raiz.add(new IrtItem("MethodCall", "# mandar parámetro a " + retorno.getHijos().get(i).getTokens().get(1).getValue().toString()));
                        //se resuelve la expresión del parámetro
                        raiz.add(new IrtItem("MethodCall", "# resolviendo la expresión del parámetro")); 
                        casoDeInstruccion(objeto.getHijos().get(i));
                        // en $s0 se guarda el valor de la primera expresión
                        raiz.add(new IrtItem("MethodCall", "sw $s0," + retorno.getHijos().get(i).getTokens().get(1).getValue().toString()));

                    }
                    raiz.add(new IrtItem("MethodCall", "jal "+ objeto.getTokens().get(0).getValue().toString()));  
                    //leer stack del último al primero (importante) OJO
                    for (int i = objeto.getHijos().size()-1; i >= 0; i--) {
                        raiz.add(new IrtItem("MethodCall", "# pop de " + retorno.getHijos().get(i).getTokens().get(1).getValue().toString()));
                        raiz.add(new IrtItem("MethodCall", "lw $s0,($sp)")); //pop $t1
                        raiz.add(new IrtItem("MethodCall", "addiu $sp,$sp,4"));  
                        raiz.add(new IrtItem("MethodCall", "sw $s0," + retorno.getHijos().get(i).getTokens().get(1).getValue().toString()));
                        
                    }
                    raiz.add(new IrtItem("MethodCall", "move $s0, $v0"));  //en $v0 y $s0 se guardan los resultados de las expresiones y métodos
                    
                
                }
                break;
            case "MethodCallExpresion": //llamada a un método tipo statement
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("MethodCall");
                    //en el token 1 está el nombre del método
                    //en los hijos están los parámetros
                    Objeto retorno = buscarMetodoEnLaTablaDeSimbolos(objeto.getTokens().get(0).getValue().toString());
                    raiz.add(new IrtItem("MethodCall", "# se guardan los valores antes de llamar al método"));
                    for (int i = 0; i < objeto.getHijos().size(); i++) {
                        raiz.add(new IrtItem("MethodCall", "# push de " + retorno.getHijos().get(i).getTokens().get(1).getValue().toString()));
                        raiz.add(new IrtItem("MethodCall", "lw $s0," + retorno.getHijos().get(i).getTokens().get(1).getValue().toString()));
                        raiz.add(new IrtItem("MethodCall", "sub $sp, $sp,4"));  //push 
                        raiz.add(new IrtItem("MethodCall", "sw $s0, ($sp)")); 
                        //ahora se mandan los parámetros
                        raiz.add(new IrtItem("MethodCall", "# mandar parámetro a " + retorno.getHijos().get(i).getTokens().get(1).getValue().toString()));
                        //se resuelve la expresión del parámetro
                        raiz.add(new IrtItem("MethodCall", "# resolviendo la expresión del parámetro")); 
                        casoDeInstruccion(objeto.getHijos().get(i));
                        // en $s0 se guarda el valor de la primera expresión
                        raiz.add(new IrtItem("MethodCall", "sw $s0," + retorno.getHijos().get(i).getTokens().get(1).getValue().toString()));

                    }
                    raiz.add(new IrtItem("MethodCall", "jal "+ objeto.getTokens().get(0).getValue().toString()));  
                    //leer stack del último al primero (importante) OJO
                    for (int i = objeto.getHijos().size()-1; i >= 0; i--) {
                        raiz.add(new IrtItem("MethodCall", "# pop de " + retorno.getHijos().get(i).getTokens().get(1).getValue().toString()));
                        raiz.add(new IrtItem("MethodCall", "lw $s0,($sp)")); //pop $t1
                        raiz.add(new IrtItem("MethodCall", "addiu $sp,$sp,4"));  
                        raiz.add(new IrtItem("MethodCall", "sw $s0," + retorno.getHijos().get(i).getTokens().get(1).getValue().toString()));
                        
                    }
                    raiz.add(new IrtItem("MethodCall", "move $s0, $v0"));  //en $v0 y $s0 se guardan los resultados de las expresiones y métodos
                    


                
                }
                break;
            case "IntLiteral":
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("IntLiteral");
                    cadena = "li $s0, " + objeto.getTokens().get(0).getValue().toString() ;
                    nodo.setValor(cadena);
                    raiz.add(nodo);
                }
                break;
            case "CharLiteral":
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("CharLiteral");
                    cadena = "li $s0, '" + objeto.getTokens().get(0).getValue().toString()+"'" ;
                    nodo.setValor(cadena);
                    raiz.add(nodo);
                }
                break;
            case "BoolLiteral":
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("BoolLiteral");
                    if(objeto.getTokens().get(0).getValue().toString().equalsIgnoreCase("true")){
                        cadena = "li $s0, 1" ;
                    } else {
                        cadena = "li $s0, 0" ;
                    }
                    nodo.setValor(cadena);
                    raiz.add(nodo);
                }
                break;
            case "ContinueStatement":
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("Continue");
                    cadena = "# continuar"; //no debería de hacer nada...
                    nodo.setValor(cadena);
                    raiz.add(nodo);
                }
                break;
            case "BreakStatement":
                {
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("Break");
                    cadena = "j FinFor" + banderaFor; // flata crear de manera dinámica la etiqueta del for
                    nodo.setValor(cadena);
                    raiz.add(nodo);
                }
                break;
            case "CalloutStatement":
                {
                    
                    raiz.add(new IrtItem("CallOut[Print]", "li	$v0, 4"));
                    raiz.add(new IrtItem("CallOut[Print]", "la	$a0, "+objeto.getTokens().get(2).toString().replace('@', '.')));
                    raiz.add(new IrtItem("CallOut[Print]", "syscall"));
                }
                break;
            case "ReturnStatement":
                {
                    if(objeto.getHijos().size() > 0) {
                        IrtItem nodo = new IrtItem();
                        nodo.setTipo("Return");
                        //análisis  de expresión {resultado en $s0}
                        casoDeInstruccion(objeto.getHijos().get(0));
                        cadena = "move $v0, $s0"; // $v0 se usará para el return de cualquier método. Adicional $s0 es el resultado de la expresión
                        nodo.setValor(cadena);
                        raiz.add(nodo);
                    }
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("Return");
                    //pop de $ra
                    //raiz.add(new IrtItem("ArrayLocationStatement", "lw $ra,($sp)")); //pop $ra
                    //raiz.add(new IrtItem("ArrayLocationStatement", "addiu $sp,$sp,4"));  
                    raiz.add(new IrtItem("Return", "# pop de $ra"));  
                    raiz.add(new IrtItem("Return", "lw $ra,($sp)")); //pop $t1
                    raiz.add(new IrtItem("Return", "addiu $sp,$sp,4"));  
                    cadena = "jr $ra";
                    nodo.setValor(cadena);
                    raiz.add(nodo);
                }
                break;
            case "Programa":
                {
                    

                    IrtItem nodo2 = new IrtItem();
                    nodo2.setTipo("Programa");
                    cadena = ".text";
                    nodo2.setValor(cadena);

                    

                    boolean metodos = false;
                    for (Objeto var : objeto.getHijos()) {
                        if(var.getType().getNombre().contains("Method") && !metodos) { //evalúa cuando termina el uso de variables
                            metodos = true;
                            raiz.add(nodo2);
                            //raiz.add(new IrtItem("Main", "addiu $ra, $pc, 4"));
                            //raiz.add(new IrtItem("ArrayLocationStatement", "sub $sp,$sp,4"));  //push $ra
                            //raiz.add(new IrtItem("ArrayLocationStatement", "sw $ra,($sp)"));
                            raiz.add(new IrtItem("Main", "jal main"));
                            raiz.add(new IrtItem("EndCode", "li $v0, 10"));
                            raiz.add(new IrtItem("EndCode", "syscall"));
                            
                        }
                        if(metodos){
                            recorrerArbolParseo(var);
                        }
                        
                    }
                    
                }
                break;
            case "IfStatement":
                {
                    IrtItem nodo = new IrtItem();
                    String nombreDelIf = objeto.getTokens().get(0).toString().replace('@', '.'); //muy importante para las etiquetas
                    nodo.setTipo("IfStatement");
                    raiz.add(nodo);
                    casoDeInstruccion(objeto.getHijos().get(0)); //analiza la primera expresión
                    //si es 0 es porque es negativa o falsa
                    raiz.add(new IrtItem("IfStatement" ,"beq $s0, $zero, Else" + nombreDelIf ));
                    // el bloque 
                    casoDeInstruccion(objeto.getHijos().get(1));
                    // se saltará el final sin hacer el else
                    raiz.add(new IrtItem("IfStatement" ,"j FinIf" + nombreDelIf ));
                    //else 
                    raiz.add(new IrtItem("IfStatement" ,"Else" + nombreDelIf +":"));
                    if(objeto.getHijos().size() > 2) {
                        casoDeInstruccion(objeto.getHijos().get(2));
                    }
                    //fin del IF
                    raiz.add(new IrtItem("IfStatement" ,"FinIf" + nombreDelIf+":" ));
                }
                break;
            case "ForStatement":
                {
                    banderaFor = objeto.getTokens().get(0).toString().replace('@', '.');
                    String nombreDelFor = objeto.getTokens().get(0).toString().replace('@', '.');
                    IrtItem nodo = new IrtItem();
                    nodo.setTipo("ForStatement");
                    raiz.add(nodo);
                    raiz.add(new IrtItem("ForStatement" ,"# For id = expr" ));
                    casoDeInstruccion(objeto.getHijos().get(1));
                    raiz.add(new IrtItem("ForStatement" ,"sw $s0, " +objeto.getHijos().get(0).getTokens().get(0).getValue().toString() ));
                    raiz.add(new IrtItem("ForStatement" ,"# {}" ));
                    casoDeInstruccion(objeto.getHijos().get(2));
                    raiz.add(new IrtItem("ForStatement" ,"bne $s0, $zero, FinFor" + nombreDelFor ));
                    //ahora sí comienza el bloque                  
                    raiz.add(new IrtItem("ForStatement" ,"InicioFor" + nombreDelFor+":" ));
                    casoDeInstruccion(objeto.getHijos().get(3));
                    //fin del bloque
                    casoDeInstruccion(objeto.getHijos().get(2));
                    raiz.add(new IrtItem("ForStatement" ,"beq $s0, $zero, InicioFor" + nombreDelFor ));
                    raiz.add(new IrtItem("ForStatement" ,"FinFor" + nombreDelFor+":" ));




                }
                break;
            default:
                break;
        }
        // if (nodo.getValor() != null) {
        //     System.out.print(nodo.getValor());
        // // }
        // // else if(!nodo.getValor().isEmpty()) {
        // //     System.out.print(nodo.getValor());
        // } else {
        //     System.out.print("#" + nodo.getTipo());
        // }
        //raiz.add(nodo);
        

    }
    
    
    
    public void recorrerArbolParseo(Objeto objeto){
        // for (int i = 0; ((i <= 1) && (i < tab)); i++) {
        //     System.out.print("\t");
        // }
        
        casoDeInstruccion(objeto);
        
        

    }

    public void imprimirInstrucciones(){
        for (IrtItem var : raiz) {
            if(! (var.getTipo().contains("Program") || var.getTipo().contains("MethodDec")) ){
                System.out.print("\t");
            }
            if (var.getValor() != null) {
                System.out.print(var.getValor());
            } else {
                System.out.print("#" + var.getTipo());
            }
            System.out.println();

        }
    }

    



}