package src.presentacion;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import src.logica.CategoriaGasto;
import src.logica.CategoriaIngreso;

public class Validador {
    private Scanner scanner = new Scanner(System.in);

    public LocalDate pideFecha(String mensaje, String msgError) {
        boolean fechaIncorrecta = true;
        String fecha;
        LocalDate resultado = LocalDate.parse("0001-01-01");

        while (fechaIncorrecta) {
            System.out.print(mensaje);

            fecha = scanner.nextLine();
            try{
                resultado = LocalDate.parse(fecha);
                fechaIncorrecta = false;
            } catch (DateTimeParseException err) {
                System.out.println(msgError);
            }
        }

        return resultado;
    }

    public float pideNumero(String mensaje, String msgError) {
        boolean valorIncorrecto = true;
        float valor = 0;

        while (valorIncorrecto) {
            System.out.print(mensaje);
            try {
                valor = scanner.nextFloat();
                valorIncorrecto = false;
            } catch ( InputMismatchException err ) {
                System.out.println(msgError);
            }
            scanner.nextLine();
        }

        return valor;
    }

    public CategoriaIngreso pideEnum(String mensaje, String mensajeError) {
        boolean enumIncorrecto = true;
        CategoriaIngreso valorEnum;
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s (", mensaje));
        for (CategoriaIngreso cat:CategoriaIngreso.values()) {
            String nombreCategoria = cat.name();
            sb.append(nombreCategoria);
        }
        sb.append("): ");

        mensaje = sb.toString();

        String categoria = "";
        while (enumIncorrecto) {
            System.out.print(mensaje);
            categoria = scanner.nextLine(); //EMPLEO

            for (CategoriaIngreso cat:CategoriaIngreso.values()) {
                if (categoria == cat.name()) {
                    return cat;
                }
            }


        }

        return CategoriaIngreso.valueOf(categoria);

    }
}

