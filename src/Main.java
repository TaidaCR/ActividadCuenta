package src;

import java.util.Scanner;

import src.entities.Cuenta;

public class Main {
    public static void main(String[] args) {
        Cuenta acumulador = new Cuenta();
        boolean seguirEnBucle = true;
        Scanner scanner = new Scanner(System.in);
        

        while (seguirEnBucle) {
            System.out.print("Quiere hacer Ingreso / Gasto / Salir?");
            String respuesta = scanner.nextLine();
            float cantidad;

            switch (respuesta) {
                case "I":
                    cantidad = scanner.nextFloat();
                    acumulador.ingresar(cantidad);
                    break;
                case "G":
                    cantidad = scanner.nextFloat();
                    acumulador.gastar(cantidad);
                    break;
                default: // Si no es I G o S habra que volver a pedir
                    seguirEnBucle = false;

            }
        }

        /*
         * Imprimir Total ingresos
         * Imprimir Total gastos
         * Imprimir Balance
         */
        System.out.println("Ingresos:");
    }
}
