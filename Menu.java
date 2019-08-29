package edu.arscompile.utilidades;

import java.util.Scanner;
import java.util.Arrays;
import edu.arscompile.lexer.Lexer;

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
                case "debug":
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
            Lexer.getInstancia().cargarPrograma(stage[2]);
        }

    }

    public void opt(String[] stage) {

    }

    public void debug(String[] stage) {

    }

    public void help() {
        System.out.println();
        System.out.println("java ArsCompile [option] <filename>");
        System.out.println();
        System.out.println("OPCI\u00d3N \t\t\t\t DESCRIPCI\u00d3N");
        System.out.println("-o <outname> \t\tEscribir el output a <outname> \n"
        + "-target <stage> \t<stage> es uno de los siguientes"
        + " elementos: scan, parse, ast,"
        + " semantic, irt, codegen\n\n\n");

        System.out.print("Mi computadora> java ArsCompile "); //Crea línea para simular cmd
        Scanner leer = new Scanner(System.in);
        String args = leer.nextLine();
        String[] argumentos = args.split(" ");
        clasificador(argumentos); //regresa a evaluar la expresión

    }

}