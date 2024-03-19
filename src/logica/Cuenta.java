package src.logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Cuenta {
    private float saldo;
    private float ingresos;
    private float gastos;
    private ArrayList<Movimiento> listaMovimientos = new ArrayList<>();
    private String fichero;

    // Constructor principal
    public Cuenta(float saldo, String fichero) {
        this.saldo = saldo;
        ingresos = 0;
        gastos = 0;
        this.fichero = fichero;
    }

    public Cuenta(String fichero) {
        this(0, fichero);  // equivale a llamar a Cuenta(0)
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
        listaMovimientos.add(ing);

    }

    public void gastar(float valor, CategoriaGasto categoria, LocalDate fecha, String concepto) {
        saldo -= valor;
        gastos += valor;

        Gasto gast = new Gasto(fecha, concepto, valor, categoria);
        listaMovimientos.add(gast);
    }

    public float getTotalIngresos() {
        return ingresos;
    }

    public float getTotalGastos() {
        return gastos;
    }
    
    public Ingreso[] getIngresos() {
        ArrayList <Ingreso> ingresos = new ArrayList<> ();
        
        for(Movimiento mov: listaMovimientos) {
            if (mov instanceof Ingreso) {
                ingresos.add((Ingreso)mov);
            }
        }

        return ingresos.toArray(new Ingreso[0]);
    }

    public Gasto[] getGastos() {

        ArrayList<Gasto> gastos = new ArrayList<>();

        for (Movimiento mov : listaMovimientos) {
            if (mov instanceof Gasto) {
                gastos.add((Gasto)mov);
            }
        }
        return gastos.toArray(new Gasto[0]);
    }

    public Movimiento[] getListaMovimientos() {
        return listaMovimientos.toArray(new Movimiento[0]);
    }

    public void leerFichero() {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {

            String linea;

            while((linea = br.readLine()) != null) {
                // 2024-03-01,sueldo,1200.00,EMPLEO,I
                String [] campos = linea.split(",");
                // Determinamos los valors de los campos fecha, concepto, cantidad, categoria y tipo
                LocalDate fecha = LocalDate.parse(campos[0]);
                String concepto = campos[1];
                float cantidad = Float.parseFloat(campos[2]);
                String tipo = campos[4];
                if (tipo.equals("I")) {
                    CategoriaIngreso categoria = CategoriaIngreso.valueOf(campos[3]);
                    this.ingresar(cantidad,categoria,fecha, concepto);
                } else {
                    CategoriaGasto categoria = CategoriaGasto.valueOf(campos[3]);
                    this.gastar(cantidad, categoria, fecha, concepto);
                }
            }
        } catch (IOException err) {
            System.out.println("Se ha producido un error en la lectura del fichero");
            err.printStackTrace();
        }
    }
}

/*
 * 
 * 
 */
