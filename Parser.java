package edu.arscompile.parser;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.arscompile.modelos.Token;
import edu.arscompile.modelos.Tipo;
import edu.arscompile.modelos.Objeto;
import edu.arscompile.modelos.Simbolo;
import edu.arscompile.modelos.CeldaParser;
import edu.arscompile.utilidades.EscritorDeArchivo;
import edu.arscompile.utilidades.LectorDeArchivo;
import edu.arscompile.semantic.Semantico;
import edu.arscompile.irt.Irt;

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

    List<Tipo> tipos = new ArrayList<>();

    Objeto cabeza = new Objeto();


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
    
public void crearTipos(){
    tipos.add(new Tipo("Programa"));
    tipos.add(new Tipo("FieldDec"));
    tipos.add(new Tipo("FieldArrayDec")); // para determinar si es o no array
    tipos.add(new Tipo("MethodDec"));
    tipos.add(new Tipo("ParamDec"));
    tipos.add(new Tipo("Block"));
    tipos.add(new Tipo("Variable"));
    tipos.add(new Tipo("Statement"));
    tipos.add(new Tipo("ReturnStatement"));
    tipos.add(new Tipo("LocationStatement"));
    tipos.add(new Tipo("ArrayLocationStatement"));// para determinar si es o no array
    tipos.add(new Tipo("Statement"));
    tipos.add(new Tipo("ArrayLocationExpresion"));
    tipos.add(new Tipo("LocationExpresion"));
    tipos.add(new Tipo("MethodCallExpresion"));
    tipos.add(new Tipo("CalloutExpresion"));
    tipos.add(new Tipo("Expresion"));
    tipos.add(new Tipo("MethodCall"));
    tipos.add(new Tipo("CalloutArg"));
    tipos.add(new Tipo("CalloutStatement"));
    tipos.add(new Tipo("IfStatement"));
    tipos.add(new Tipo("ForStatement"));
    tipos.add(new Tipo("BreakStatement"));
    tipos.add(new Tipo("ContinueStatement"));
    tipos.add(new Tipo("AssignOp"));
    tipos.add(new Tipo("BinOp"));
    tipos.add(new Tipo("RelOp"));
    tipos.add(new Tipo("ArithOp"));
    tipos.add(new Tipo("EqOp"));
    tipos.add(new Tipo("CondOp"));
    tipos.add(new Tipo("BoolLiteral"));
    tipos.add(new Tipo("IntLiteral"));
    tipos.add(new Tipo("CharLiteral"));
    tipos.add(new Tipo("StringLiteral"));

}

public Tipo buscarTipo(String nombre) {
    for (Tipo var : tipos) {
        if(var.getNombre().equalsIgnoreCase(nombre)) {
            return var;
        }
    }
    return new Tipo("Error");
}


