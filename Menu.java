package edu.arscompile.utilidades;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import edu.arscompile.scanner.Lexer;
import edu.arscompile.utilidades.Excentricidades;
import edu.arscompile.modelos.Token; // para evitar confusiones
import edu.arscompile.parser.Parser;

public class Menu {

    public Menu(){

    }

    private static Menu instancia = new Menu();
    
    public static Menu getInstancia() {
        if(instancia == null) {
            instancia = new Menu();
        }
        return instancia;
    }
    
    public void clasificador(String[] argumentos) {
        if(argumentos.length > 0) { //Verifica que venga al menos 1 parámetro
            switch(argumentos[0]) {
                case "-o":
                    output(argumentos);
                    break;
                case "-target":
                    target(argumentos);
                    break;
                case "-opt":
                    opt(argumentos);
                    break;
                case "-debug":
                    debug(argumentos);
                    break;
                case "-h":
                    help();
                    break;
                case "-help":
                    help();
                    break;
                case "-e":
                    System.out.println("THE END...");
                    break;
                case "-end":
                    System.out.println("THE END...");
                    break;
                case "end":
                    System.out.println("THE END...");
                    break;
                default:
                    System.out.println("No se especific\u00f3 un par\u00e1metro v\u00e1lido...");
                    help();



            }
        } else {
            System.out.println("No se especific\u00f3 un par\u00e1metro...");
            help();
        }
        
    }

    public void output(String[] outename) {

    }

    public void target(String[] stage) {
        if(stage[1].equals("scan")) {
            Lexer.getInstancia().cargarPrograma(stage[2], false);
        } else if (stage[1].equals("parse")){
            Parser.getInstancia().asignarTokens(false);            
        } else if(stage[1].equals("all")){
            Lexer.getInstancia().cargarPrograma(stage[2], false);
            Parser.getInstancia().setSemantic(true);
            Parser.getInstancia().setCodigo(true);
            Parser.getInstancia().asignarTokens(false);
            System.out.println();
            System.out.println();
            System.out.println("\t\t\t\t\t\t\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557");
            System.out.println("\t\t\t\t\t\t\u2551 Etapa: CoDeGeN  \u2551");
            System.out.println("\t\t\t\t\t\t\u255A\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255D");
            System.out.println();
            System.out.println(Excentricidades.BLACK+ Excentricidades.GREEN_BACKGROUND + "resultados.asm" + Excentricidades.RESET);
        } else if(stage[1].equals("semantic")){
            Lexer.getInstancia().cargarPrograma(stage[2], false);
            Parser.getInstancia().setSemantic(true);
            Parser.getInstancia().asignarTokens(false);
        } else if(stage[1].equals("irt")){
            Lexer.getInstancia().cargarPrograma(stage[2], false);
            Parser.getInstancia().setSemantic(true);
            Parser.getInstancia().setCodigo(true);
            Parser.getInstancia().asignarTokens(false);
        } else if(stage[1].equals("codegen")){
            Lexer.getInstancia().cargarPrograma(stage[2], false);
            Parser.getInstancia().setSemantic(true);
            Parser.getInstancia().setCodigo(true);
            Parser.getInstancia().asignarTokens(false);
            System.out.println();
            System.out.println();
            System.out.println("\t\t\t\t\t\t\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557");
            System.out.println("\t\t\t\t\t\t\u2551 Etapa: CoDeGeN  \u2551");
            System.out.println("\t\t\t\t\t\t\u255A\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255D");
            System.out.println();
            System.out.println(Excentricidades.BLACK+ Excentricidades.GREEN_BACKGROUND + "resultados.asm" + Excentricidades.RESET);
        } else {
            System.out.println("COMANDO INCORRECTO");
        }

    }

    public void opt(String[] stage) {

    }

    public void debug(String[] stage) {
        if(stage[1].equals("scan")) {
            Lexer.getInstancia().cargarPrograma(stage[2], true);
        } else if (stage[1].equals("parse")){
            Parser.getInstancia().asignarTokens(true);            
        } else if(stage[1].equals("all")){
            Lexer.getInstancia().cargarPrograma(stage[2], true);
            Parser.getInstancia().setSemantic(true);
            Parser.getInstancia().setCodigo(true);
            Parser.getInstancia().asignarTokens(true);
            System.out.println();
            System.out.println();
            System.out.println("\t\t\t\t\t\t\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557");
            System.out.println("\t\t\t\t\t\t\u2551 Etapa: CoDeGeN  \u2551");
            System.out.println("\t\t\t\t\t\t\u255A\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255D");
            System.out.println();
            System.out.println(Excentricidades.BLACK+ Excentricidades.GREEN_BACKGROUND + "resultados.asm" + Excentricidades.RESET);
        } else if(stage[1].equals("semantic")){
            Lexer.getInstancia().cargarPrograma(stage[2], true);
            Parser.getInstancia().setSemantic(true);
            Parser.getInstancia().asignarTokens(true);

        } else if(stage[1].equals("irt")){
            Lexer.getInstancia().cargarPrograma(stage[2], true);
            Parser.getInstancia().setSemantic(true);
            Parser.getInstancia().setCodigo(true);
            Parser.getInstancia().asignarTokens(true);
        } else if(stage[1].equals("codegen")){
            Lexer.getInstancia().cargarPrograma(stage[2], true);
            Parser.getInstancia().setSemantic(true);
            Parser.getInstancia().setCodigo(true);
            Parser.getInstancia().asignarTokens(true);
            System.out.println();
            System.out.println();
            System.out.println("\t\t\t\t\t\t\u2554\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2557");
            System.out.println("\t\t\t\t\t\t\u2551 Etapa: CoDeGeN  \u2551");
            System.out.println("\t\t\t\t\t\t\u255A\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u2550\u255D");
            System.out.println();
            System.out.println();
            System.out.println(Excentricidades.BLACK+ Excentricidades.GREEN_BACKGROUND + "resultados.asm" + Excentricidades.RESET);
        } else {
            System.out.println("COMANDO INCORRECTO");
        }


    }

    public void help() {
        System.out.println();
        System.out.println("java ArsCompile [option] <filename>");
        System.out.println();
        System.out.println("OPCI\u00d3N \t\t\t\t DESCRIPCI\u00d3N");
        System.out.println("-o <outname> \t\tEscribir el output a <outname> \n"
        + "-target <stage> \t<stage> es uno de los siguientes"
        + " elementos: scan, parse, ast,"
        + " semantic, irt, codegen");
        System.out.println("-debug <stage> \t\tImprime informaci\u00f3n de debugging \n"
        + "\n\n\n");

        System.out.print("Mi computadora> java ArsCompile "); //Crea línea para simular cmd
        Scanner leer = new Scanner(System.in);
        String args = leer.nextLine();
        String[] argumentos = args.split(" ");
        clasificador(argumentos); //regresa a evaluar la expresión

    }

}