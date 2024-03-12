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
                    System.out.print("Cantidad: ");
                    cantidad = scanner.nextFloat();
                    scanner.nextLine();
                    acumulador.ingresar(cantidad);

                    break;
                case "G":
                    System.out.print("Cantidad: ");
                    cantidad = scanner.nextFloat();
                    scanner.nextLine();
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
        System.out.printf("Ingresos: %15.2f Euros\n", acumulador.getTotalIngresos());
        System.out.printf("Gastos..: %15.2f Euros\n", acumulador.getTotalGastos());
        System.out.printf("Balance.: %15.2f Euros\n", acumulador.getTotalIngresos() - acumulador.getTotalGastos());
        System.out.printf("Saldo...: %15.2f Euros\n", acumulador.getSaldo());

        scanner.close();
    }
}