public void barraDeProceso(){
    char[] animationChars = new char[]{'|', '/', '-', '\\'};
    for (int i = 0; i <= 100; i++) {
        System.out.print("Procesando: " + i + "% " + animationChars[i % 4] + "\r");

        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


public void asignarTokens(boolean debug) {
        System.out.println("\t\t\t\t\t\t\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557");
        System.out.println("\t\t\t\t\t\t\u2551 Etapa: PaRsEr \u2551");
        System.out.println("\t\t\t\t\t\t\u255A\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255D");
        
        crearTipos();
        getInstancia().tokens = LectorDeArchivo.getInstancia().leerTokens("resultadosScanner");
        Parser.getInstancia().analizarTokens();

        if(!tokens.isEmpty()){ barraDeProceso();}
        System.out.println("FIN <AN\u00c1LISIS DE PARSING>");
        if(debug){
            boolean largo = false;
            System.out.println("\u00BFDesea mostrar un \u00e1rbol m\u00e1s complejo y detallado? (Puede distorsionar la vista del grafo) [S/*]");
            Scanner scan= new Scanner(System.in);
            String text= scan.nextLine();
            if(text.equalsIgnoreCase("s")) {
                largo =true;
            }
            recorrerArbolParseo(cabeza, 0, largo);
        }
        System.out.println();
        System.out.println("\t\t\t\t\t\t\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557");
        System.out.println("\t\t\t\t\t\t\u2551 Etapa: SeMaNtIc \u2551");
        System.out.println("\t\t\t\t\t\t\u255A\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255D");
        
        Semantico semantico = new Semantico(cabeza);
        semantico.crearTablaSimbolos(debug);
        semantico.unicidad();
        semantico.chequeoMetodoMain();
        semantico.dandoClaseAExpresiones(cabeza);
        semantico.chequeandoExpresionesIf(cabeza);
        semantico.chequeandoExpresionesFor(cabeza);
        semantico.chequeandoLocalizacionesArrayYStatement(cabeza);
        semantico.chequeoNumeroArgumentosMetodo(cabeza);
        semantico.asignarTipoReturnStatement(cabeza);
        semantico.chequeoReturn(cabeza);
        semantico.comprobacionBreakContinueEnFor(cabeza);


        //recorrerArbolParseo(cabeza, 0, false); //sirve para debug posterior
        //Irt irt = new Irt();
        for (Simbolo var : semantico.getTablaSimbolos()) {
            if(!var.getObjeto().getType().getNombre().contains("Method")){
                Irt.getInstancia().construirVariables(var.getObjeto());
            }
            
        }
        Irt.getInstancia().recorrerArbolParseo(cabeza);
        Irt.getInstancia().imprimirInstrucciones();
        EscritorDeArchivo.getInstancia().escribirASM("resultado", Irt.getInstancia().getRaiz());

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
            cabeza.setType(buscarTipo("Programa"));
            cabeza.setToken(tokens.get(contador));
            contador++;
            programa();
        } else {error(tokens.get(contador));}

    }

    public void programa(){
        if(tokens.get(contador).getType().getType()==2){
            cabeza.setToken(tokens.get(contador));
            contador++;
            llaveAPrograma();
        } else {error(tokens.get(contador));}
    }

    public void llaveAPrograma(){
        if(tokens.get(contador).getType().getType()==3){
            cabeza.setToken(tokens.get(contador));
            contador++;
            fieldDecl();
            methodDec();
            llaveCPrograma();
        } else {error(tokens.get(contador));}
    }

    public void fieldDecl(){
        System.out.println("INICIO <AN\u00c1LISIS DE PARSEO>");
        // estructura do-while que equivale a un go-to
        
        boolean goTo = true;
        do {
            Objeto actual = new Objeto();
            switch(tokens.get(contador).getType().getType()){
                case 9:
                    actual.setToken(tokens.get(contador));
                    contador++;
                    boolean goTo2 = true;
                    do {
                        
                        if(tokens.get(contador).getType().getType()==29){
                            Objeto nuevo = clonar(actual);
                            nuevo.setToken(tokens.get(contador));
                            // if(actual.getTokens().size()==1){
                            //     Objeto nuevo = clonar(actual);
                            //     nuevo.setToken(tokens.get(contador));
                            //     nuevo.setType(buscarTipo("FieldDec"));
                            //     cabeza.setObjeto(nuevo);
                            // } else {
                            //     Objeto nuevo = clonar(actual);
                            //     nuevo.getTokens().add(1, tokens.get(contador));
                            //     nuevo.setType(buscarTipo("FieldDec"));
                            //     cabeza.setObjeto(nuevo);
                            // }
                            
                            contador++;
                        
                            switch(tokens.get(contador).getType().getType()){
                                case 8: //punto y coma
                                    //nuevo.setToken(tokens.get(contador)); //no considero necesario agregar el ;
                                    nuevo.setClase(nuevo.getTokens().get(0).getValue().toString());
                                    nuevo.setType(buscarTipo("FieldDec"));
                                    cabeza.setObjeto(nuevo);
                                    contador++;
                                    //nada vaya a goTo
                                    goTo2 = false; // se ha acabado
                                    break;
                                case 5: // coma
                                    nuevo.setClase(nuevo.getTokens().get(0).getValue().toString());
                                    nuevo.setType(buscarTipo("FieldDec"));
                                    cabeza.setObjeto(nuevo);
                                    contador++;
                                    //nada vaya a goTo
                                    break;
                                case 6: // corchete
                                    nuevo.setToken(tokens.get(contador));
                                    contador++;
                                    if(tokens.get(contador).getType().getType()==28){
                                        nuevo.setToken(tokens.get(contador));
                                        contador++;
                                    } else {goTo = false; goTo2 = false; error(tokens.get(contador));}
                                    if(tokens.get(contador).getType().getType()==7){
                                        nuevo.setToken(tokens.get(contador));
                                        contador++;
                                    } else {goTo = false; goTo2 = false; error(tokens.get(contador));}
                                    if(tokens.get(contador).getType().getType()==5){
                                        nuevo.setClase(nuevo.getTokens().get(0).getValue().toString());
                                        nuevo.setType(buscarTipo("FieldArrayDec"));
                                        cabeza.setObjeto(nuevo);
                                        contador++; // coma
                                        goTo2 = true;
                                    } else if(tokens.get(contador).getType().getType()==8) { // punto y coma
                                        nuevo.setClase(nuevo.getTokens().get(0).getValue().toString());
                                        nuevo.setType(buscarTipo("FieldArrayDec"));
                                        cabeza.setObjeto(nuevo);
                                        contador++;
                                        goTo2 = false;
                                    } else {
                                        goTo2 = false;
                                        goTo = false;
                                        error(tokens.get(contador));}

                                    break; // nada vaya a goTo
                                case 12: //método
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
        //System.out.println("FIN <DECLARACIÓN DE CAMPOS>");
        
    }

    public void methodDec(){
        boolean terminarPeticionMetodos = true;
        while(terminarPeticionMetodos){
            
            if((tokens.get(contador).getType().getType()==9) || (tokens.get(contador).getType().getType()==10)){ //integer, boolean o void
                Objeto actual = new Objeto();
                actual.setToken(tokens.get(contador));
                actual.setClase(actual.getTokens().get(0).getValue().toString());
                contador++;
                if((tokens.get(contador).getType().getType()==29)){ // id
                    actual.setToken(tokens.get(contador));
                    contador++;
                    if((tokens.get(contador).getType().getType()==12)){ //paréntesis de apertura
                        actual.setToken(tokens.get(contador));
                        contador++;
                        switch(tokens.get(contador).getType().getType()){
                            case 13: // paréntesis de cierre
                                actual.setToken(tokens.get(contador));
                                actual.setType(buscarTipo("MethodDec"));
                                //cabeza.setObjeto(actual);
                                contador++;
                                block(actual);
                                break;
                            case 9: //int o boolean
                                
                                boolean bandera = true;
                                while(bandera) {
                                    if(tokens.get(contador).getType().getType()==9) { //type
                                        Objeto parametro = new Objeto();
                                        parametro.setClase(tokens.get(contador).getValue().toString());
                                        parametro.setToken(tokens.get(contador));
                                        contador++;
                                        if((tokens.get(contador).getType().getType()==29)){ //id
                                            parametro.setToken(tokens.get(contador));
                                            contador++;
                                        } else {
                                            System.out.println("ESPERADO: ID");
                                            error(tokens.get(contador));  
                                        }
    
                                        if(tokens.get(contador).getType().getType()==5) { //coma
                                            parametro.setType(buscarTipo("ParamDec"));
                                            actual.setObjeto(parametro); //lo pone como hijo
                                            contador++;
                                        } else if (tokens.get(contador).getType().getType()==13) { //paréntesis de cierre
                                            actual.setType(buscarTipo("MethodDec"));
                                            parametro.setType(buscarTipo("ParamDec"));
                                            actual.setObjeto(parametro); //lo pone como hijo
                                            contador++;
                                            bandera = false;
                                            block(actual);
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
    
                    cabeza.setObjeto(actual);    
                } else {error(tokens.get(contador));}
                
    
            } else if(tokens.get(contador).getType().getType()==4) { //llave de cierre
                //no hace nada, simplemente ya no hay métodos por declarar
                terminarPeticionMetodos = false;

            } else {error(tokens.get(contador));}
    
        }
        
    }

    public void block(Objeto padre){
        // System.out.println("BLOCK");
        Objeto actual = new Objeto();
        if(tokens.get(contador).getType().getType()==3){ //llave de apertura
            actual.setToken(tokens.get(contador));
            contador++;
            boolean bandera = true;
            while(bandera) {
                    Objeto elemento = new Objeto();
                    switch(tokens.get(contador).getType().getType()){
                        case 4: // llave de cierre
                            actual.setToken(tokens.get(contador));
                            actual.setType(buscarTipo("Block"));
                            padre.setObjeto(actual);
                            contador++;
                            bandera = false;
                            break;
                /* --------------declaración de variables -------------------------------- *///
                        case 9: //type --entra a var declaration
                                //System.out.println("VAR DECLARATION");
                                
                                if(tokens.get(contador).getType().getType()==9) { //type
                                    elemento.setClase(tokens.get(contador).getValue().toString());
                                    elemento.setToken(tokens.get(contador));
                                    contador++;
                                    boolean bandera2 = true;
                                    while (bandera2){
                                        if((tokens.get(contador).getType().getType()==29)){ //id
                                            elemento.setToken(tokens.get(contador));
                                            contador++;
                                        } else {
                                            System.out.println("ESPERADO: ID");
                                            error(tokens.get(contador));  
                                        }

                                        if(tokens.get(contador).getType().getType()==5) { //coma
                                            elemento.setType(buscarTipo("Variable"));
                                            actual.setObjeto(elemento);
                                            contador++;
                                        
                                        } else if(tokens.get(contador).getType().getType()==8) { //punto y coma
                                                elemento.setType(buscarTipo("Variable"));    
                                                actual.setObjeto(elemento);
                                                contador++;
                                                bandera2 = false;
                                            } else {
                                                System.out.println("ESPERADO: , o ;");
                                                error(tokens.get(contador));
                                            }
                                    }    
                                        

                                    
                                } else {
                                    System.out.println("ESPERADO: TYPE");
                                    error(tokens.get(contador));
                                    
                                }
                                
                            
                            
                            break;
                        // espacio para statement
                        /* --------------statement -------------------------------- *///
                        case 29: //id
                            statement(actual);
                            break;
                        case 17: //break
                            statement(actual);
                            break;
                        case 18: //continue
                            statement(actual);
                            break;
                        case 11: //if
                            statement(actual);
                            break;
                        case 20: //callout
                            statement(actual);
                            break;
                        case 15: //for
                            statement(actual);
                            break;
                        case 16: //return
                            statement(actual);
                            break;
                        case 3: //bloque
                            block(actual);
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

    public void statement(Objeto padre){
        boolean recursivo = true;
        while(recursivo){
            Objeto actual = new Objeto();
            recursivo = false;
            /// hay que volverlo recursivo
            //System.out.println("STATEMENT");
            switch (tokens.get(contador).getType().getType()) {
                case 15: //for
                    actual.setToken(tokens.get(contador));
                    contador++;
                    if(tokens.get(contador).getType().getType() == 29){ //id
                        actual.setToken(tokens.get(contador));
                        expresion(actual);
                        //contador++;
                    } else {
                        System.out.println("ESPERABA ID");
                        error(tokens.get(contador));
                    }
                    if(tokens.get(contador).getType().getType() == 32){ //=
                        actual.setToken(tokens.get(contador));
                        contador++;
                    } else {
                        System.out.println("ESPERABA =");
                        error(tokens.get(contador));
                    }
                    expresion(actual);
                    if(tokens.get(contador).getType().getType() == 5){ //coma
                        actual.setToken(tokens.get(contador));
                        contador++;
                    } else {
                        System.out.println("ESPERABA: ,");
                        error(tokens.get(contador));
                    }
                    expresion(actual);
                    block(actual);
                    actual.setType(buscarTipo("ForStatement"));
                    padre.setObjeto(actual);

                    break;
                case 16: //return expresión;
                    actual.setToken(tokens.get(contador));
                    contador++;
                    if(tokens.get(contador).getType().getType() == 8){ //punto y coma
                        actual.setType(buscarTipo("ReturnStatement"));
                        padre.setObjeto(actual);
                        contador++;
                        break;
                    } 
                    
                    expresion(actual);
                    if(tokens.get(contador).getType().getType() == 8){ //punto y coma
                        actual.setType(buscarTipo("ReturnStatement"));
                        padre.setObjeto(actual);
                        contador++;
                    } else {
                        System.out.println("FALTA ;");
                        error(tokens.get(contador));
                    }
                    break;

                case 17: //break;
                    actual.setToken(tokens.get(contador));
                    contador++;
                    if(tokens.get(contador).getType().getType() == 8){ //punto y coma
                        actual.setType(buscarTipo("BreakStatement"));
                        padre.setObjeto(actual);
                        contador++;
                    } else {
                        System.out.println("FALTA ;");
                        error(tokens.get(contador));
                    }
                    break;
                case 18: //continue;
                    actual.setToken(tokens.get(contador));
                    contador++;
                    if(tokens.get(contador).getType().getType() == 8){ //punto y coma
                        actual.setType(buscarTipo("ContinueStatement"));
                        padre.setObjeto(actual);
                        contador++;
                    } else {
                        System.out.println("FALTA;");
                        error(tokens.get(contador));
                    }
                    break; 
                case 29: //id
                    actual.setToken(tokens.get(contador));
                    contador++;
                    //location
                    switch (tokens.get(contador).getType().getType()) {
                        //<location> assign_op expr
                        case 19: //asign_op
                            actual.setToken(tokens.get(contador));
                            contador++;
                            expresion(actual);
                            if(tokens.get(contador).getType().getType()==8){
                                contador++;
                                actual.setType(buscarTipo("LocationStatement"));
                                padre.setObjeto(actual);
                                //nice terminó statement
                            } else {
                                System.out.println("ESPERADO: ;");
                                error(tokens.get(contador));
                            }
                            break;
                        case 32: //asign_op =
                            actual.setToken(tokens.get(contador));
                            contador++;
                            expresion(actual);
                            if(tokens.get(contador).getType().getType()==8){
                                //nice terminó statement
                                actual.setType(buscarTipo("LocationStatement"));
                                padre.setObjeto(actual);
                                contador++;
                                
                            } else {
                                System.out.println("ESPERADO: ;");
                                error(tokens.get(contador));
                            }
                            break;
                        //<location>
                        case 6:
                            actual.setToken(tokens.get(contador));
                            contador++;
                            expresion(actual);
                            if(tokens.get(contador).getType().getType()==7){
                                actual.setToken(tokens.get(contador));
                                contador++;
                            } else {System.out.println("ESPERADO: ]");error(tokens.get(contador));}
                            if((tokens.get(contador).getType().getType()==19) ||(tokens.get(contador).getType().getType()==32)){
                                actual.setToken(tokens.get(contador));
                                contador++;
                            } else {System.out.println("ESPERADO: ASIGNATION_OPERATOR"); error(tokens.get(contador));}
                            expresion(actual);
                            //System.out.println(actual.getHijos().size());
                            if(tokens.get(contador).getType().getType()==8) { //punto y coma
                                actual.setType(buscarTipo("ArrayLocationStatement")); // para determinar que este definido en scope general
                                padre.setObjeto(actual);
                                contador++;
                            } else {
                                
                                System.out.println("ESPERADO: ;"); error(tokens.get(contador));}

                            break;
                        // method call 
                        // method name ( expresión* )
                        case 12:
                            actual.setToken(tokens.get(contador));
                            contador++;
                            boolean bandera = true;
                            while(bandera){ // varias expresiones o cierre inmediato
                                if(tokens.get(contador).getType().getType()==13) { //paréntesis de cierre (carece de expresiones)
                                    actual.setToken(tokens.get(contador));
                                    contador++;
                                    bandera = false;
                                    if(tokens.get(contador).getType().getType()==8) { //punto y coma
                                        actual.setType(buscarTipo("MethodCall"));
                                        padre.setObjeto(actual);
                                        contador++;
                                        bandera = false;
                                    } else {System.out.println("ESPERADO: ;");error(tokens.get(contador));}
                                    break;
                                }
                                //System.out.println("qwertyuiop");
                                expresion(actual);
                                if(tokens.get(contador).getType().getType()==5){ //coma
                                    //System.out.println("COMA"); 
                                    actual.setToken(tokens.get(contador));
                                    contador++;
                                    //busca más expresiones

                                } else if(tokens.get(contador).getType().getType()==13) { //paréntesis de cierre
                                    actual.setToken(tokens.get(contador));
                                    contador++;
                                    bandera = false;
                                    if(tokens.get(contador).getType().getType()==8) { //punto y coma
                                        actual.setType(buscarTipo("MethodCall"));
                                        padre.setObjeto(actual);
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
                    actual.setToken(tokens.get(contador));
                    contador++;
                    if(tokens.get(contador).getType().getType()==12){ //paréntesis apertura
                        actual.setToken(tokens.get(contador));
                        contador++;
                    } else{
                        System.out.println("ESPERADO: (");
                        error(tokens.get(contador));
                    }

                    if(tokens.get(contador).getType().getType()==27){ //string literal
                        actual.setToken(tokens.get(contador));
                        contador++;
                    } else{
                        System.out.println("ESPERADO: STRING");
                        error(tokens.get(contador));
                    }

                    if(tokens.get(contador).getType().getType()==5){ //coma
                        contador++;
                        boolean bandera = true;
                        while(bandera){
                            calloutArgs(actual);
                            if(tokens.get(contador).getType().getType()==5) { //coma
                                //actual.setToken(tokens.get(contador));
                                contador++;
                            } else if(tokens.get(contador).getType().getType()==13) { //paréntesis cierre
                                actual.setToken(tokens.get(contador));
                                contador++;
                                bandera = false;
                                if(tokens.get(contador).getType().getType()==8) { //punto y coma
                                    actual.setClase("int");
                                    actual.setType(buscarTipo("CalloutStatement"));
                                    padre.setObjeto(actual);
                                    contador++;
                                    bandera = false;
                                } else {System.out.println("ESPERADO: ;"); error(tokens.get(contador));}
                            } else {System.out.println("ESPERADO: , o )");error(tokens.get(contador));}
                        }
                        

                    } else if(tokens.get(contador).getType().getType()==13){ // paréntesis de cierre
                        actual.setToken(tokens.get(contador));
                        contador++;
                        if(tokens.get(contador).getType().getType()==8) { //punto y coma
                            contador++;
                            actual.setType(buscarTipo("CalloutStatement"));
                            padre.setObjeto(actual);
                                    
                        } else {System.out.println("ESPERADO: ;");error(tokens.get(contador));}
                    } else{
                        System.out.println("ESPERADO: , o )");
                        error(tokens.get(contador));
                    }

                    
                    break;

                case 11: // if ( expr) block y puede no haber else block
                    actual.setToken(tokens.get(contador));
                    contador++;
                    if(tokens.get(contador).getType().getType()==12) { //parentesis apertura
                        actual.setToken(tokens.get(contador));
                        contador++;         
                    } else {System.out.println("ESPERADO: (");error(tokens.get(contador));}
                    expresion(actual);
                    if(tokens.get(contador).getType().getType()==13) { //parentesis cierre
                        actual.setToken(tokens.get(contador));
                        contador++;                               
                    } else {System.out.println("ESPERADO: )");error(tokens.get(contador));}
                    block(actual);
                    if(tokens.get(contador).getType().getType()==14) { // else
                        actual.setToken(tokens.get(contador));
                        contador++;
                        block(actual);

                    } 
                    actual.setType(buscarTipo("IfStatement"));
                    padre.setObjeto(actual);
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


    public void expresion(Objeto padre){
        Objeto actual = new Objeto();
        //System.out.println("EXPRESIÓN" + tokens.get(contador).getValue());
        switch (tokens.get(contador).getType().getType()) {
            case 29: //id corresponderá a location y method_call1
                actual.setToken(tokens.get(contador));
                contador++;
                if(tokens.get(contador).getType().getType() == 6){ // [ caso id[expr]
                    actual.setToken(tokens.get(contador));
                    contador++;
                    expresion(actual);
                    
                    if(tokens.get(contador).getType().getType() == 7){ //] // se corrigio el id del case antes era 6
                        actual.setToken(tokens.get(contador));
                        contador++;
                        actual.setType(buscarTipo("ArrayLocationExpresion"));
                        //actual.setType(buscarTipo("LocationExpresionArray"));
                        //padre.setObjeto(actual); //Todavía no, puede ser una expresión anidada
                    } else {
                        System.out.println("ESPERADO: ]");error(tokens.get(contador));
                    }
                } else if(tokens.get(contador).getType().getType() ==12){ // ( caso id(expr*)
                    actual.setToken(tokens.get(contador));
                    contador++;
                    //System.out.println("caso id(expr*)");
                    boolean bandera = true;
                    if (tokens.get(contador).getType().getType() == 13) { //cierre de una vez
                        actual.setToken(tokens.get(contador));
                        actual.setType(buscarTipo("MethodCallExpresion"));
                        //padre.setObjeto(actual); //Todavía no, puede ser una expresión anidada
                        contador++;
                        //break;
                        bandera = false;
                    }
                    //boolean bandera = true;
                    while(bandera) {
                        expresion(actual);
                        if (tokens.get(contador).getType().getType() == 5) { // coma
                            //System.out.println("COMA " + bandera);
                            contador++;
                        }  else if(tokens.get(contador).getType().getType() == 13){ //cierre )
                            actual.setToken(tokens.get(contador));
                            actual.setType(buscarTipo("MethodCallExpresion"));
                            //padre.setObjeto(actual); //Todavía no, puede ser una anidación
                            contador++;
                            bandera = false;
                            break;
                        } else {
                            System.out.println("ESPERADO: , o )");error(tokens.get(contador));
                        }
                        //System.out.println(bandera);
                    }
                    
                    
                    
                }
                if(actual.getType().getNombre().equals("NULL")){actual.setType(buscarTipo("LocationExpresion"));}
                //System.out.println("VARIABLE COMÚN" + tokens.get(contador).getValue());
                if(esBinOp()) {
                    Objeto actualDosPuntoCero = new Objeto();
                    actualDosPuntoCero.setType(buscarTipo("Expresion"));
                    actualDosPuntoCero.setObjeto(actual);
                    binOp(actualDosPuntoCero);
                    expresion(actualDosPuntoCero);
                    padre.setObjeto(actualDosPuntoCero);
                } else {
                    padre.setObjeto(actual);
                }

                break;
            case 20: //callout corresponde a method_call2
                actual.setToken(tokens.get(contador));
                contador++;
                if(tokens.get(contador).getType().getType() ==12) { // paréntesis (
                    actual.setToken(tokens.get(contador));
                    contador++;  
                } else {
                    System.out.println("ESPERADO: (");error(tokens.get(contador));
                }
                if(tokens.get(contador).getType().getType() ==27) { // string literal
                    actual.setToken(tokens.get(contador));
                    contador++;  
                } else {
                    System.out.println("ESPERADO: STRING_LITERAL");error(tokens.get(contador));
                }
                if(tokens.get(contador).getType().getType() ==5) { // coma
                    contador++; 
                    boolean bandera= true;
                    while(bandera){
                        calloutArgs(actual);
                        if(tokens.get(contador).getType().getType() ==5) { //coma 
                            contador++;
                        } else if (tokens.get(contador).getType().getType() ==13) {
                            actual.setToken(tokens.get(contador));
                            actual.setClase("int");
                            actual.setType(buscarTipo("CalloutExpresion"));
                            bandera = false;
                            contador++;
                            break;
                        }
                    }
                    
                } else if(tokens.get(contador).getType().getType() ==13){ //parentesis de cierre
                    actual.setToken(tokens.get(contador));
                    actual.setType(buscarTipo("CalloutExpresion"));
                    contador++;

                } else {System.out.println("ESPERADO: )");error(tokens.get(contador));}
                
                if(esBinOp()) {
                    Objeto actualDosPuntoCero = new Objeto();
                    actualDosPuntoCero.setType(buscarTipo("Expresion"));
                    actualDosPuntoCero.setObjeto(actual);
                    binOp(actualDosPuntoCero);
                    expresion(actualDosPuntoCero);
                    padre.setObjeto(actualDosPuntoCero);
                } else {
                    padre.setObjeto(actual);
                }

                break;
        /*------------------------------- LITERAL ---------------------------------- */
            case 25: //boolean
                actual.setType(buscarTipo("BoolLiteral"));
                actual.setClase("boolean");
                actual.setToken(tokens.get(contador));
                contador++;
                if(esBinOp()) {
                    Objeto actualDosPuntoCero = new Objeto();
                    actualDosPuntoCero.setType(buscarTipo("Expresion"));
                    actualDosPuntoCero.setObjeto(actual);
                    binOp(actualDosPuntoCero);
                    expresion(actualDosPuntoCero);
                    padre.setObjeto(actualDosPuntoCero);
                } else {
                    padre.setObjeto(actual);
                }
                break;
            case 26: //char
                actual.setType(buscarTipo("CharLiteral"));
                actual.setClase("int");
                actual.setToken(tokens.get(contador));
                contador++;
                if(esBinOp()) {
                    Objeto actualDosPuntoCero = new Objeto();
                    actualDosPuntoCero.setType(buscarTipo("Expresion"));
                    actualDosPuntoCero.setObjeto(actual);
                    binOp(actualDosPuntoCero);
                    expresion(actualDosPuntoCero);
                    padre.setObjeto(actualDosPuntoCero);
                } else {
                    padre.setObjeto(actual);
                }
                break;
            case 28: //int literal
                actual.setType(buscarTipo("IntLiteral"));
                actual.setClase("int");
                actual.setToken(tokens.get(contador));
                contador++;
                if(esBinOp()) {
                    Objeto actualDosPuntoCero = new Objeto();
                    actualDosPuntoCero.setType(buscarTipo("Expresion"));
                    actualDosPuntoCero.setObjeto(actual);
                    binOp(actualDosPuntoCero);
                    expresion(actualDosPuntoCero);
                    padre.setObjeto(actualDosPuntoCero);
                } else {
                    padre.setObjeto(actual);
                }
                break;
            case 30: //minus
                actual.setType(buscarTipo("Expresion"));
                //actual.setClase("int");
                actual.setToken(tokens.get(contador));
                contador++;
                expresion(actual);
                if(esBinOp()) {
                    Objeto actualDosPuntoCero = new Objeto();
                    actualDosPuntoCero.setType(buscarTipo("Expresion"));
                    actualDosPuntoCero.setObjeto(actual);
                    binOp(actualDosPuntoCero);
                    expresion(actualDosPuntoCero);
                    padre.setObjeto(actualDosPuntoCero);
                } else {
                    padre.setObjeto(actual);
                }
                break;
            case 31: // ! (exp)
                actual.setType(buscarTipo("Expresion"));
                actual.setToken(tokens.get(contador));
                contador++;
                expresion(actual);
                if(esBinOp()) {
                    Objeto actualDosPuntoCero = new Objeto();
                    actualDosPuntoCero.setType(buscarTipo("Expresion"));
                    actualDosPuntoCero.setObjeto(actual);
                    binOp(actualDosPuntoCero);
                    expresion(actualDosPuntoCero);
                    padre.setObjeto(actualDosPuntoCero);
                } else {
                    padre.setObjeto(actual);
                }
                break;
            case 12: //paréntesis apertura (
                actual.setType(buscarTipo("Expresion"));
                actual.setToken(tokens.get(contador));
                contador++;
                expresion(actual);
                if(tokens.get(contador).getType().getType() ==13) { //cierre
                    actual.setToken(tokens.get(contador));
                    contador++;
                } else {
                    System.out.println("ESPERADO: )");
                    error(tokens.get(contador));
                }
                if(esBinOp()) {
                    Objeto actualDosPuntoCero = new Objeto();
                    actualDosPuntoCero.setType(buscarTipo("Expresion"));
                    actualDosPuntoCero.setObjeto(actual);
                    binOp(actualDosPuntoCero);
                    expresion(actualDosPuntoCero);
                    padre.setObjeto(actualDosPuntoCero);
                } else {
                    padre.setObjeto(actual);
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
            case 32:// =
                return false;
            default:
                return false;
        }
    }

    public void binOp(Objeto padre){
        Objeto actual = new Objeto();
        actual.setType(buscarTipo("BinOp"));
        switch (tokens.get(contador).getType().getType()) {
            case 21:// aritmético
                actual.setType(buscarTipo("ArithOp"));
                actual.setToken(tokens.get(contador));
                padre.setObjeto(actual);
                contador++;
                break;
            case 22:// rel_op
                actual.setType(buscarTipo("RelOp"));
                actual.setToken(tokens.get(contador));
                padre.setObjeto(actual);
                contador++;
                break;
            case 23:// eq_op
                actual.setType(buscarTipo("EqOp"));
                actual.setToken(tokens.get(contador));
                padre.setObjeto(actual);
                contador++;
                break;
            case 24:// cond_op
                actual.setType(buscarTipo("CondOp"));
                actual.setToken(tokens.get(contador));
                padre.setObjeto(actual);
                contador++;
                break;
            case 30:// minus_op
                actual.setType(buscarTipo("ArithOp"));
                actual.setToken(tokens.get(contador));
                padre.setObjeto(actual);
                contador++;
                break;
            // case 31:// =
            //     actual.setType(buscarTipo("Error"));
            //     actual.setToken(tokens.get(contador));
            //     padre.setObjeto(actual);
            //     contador++;
            //     break;
            default:
                System.out.println("NO ES NINGÚN OPERADOR");
                error(tokens.get(contador));
                break;
        }
    }

    public void calloutArgs(Objeto obj){
        //System.out.println("calloutArgs");
        if(tokens.get(contador).getType().getType()==27) {
            obj.setToken(tokens.get(contador));
            contador++;
            return;
        } else {
            expresion(obj);
        }

        

    }

    public void llaveCPrograma(){
        if(tokens.get(contador).getType().getType()==4){
            cabeza.setToken(tokens.get(contador));
            contador++;
            try {
                Token extra = tokens.get(contador);
                System.out.println("YA TERMIN\u00d3 EL PROGRAMA, REVISE QUE NO HAYAN ELEMENTOS DESPU\u00c9S DE TERMINAR EL ARCHIVO");
                error(tokens.get(contador));
            } catch (Exception e) {
                //TODO: handle exception
            }
            
            

        } else {
            System.out.println("ESPERADO: }");
            error(tokens.get(contador));
        }


    }

    public Objeto clonar(Objeto objeto) {
        Objeto nuevo = new Objeto();
        nuevo.setType(objeto.getType());
        nuevo.setValue(objeto.getValue());
        nuevo.setTokens(objeto.getTokens());
        nuevo.setObjetos(objeto.getHijos());
        //objeto.imprimirToken();
        return nuevo;
    }

    public void recorrerArbolParseo(Objeto objeto, int tab, boolean largo){
        for (int i = 0; i < tab; i++) {
            System.out.print("\t");
        }
        if(largo){
            objeto.imprimirTokenBonitoLargo();
        } else {
            objeto.imprimirTokenBonitoCorto();
        }
        
        System.out.println();
        for (Objeto var : objeto.getHijos()) {
            recorrerArbolParseo(var, tab+1, largo);
        }

    }

    


}