package src;

import java.time.LocalDate;
import java.util.Scanner;

import src.logica.CategoriaIngreso;
import src.logica.Cuenta;
import src.logica.Movimiento;
import src.presentacion.Validador;

public class Main {
    public static void main(String[] args) {
        Cuenta acumulador = new Cuenta();
        boolean seguirEnBucle = true;
        Scanner scanner = new Scanner(System.in);
        Validador validador = new Validador();
        

        while (seguirEnBucle) {
            System.out.print("Quiere hacer Ingreso / Gasto / Salir? ");
            String respuesta = scanner.nextLine();
            float cantidad;
            String concepto;
            LocalDate fecha;
            CategoriaIngreso catIngreso;


            switch (respuesta.toUpperCase()) {
                case "I":
                    cantidad = validador.pideNumero("Cantidad: ", "Debe ser un número válido.");
                    
                    System.out.print("Concepto: ");
                    concepto = scanner.nextLine();

                    fecha = validador.pideFecha("Fecha:", "La fecha debe ser YYYY-MM-DD.");
                    
                    System.out.print("Categoria: ");
                    String nombreCategoria = scanner.nextLine();
                    catIngreso = CategoriaIngreso.valueOf(nombreCategoria);

                    Movimiento mv = new Movimiento(fecha, concepto, cantidad);
                    acumulador.ingresar(cantidad, catIngreso, fecha, concepto);

                    break;
                case "G":
                    System.out.print("Cantidad: ");
                    cantidad = scanner.nextFloat();
                    scanner.nextLine();
                    //acumulador.gastar(cantidad);
                    break;
                case "S": // Si no es I G o S habra que volver a pedir
                    seguirEnBucle = false;
                    break;
                default:
                    System.out.println("Debe pulsar I, G o S");

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
