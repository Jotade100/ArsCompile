import java.util.Scanner;

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
                    System.out.println("No se especificó un parámetro válido...");
                    help();



            }
        } else {
            System.out.println("No se especificó un parámetro...");
            help();
        }
        
    }

    public void output(String[] outename) {

    }

    public void target(String[] stage) {

    }

    public void opt(String[] stage) {

    }

    public void debug(String[] stage) {

    }

    public void help() {
        System.out.print("Mi computadora> java ArsCompile "); //Crea línea para simular cmd
        Scanner leer = new Scanner(System.in);
        String args = leer.nextLine();
        String[] argumentos = args.split(" ");
        clasificador(argumentos); //regresa a evaluar la expresión

    }

}