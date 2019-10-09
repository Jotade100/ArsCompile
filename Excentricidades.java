package edu.arscompile.utilidades;

public class Excentricidades {

    public Excentricidades(){

    }

    private static Excentricidades instancia = new Excentricidades();
    
    public static Excentricidades getInstancia() {
        if(instancia == null) {
            instancia = new Excentricidades();
        }
        return instancia;
    }

    //Colores
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE

    public void encabezado() {
        System.out.println(ANSI_RED +"                                                                                                  "+ ANSI_RESET);
        System.out.println(ANSI_RED +"               `.--:.          ---..`    -::::::::::::::   `::::-`           .::::-               "+ ANSI_RESET);
        System.out.println(ANSI_RED +"               oyyyy/          oyyyy/    oyyyyyyyyyyyyyo   .yyyyyo.        `:syyyyo               "+ ANSI_RESET);
        System.out.println(ANSI_RED +"               syyyy/          oyyyy+    oyyyyo////////-   .yyyyyyy+`     -oyyyyyyo               "+ ANSI_RESET);
        System.out.println(ANSI_RED +"               syyyy/          oyyyy+    oyyyy/            .yyyyyyyys:` .+yyyyyyyyo               "+ ANSI_RESET);
        System.out.println(ANSI_RED +"               syyyy/          oyyyy+    oyyyy+````````    .yyyyysyyyyo/syyyysyyyyo               "+ ANSI_RESET);
        System.out.println(ANSI_RED +"               syyyy/          oyyyy+    oyyyyysssssss+    .yyyyy./syyyyyyyo-/yyyyo               "+ ANSI_RESET);
        System.out.println(ANSI_RED +"               syyyy/          oyyyy+    oyyyyysssssss:    .yyyyy` .+yyyys:` /yyyyo               "+ ANSI_RESET);
        System.out.println(ANSI_RED +"               +yyyyo         `syyyy:    oyyyy/```````     .yyyyy`   -os+`   /yyyyo               "+ ANSI_RESET);
        System.out.println(ANSI_RED +"               .syyyy+.`    `.oyyyyo`    oyyyy/            .yyyyy`    ``     /yyyyo               "+ ANSI_RESET);
        System.out.println(ANSI_RED +"                .oyyyyyo+++osyyyyy+`     oyyyy/            .yyyyy`           /yyyyo               "+ ANSI_RESET);
        System.out.println(ANSI_RED +"                  -osyyyyyyyyyys/.       oyyyy/            .yyyyy`           /yyyyo               "+ ANSI_RESET);
        System.out.println(ANSI_RED +"                    `.://++/:-.          :////-             ..-::`           .::-.`               "+ ANSI_RESET);
        System.out.println();
        System.out.println ("\t\t\t\t\t    Juan Diego Sique Mart\u00ednez \n\t\t\t\t\t   Geordie Josu\u00e9 Quiroa Bulnes \n\n \t\t\t\t\t Universidad Francisco Marroqu\u00edn \n\t\t\t\t\t\t   Compiladores \n \n");
    }
}