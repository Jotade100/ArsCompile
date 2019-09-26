package edu.arscompile.parser;

import java.util.ArrayList;
import java.util.List;

import edu.arscompile.modelos.Token;
import edu.arscompile.modelos.CeldaParser;
import edu.arscompile.utilidades.EscritorDeArchivo;
import edu.arscompile.utilidades.LectorDeArchivo;

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
    public void asignarTokens() {
        System.out.println("\nEtapa: PaRsEr");
        getInstancia().tokens = LectorDeArchivo.getInstancia().leerTokens("resultadosScanner");
        Parser.getInstancia().analizarTokens();
    }

    public void analizarTokens() {
        if((tokens.get(0).getType().getType() != 1) || (tokens.get(1).getType().getType() != 2)) {
            System.out.println("Error en la nomenclatura class Program, a partir de la l\u00ednea " + tokens.get(0).getLeft() );
            return;
        }
        classToken();
    }

    public void error(Token elemento) {
        System.out.println("Error en la l\u00ednea " + (elemento.getLeft()+1) + " y caracter " + elemento.getRight() + ", palabra " + elemento.getValue() + " del tipo " + elemento.getType().getNombre() +" no hace sentido, se esperaba un elemento diferente. Corrija este error e int\u00e9ntelo de nuevo");
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
                                    if(tokens.get(contador).getType().getType()==5){
                                        contador++; // coma
                                        goTo2 = true;
                                    } else if(tokens.get(contador).getType().getType()==8) { // punto y coma
                                        contador++;
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
        boolean terminarPeticionMetodos = true;
        while(terminarPeticionMetodos){
            if((tokens.get(contador).getType().getType()==9) || (tokens.get(contador).getType().getType()==10)){
                contador++;
                if((tokens.get(contador).getType().getType()==29)){
                    contador++;
                    if((tokens.get(contador).getType().getType()==12)){
                        contador++;
                        switch(tokens.get(contador).getType().getType()){
                            case 13: // paréntesis de cierre
                                contador++;
                                block();
                                break;
                            case 9:
                                boolean bandera = true;
                                while(bandera) {
                                    if(tokens.get(contador).getType().getType()==9) { //type
                                        contador++;
                                        if((tokens.get(contador).getType().getType()==29)){ //id
                                            contador++;
                                        } else {
                                            System.out.println("ESPERADO: ID");
                                            error(tokens.get(contador));  
                                        }
    
                                        if(tokens.get(contador).getType().getType()==5) { //coma
                                            contador++;
                                        } else if (tokens.get(contador).getType().getType()==13) { //paréntesis de cierre
                                            contador++;
                                            bandera = false;
                                            block();
                                        } else {
                                            System.out.println("ESPERADO: , o )");
                                            error(tokens.get(contador));
                                        }
                                    } 
                                    
                                } 
                                
                                break;
                            default:
                                error(tokens.get(contador));
                                break;
                        }
                        
                        
                    } else {error(tokens.get(contador));}
    
                    
                } else {error(tokens.get(contador));}
    
            } else if(tokens.get(contador).getType().getType()==4) { //paréntesis de cierre
                //no hace nada, simplemente ya no hay métodos por declarar
                terminarPeticionMetodos = false;

            } else {error(tokens.get(contador));}
    
        }
        
    }

    public void block(){
        // System.out.println("BLOCK");
        if(tokens.get(contador).getType().getType()==3){
            contador++;
            boolean bandera = true;
            while(bandera) {
                    switch(tokens.get(contador).getType().getType()){
                        case 4: // llave de cierre
                            contador++;
                            bandera = false;
                            break;
                /* --------------declaración de variables -------------------------------- *///
                        case 9: //type --entra a var declaration
                                System.out.println("VAR DECLARATION");
                                if(tokens.get(contador).getType().getType()==9) { //type
                                    contador++;
                                    if((tokens.get(contador).getType().getType()==29)){ //id
                                        contador++;
                                    } else {
                                        System.out.println("ESPERADO: ID");
                                        error(tokens.get(contador));  
                                    }

                                    if(tokens.get(contador).getType().getType()==5) { //coma
                                        contador++;
                                        boolean bandera2 = true;
                                        while (bandera2){
                                            if(tokens.get(contador).getType().getType()==29){ //id
                                                contador++;
                                            } else {System.out.println("ESPERADO: ID"); error(tokens.get(contador));}

                                            if(tokens.get(contador).getType().getType()==5) { //coma
                                                contador++;
                                            } else if(tokens.get(contador).getType().getType()==8) { //punto y coma
                                                contador++;
                                                bandera2 = false;
                                            } else {
                                                System.out.println("ESPERADO: , o ;");
                                                error(tokens.get(contador));
                                            }
                                        }
                                        

                                    }else if(tokens.get(contador).getType().getType()==8) { // punto y coma
                                        contador++;
                                    }  else {
                                        System.out.println("ESPERADO: ;");
                                        error(tokens.get(contador));
                                       
                                    };
                                } else {
                                    System.out.println("ESPERADO: TYPE");
                                    error(tokens.get(contador));
                                    
                                }
                                
                            
                            
                            break;
                        // espacio para statement
                        /* --------------statement -------------------------------- *///
                        case 29: //id
                            statement();
                            break;
                        case 17: //break
                            statement();
                            break;
                        case 18: //continue
                            statement();
                            break;
                        case 11: //if
                            statement();
                            break;
                        case 20: //callout
                            statement();
                            break;
                        case 15: //for
                            statement();
                            break;
                        case 16: //return
                            statement();
                            break;
                        case 3: //bloque
                            block();
                            break;
                        /* --------------FIN DE statement -------------------------------- *///    
                        default:
                            System.out.println("NO ES UN ENUNCIADO V\u00c1LIDO NI DECLARACI\u00d3N DE VARIABLE");
                            error(tokens.get(contador));
                            
                            break;
                    }
            }


        } else {
            error(tokens.get(contador));
            
        }
    }

    public void statement(){
        boolean recursivo = true;
        while(recursivo){
            recursivo = false;
            /// hay que volverlo recursivo
            System.out.println("STATEMENT");
            switch (tokens.get(contador).getType().getType()) {
                case 15: //for
                    contador++;
                    if(tokens.get(contador).getType().getType() == 29){ //id
                        contador++;
                    } else {
                        System.out.println("ESPERABA ID");
                        error(tokens.get(contador));
                    }
                    if(tokens.get(contador).getType().getType() == 32){ //=
                        contador++;
                    } else {
                        System.out.println("ESPERABA =");
                        error(tokens.get(contador));
                    }
                    expresion();
                    if(tokens.get(contador).getType().getType() == 5){ //coma
                        contador++;
                    } else {
                        System.out.println("ESPERABA: ,");
                        error(tokens.get(contador));
                    }
                    expresion();
                    block();


                    break;
                case 16: //return expresión;
                    contador++;
                    if(tokens.get(contador).getType().getType() == 8){ //punto y coma
                        contador++;
                        break;
                    } 
                    
                    expresion();
                    if(tokens.get(contador).getType().getType() == 8){ //punto y coma
                        contador++;
                    } else {
                        System.out.println("FALTA ;");
                        error(tokens.get(contador));
                    }
                    break;

                case 17: //break;
                    contador++;
                    if(tokens.get(contador).getType().getType() == 8){ //punto y coma
                        contador++;
                    } else {
                        System.out.println("FALTA ;");
                        error(tokens.get(contador));
                    }
                    break;
                case 18: //continue;
                    contador++;
                    if(tokens.get(contador).getType().getType() == 8){ //punto y coma
                        contador++;
                    } else {
                        System.out.println("FALTA;");
                        error(tokens.get(contador));
                    }
                    break; 
                case 29: //id
                    contador++;
                    //location
                    switch (tokens.get(contador).getType().getType()) {
                        //<location> assign_op expr
                        case 19: //asign_op
                            contador++;
                            expresion();
                            if(tokens.get(contador).getType().getType()==8){
                                contador++;
                                
                                //nice terminó statement
                            } else {
                                System.out.println("ESPERADO: ;");
                                error(tokens.get(contador));
                            }
                            break;
                        case 32: //asign_op =
                            contador++;
                            expresion();
                            if(tokens.get(contador).getType().getType()==8){
                                //nice terminó statement
                                contador++;
                                
                            } else {
                                System.out.println("ESPERADO: ;");
                                error(tokens.get(contador));
                            }
                            break;
                        //<location>
                        case 6:
                            contador++;
                            expresion();
                            if(tokens.get(contador).getType().getType()==7){
                                contador++;
                            } else {System.out.println("ESPERADO: ]");error(tokens.get(contador));}
                            if((tokens.get(contador).getType().getType()==19) ||(tokens.get(contador).getType().getType()==32)){
                                contador++;
                            } else {System.out.println("ESPERADO: ASIGNATION_OPERATOR"); error(tokens.get(contador));}
                            expresion();
                            if(tokens.get(contador).getType().getType()==8) { //punto y coma
                                contador++;
                            } else {
                                System.out.println("ESPERADO: ;"); error(tokens.get(contador));}

                            break;
                        // method call 
                        // method name ( expresión* )
                        case 12:
                            contador++;
                            boolean bandera = true;
                            while(bandera){ // varias expresiones o cierre inmediato
                                if(tokens.get(contador).getType().getType()==13) { //paréntesis de cierre (carece de expresiones)
                                    contador++;
                                    bandera = false;
                                    if(tokens.get(contador).getType().getType()==8) { //punto y coma
                                        contador++;
                                        bandera = false;
                                    } else {System.out.println("ESPERADO: ;");error(tokens.get(contador));}
                                    break;
                                }
                                System.out.println("qwertyuiop");
                                expresion();
                                if(tokens.get(contador).getType().getType()==5){ //coma
                                    System.out.println("COMA"); 
                                    contador++;
                                    //busca más expresiones

                                } else if(tokens.get(contador).getType().getType()==13) { //paréntesis de cierre
                                    contador++;
                                    bandera = false;
                                    if(tokens.get(contador).getType().getType()==8) { //punto y coma
                                        contador++;
                                        bandera = false;
                                        break;
                                    } else {System.out.println("ESPERADO: ;");error(tokens.get(contador));}
                                } else {System.out.println("ESPERADO: , o ;");error(tokens.get(contador));}

                                

                            }
                            
                            break;
                        
                        default:
                            break;
                    }
                    
                    break;
                
                case 20: // callout
                    contador++;
                    if(tokens.get(contador).getType().getType()==12){ //paréntesis apertura
                        contador++;
                    } else{
                        System.out.println("ESPERADO: (");
                        error(tokens.get(contador));
                    }

                    if(tokens.get(contador).getType().getType()==27){ //string literal
                        contador++;
                    } else{
                        System.out.println("ESPERADO: STRING");
                        error(tokens.get(contador));
                    }

                    if(tokens.get(contador).getType().getType()==5){ //coma
                        contador++;
                        boolean bandera = true;
                        while(bandera){
                            calloutArgs();
                            if(tokens.get(contador).getType().getType()==5) { //coma
                                contador++;
                            } else if(tokens.get(contador).getType().getType()==13) { //paréntesis cierre
                                contador++;
                                bandera = false;
                                if(tokens.get(contador).getType().getType()==8) { //punto y coma
                                    contador++;
                                    bandera = false;
                                } else {System.out.println("ESPERADO: ;"); error(tokens.get(contador));}
                            } else {System.out.println("ESPERADO: , o )");error(tokens.get(contador));}
                        }
                        

                    } else if(tokens.get(contador).getType().getType()==13){ // paréntesis de cierre
                        contador++;
                        if(tokens.get(contador).getType().getType()==8) { //punto y coma
                            contador++;
                                    
                        } else {System.out.println("ESPERADO: ;");error(tokens.get(contador));}
                    } else{
                        System.out.println("ESPERADO: , o )");
                        error(tokens.get(contador));
                    }

                    
                    break;

                case 11: // if ( expr) block y puede no haber else block
                    contador++;
                    if(tokens.get(contador).getType().getType()==12) { //parentesis apertura
                        contador++;         
                    } else {System.out.println("ESPERADO: (");error(tokens.get(contador));}
                    expresion();
                    if(tokens.get(contador).getType().getType()==13) { //parentesis cierre
                        contador++;                               
                    } else {System.out.println("ESPERADO: )");error(tokens.get(contador));}
                    block();
                    if(tokens.get(contador).getType().getType()==14) { // else
                        contador++;
                        block();

                    } 
                    break;
                    

                case 4: // llave de cierre, no hay statement
                    recursivo = false;
                    break;

            
                default:
                    error(tokens.get(contador));
                    break;
            }

        }
        
        


    }


    public void expresion(){
        System.out.println("EXPRESIÓN" + tokens.get(contador).getValue());
        switch (tokens.get(contador).getType().getType()) {
            case 29: //id corresponderá a location y method_call1
                contador++;
                if(tokens.get(contador).getType().getType() == 6){ // [ caso id[expr]
                    contador++;
                    expresion();
                    
                    if(tokens.get(contador).getType().getType() == 6){ //]
                        
                    } else {
                        System.out.println("ESPERADO: ]");error(tokens.get(contador));
                    }
                } else if(tokens.get(contador).getType().getType() ==12){ // ( caso id(expr*)
                    contador++;
                    System.out.println("caso id(expr*)");
                    if (tokens.get(contador).getType().getType() == 13) { //cierre de una vez
                        contador++;
                        break;
                    }
                    boolean bandera = true;
                    while(bandera) {
                        expresion();
                        if (tokens.get(contador).getType().getType() == 5) { // coma
                            System.out.println("COMA " + bandera);
                            contador++;
                        }  else if(tokens.get(contador).getType().getType() == 13){ //cierre )
                            contador++;
                            bandera = false;
                            break;
                        } else {
                            System.out.println("ESPERADO: , o )");error(tokens.get(contador));
                        }
                        System.out.println(bandera);
                    }
                    
                    
                    
                }
                System.out.println("VARIABLE COMÚN" + tokens.get(contador).getValue());
                if(esBinOp()) {
                    binOp();
                    expresion();
                }

                break;
            case 20: //callout corresponde a method_call2
                contador++;
                if(tokens.get(contador).getType().getType() ==12) { // paréntesis (
                    contador++;  
                } else {
                    System.out.println("ESPERADO: (");error(tokens.get(contador));
                }
                if(tokens.get(contador).getType().getType() ==27) { // string literal
                    contador++;  
                } else {
                    System.out.println("ESPERADO: STRING_LITERAL");error(tokens.get(contador));
                }
                if(tokens.get(contador).getType().getType() ==5) { // coma
                    contador++; 
                    boolean bandera= true;
                    while(bandera){
                        calloutArgs();
                        if(tokens.get(contador).getType().getType() ==5) { //coma 
                            contador++;
                        } else if (tokens.get(contador).getType().getType() ==13) {
                            bandera = false;
                            contador++;
                            break;
                        }
                    }
                    
                } else if(tokens.get(contador).getType().getType() ==13){ //parentesis de cierre
                    contador++;

                } else {System.out.println("ESPERADO: )");error(tokens.get(contador));}
                
                if(esBinOp()) {
                    binOp();
                    expresion();
                }

                break;
        /*------------------------------- LITERAL ---------------------------------- */
            case 25: //boolean
                contador++;
                if(esBinOp()) {
                    binOp();
                    expresion();
                }
                break;
            case 26: //char
                contador++;
                if(esBinOp()) {
                    binOp();
                    expresion();
                }
                break;
            case 28: //int literal
                contador++;
                if(esBinOp()) {
                    binOp();
                    expresion();
                }
                break;
            case 30: //minus
                contador++;
                expresion();
                if(esBinOp()) {
                    binOp();
                    expresion();
                }
                break;
            case 31: // ! (exp)
                contador++;
                expresion();
                if(esBinOp()) {
                    binOp();
                    expresion();
                }
                break;
            case 12: //paréntesis apertura (
                contador++;
                expresion();
                if(tokens.get(contador).getType().getType() ==13) { //cierre
                    contador++;
                } else {
                    System.out.println("ESPERADO: )");
                    error(tokens.get(contador));
                }
                if(esBinOp()) {
                    binOp();
                    expresion();
                }
                break;
            default:
                System.out.print("NADA");
                break;
        }
        

    }

    public boolean esBinOp(){
        switch (tokens.get(contador).getType().getType()) {
            case 21:// aritmético
                return true;
            case 22:// rel_op
                return true;
            case 23:// eq_op
                return true;
            case 24:// cond_op
                return true;
            case 30:// minus_op
                return true;
            case 31:// =
                return true;
            default:
                return false;
        }
    }

    public void binOp(){
        switch (tokens.get(contador).getType().getType()) {
            case 21:// aritmético
                contador++;
                break;
            case 22:// rel_op
                contador++;
                break;
            case 23:// eq_op
                contador++;
                break;
            case 24:// cond_op
                contador++;
                break;
            case 30:// minus_op
                contador++;
                break;
            case 31:// =
                contador++;
                break;
            default:
                System.out.println("NO ES NINGÚN OPERADOR");
                error(tokens.get(contador));
                break;
        }
    }

    public void calloutArgs(){
        System.out.println("calloutArgs");
        if(tokens.get(contador).getType().getType()==27) {
            contador++;
            return;
        } else {
            expresion();
        }

        

    }

    public void llaveCPrograma(){
        if(tokens.get(contador).getType().getType()==4){
            System.out.println("qwertyuiop");
        } else {
            System.out.println("ESPERADO: }");
            error(tokens.get(contador));
        }


    }

    


}