package src;

import java.time.LocalDate;
import java.util.Scanner;


import src.logica.CategoriaGasto;
import src.logica.CategoriaIngreso;
import src.logica.Cuenta;

import src.logica.Gasto;
import src.logica.Ingreso;
import src.presentacion.Validador;

public class Main {
    public static void main(String[] args) {
        Cuenta acumulador = new Cuenta();
        boolean seguirEnBucle = true;
        Scanner scanner = new Scanner(System.in);
        Validador validador = new Validador();
        

        while (seguirEnBucle) {
            System.out.println("SOY LA MODIFICACION DE JULIA");
            System.out.print("Quiere hacer Ingreso / Gasto / Salir? ");
            String respuesta = scanner.nextLine();
            float cantidad;
            String concepto;
            LocalDate fecha;
            CategoriaIngreso catIngreso;
            CategoriaGasto catGasto;

            switch (respuesta.toUpperCase()) {
                case "I":
                    cantidad = validador.pideNumero("Cantidad: ","Debe ser un numero valido");
                    
                    concepto = validador.pideConcepto("Concepto: ", "Debes introducir un concepto de", 5);

                    fecha = validador.pideFecha("Fecha: ","La fecha debe ser YYYY-MM-DD.");

                    catIngreso = validador.pideCatIngreso("Categoria Ingreso", "Elige una de las opciones");
                    
                    acumulador.ingresar(cantidad, catIngreso, fecha, concepto);
                    new Ingreso(fecha, concepto, cantidad, catIngreso).save("./data/movimientos.dat");

                    break;
                case "G":
                    
                    cantidad = validador.pideNumero("Cantidad: ","Debe ser un numero valido");

                    concepto = validador.pideConcepto("Concepto:", "Debes introducir un concepto de", 5);

                    fecha = validador.pideFecha("Fecha: ","La fecha debe ser YYYY-MM-DD.");

                    catGasto = validador.pideCatGasto("Categoria Gasto", "Elige una de las opciones");

                    acumulador.gastar(cantidad, catGasto, fecha, concepto);
                    Gasto gasto = new Gasto(fecha, concepto, cantidad, catGasto);
                    System.out.println(gasto.getFecha());
                    gasto.save("./data/movimientos.dat");

                    
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
