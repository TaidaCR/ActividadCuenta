package src;

import java.io.IOException;
import java.util.Scanner;
import src.logica.Cuenta;
//import src.logica.excepciones.FormatError;
//fichero = ./dat/movimientos.dat

public class Main2 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner (System.in);

        System.out.println("Introduzca el nombre del fichero");
        String fichero1 = scanner.nextLine();
        Cuenta acumulador = new Cuenta(fichero1);
        System.out.print(acumulador.imprimirMovimientos(fichero1)); 
        System.out.print("  \n");
        System.out.print(acumulador.imprimirPorCategoria(fichero1)); 

        System.out.print("  \n");
        System.out.printf("BALANCE:       %8.2f €\n", acumulador.getTotalIngresos() - acumulador.getTotalGastos());

    }


}

