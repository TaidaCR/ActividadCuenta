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

    public CategoriaIngreso pideCatIngreso(String mensaje, String mensajeError) {
        boolean enumIncorrecto = true;
        StringBuilder sb = new StringBuilder();
        CategoriaIngreso resultado = null;

        sb.append(String.format("%s (", mensaje));
        for (CategoriaIngreso cat:CategoriaIngreso.values()) {
            String nombreCategoria = cat.name();
            sb.append(nombreCategoria);
        }
        sb.append("): ");

        mensaje = sb.toString();

        String categoriaIntroducidaPorUsuario = "";
        while (enumIncorrecto) {
            System.out.print(mensaje);
            categoriaIntroducidaPorUsuario = scanner.nextLine(); //EMPLEO
            // Forma 1
            // for (CategoriaIngreso cat:CategoriaIngreso.values()) {
            //     if (categoriaIntroducidaPorUsuario == cat.name()) {
            //         return cat;
            //     }
            // }
            // System.out.println(mensajeError);
            // Forma 2
            try {
                resultado = CategoriaIngreso.valueOf(categoriaIntroducidaPorUsuario);
                enumIncorrecto = false;
            } catch (IllegalArgumentException err) {
                System.out.println(mensajeError);
            }
            
        }

        return resultado;

    }
}

