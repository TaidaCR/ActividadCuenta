package src.presentacion;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import src.logica.CategoriaGasto;
import src.logica.CategoriaIngreso;

public class Validador {
    private Scanner scanner = new Scanner(System.in);

    public LocalDate pideFecha(String mensaje, String msgError){
        boolean fechaIncorrecta = true;
        String fecha;
        LocalDate resultado = null;
        LocalDate today = LocalDate.now();

        while (fechaIncorrecta) {
            System.out.print(mensaje);
            fecha = scanner.nextLine();
            try {
                resultado = LocalDate.parse(fecha);
                if(!resultado.isAfter(today)){
                    fechaIncorrecta = false;
                } else {
                    System.out.println(msgError);
                }
            } catch (DateTimeParseException err) {
                System.out.println(msgError);
            }
        }
        return resultado;
    }

    public float pideNumero(String mensaje, String msgError) {
        boolean valorIncorrecto = true;
        float valor = 0;

        while(valorIncorrecto) {
            System.out.print(mensaje);
            try {
                valor = scanner.nextFloat();
                
                if (valor > 0){
                    valorIncorrecto = false;
                } else {
                    System.out.println("Debe ser un numero positivo");
                }
            } catch (InputMismatchException err) {
                System.out.println(msgError);
            }
        scanner.nextLine();
        }

        return valor;
    }

    public CategoriaIngreso pideCatIngreso(String mensaje, String msgError) { 
        boolean enumIncorrecta = true;
        StringBuilder sb = new StringBuilder();
        CategoriaIngreso resultado = null;

        sb.append(String.format("%s (", mensaje));

        
        for (CategoriaIngreso cat: CategoriaIngreso.values()){
            String nombreCategoria = cat.name() + ", ";
            sb.append(nombreCategoria);
        }
        sb.delete(sb.length() - 2, sb.length()).append("):");

        mensaje = sb.toString();

        String categoriaIntroducidaPorUsuario = "";
        
        
        while (enumIncorrecta) {
            System.out.println(mensaje);
            categoriaIntroducidaPorUsuario = scanner.nextLine().toUpperCase();
            //forma 1
            //for(CategoriaIngreso cat: CategoriaIngreso.values()) {
            //    if (categoriaIntroducidaPorUsuario == cat.name()) {
            //        return cat;
            //    }
            //}
            //System.out.println(msgError); 
            //forma 2
            
            try {
                resultado = CategoriaIngreso.valueOf(categoriaIntroducidaPorUsuario);
                enumIncorrecta = false;
            } catch (IllegalArgumentException err) {
                System.out.println( msgError);
            }
        }
        return resultado;
    }
    
    public CategoriaGasto pideCatGasto(String mensaje, String msgError) {
        boolean catGastoIncorrecto = true;
        StringBuilder sb = new StringBuilder();
        CategoriaGasto resultado = null;

        sb.append(String.format("%s (", mensaje));

        for (CategoriaGasto cat: CategoriaGasto.values()){
            String nombreCategoria = cat.name() + ", ";
            sb.append(nombreCategoria);
        }
        sb.delete(sb.length() - 2, sb.length()).append("):");

        mensaje =sb.toString();

        String categoriaIntroducidaPorUsuario = "";

        while(catGastoIncorrecto){

            System.out.println(mensaje);
            categoriaIntroducidaPorUsuario = scanner.nextLine().toUpperCase();

            try {
                resultado = CategoriaGasto.valueOf(categoriaIntroducidaPorUsuario);
                catGastoIncorrecto = false;
            } catch (IllegalArgumentException err) {
                System.out.println( msgError);
            }
        }
        return resultado;
    }

    public String pideConcepto(String mensaje, String msgError, int Longitudmin){
        boolean conceptoIncorrecto = true;
        String concepto = null;

        while (conceptoIncorrecto){
            try {
                System.out.print(mensaje);
                
                concepto = scanner.nextLine();

                if (concepto.length() >= Longitudmin){
                    conceptoIncorrecto = false;
                } else {
                    System.out.println(String.format("La longitud debe ser al menos %d", Longitudmin));
                }
    
            } catch (InputMismatchException err) {
                System.out.println(msgError);
            }
        }
        return concepto;
    }
}




