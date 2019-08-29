package edu.arscompile;

import edu.arscompile.utilidades.Menu;
import edu.arscompile.utilidades.Excentricidades;

public class ArsCompile {
    public static void main (String [] args) {
        // Método principal
        Excentricidades.getInstancia().encabezado();
        Menu.getInstancia().clasificador(args);
    } 
} 