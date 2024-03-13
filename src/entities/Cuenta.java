package src.entities;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cuenta {
    private float saldo;
    private float ingresos;
    private float gastos;
    private ArrayList<Ingreso> listaIngresos = new ArrayList<>();    
    private ArrayList<Gasto> listaGastos = new ArrayList<>();

    // Constructor principal
    public Cuenta(float saldo) {
        this.saldo = saldo;
        ingresos = 0;
        gastos = 0;
    }

    public Cuenta() {
        this(0);  // equivale a llamar a Cuenta(0)
    }

    public float getSaldo() {
        return saldo;
    }

    public void ingresar(float valor, CategoriaIngreso categoria, LocalDate fecha, String concepto) {
        saldo += valor;
        ingresos += valor;

        /*
         * Instanciar el ingreso con los argumentos de entrada
         * añadir el ingreso nuevo a this.listaIngresos
         */

        Ingreso ing = new Ingreso(fecha, concepto, valor, categoria);
        listaIngresos.add(ing);

    }

    public void gastar(float valor, CategoriaGasto categoria, LocalDate fecha, String concepto) {
        saldo -= valor;
        gastos += valor;

        Gasto gast = new Gasto(fecha, concepto, valor, categoria);
        listaGastos.add(gast);
    }

    public float getTotalIngresos() {
        return ingresos;
    }

    public float getTotalGastos() {
        return gastos;
    }

    
    public Ingreso[] getIngresos() {
        return listaIngresos.toArray(new Ingreso[0]);
    }
    
    public Gasto[] getGastos() {
        return listaGastos.toArray(new Gasto[0]);
    }

   

    
}
