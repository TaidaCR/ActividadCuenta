package src.logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import src.logica.excepciones.FormatError;

public class Cuenta {
    private float saldo;
    private float ingresos;
    private float gastos;
    private ArrayList<Movimiento> listaMovimientos = new ArrayList<>();
    private String fichero;
    Map <CategoriaIngreso, Float> resumenIngresos= new HashMap<>();
    Map <CategoriaGasto, Float> resumenGastos= new HashMap<>();

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

    public void ingresar(float valor, CategoriaIngreso categoria, LocalDate fecha, String concepto, boolean guardar) {
        saldo += valor;
        ingresos += valor;

        Ingreso ing = new Ingreso(fecha, concepto, valor, categoria);
        if (guardar) {
            ing.save(fichero);
        }
        listaMovimientos.add(ing);
    }

    public void ingresar(float valor, CategoriaIngreso categoria, LocalDate fecha, String concepto) {
        ingresar(valor, categoria, fecha, concepto, false);
    }

    public void gastar(float valor, CategoriaGasto categoria, LocalDate fecha, String concepto) {
        gastar(valor, categoria, fecha, concepto, false);
    }
    
    public void gastar(float valor, CategoriaGasto categoria, LocalDate fecha, String concepto, boolean guardar) {
        saldo -= valor;
        gastos += valor;

        Gasto gast = new Gasto(fecha, concepto, valor, categoria);
        if (guardar) {
            gast.save(fichero);
        }
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

    private LocalDate convertirAfecha(String strFecha) throws FormatError{
        try {
            return LocalDate.parse(strFecha);
        } catch (DateTimeParseException err) {
            String msgError = String.format("No es posible convertir %s a fecha\n", strFecha);
            System.out.printf(msgError);
            throw new FormatError(msgError);
        }
    }

    private float convertirAcantidad(String strCantidad) throws FormatError {
        try {
            float cantidad = Float.parseFloat(strCantidad);
            if (cantidad > 0) {
                return cantidad;
            }
            throw new FormatError("El valor de cantidad debe ser positivo");
        } catch (NumberFormatException err) {
            String msgError = String.format("No es posible convertir %s a cantidad\n");
            System.out.println(msgError);
            throw new FormatError(msgError);
        } 
    }

    private Enum<?> convertirAcategoria(String valor, String tipo) throws FormatError{   
        Enum<?> categoria = null;
        try {
            if (tipo.equals("I")) {
                categoria = CategoriaIngreso.valueOf(valor);
            } else if (tipo.equals("G")) {
                categoria = CategoriaGasto.valueOf(valor);
            }
        } catch (IllegalArgumentException err) {
            String msgError = String.format("No se puede convertir %s en categoria de movimiento", valor);
            System.out.println(msgError);
            throw new FormatError(msgError);
        }
        return categoria;
    }

    public void leerFichero() throws FormatError {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {

            String linea;

            while((linea = br.readLine()) != null) {
                // 2024-03-01,sueldo,1200.00,EMPLEO,I
               
                String [] campos = linea.split(";");
                LocalDate fecha = convertirAfecha(campos[0]);
                float cantidad = convertirAcantidad(campos[2]);
                Enum<?> categoria = convertirAcategoria(campos[3], campos[4]);
                if (campos[4].equals("I")) {
                    ingresar(cantidad, (CategoriaIngreso) categoria, fecha, campos[1]);
                } else {
                    gastar(cantidad, (CategoriaGasto) categoria, fecha, campos[1]);
                }
                
            }
        } catch (FileNotFoundException err) {
            
        } catch (IOException err) {
            System.out.println("Se ha producido un error en la lectura del fichero");
            err.printStackTrace();
        }
    }

    public String imprimirMovimientos(String fichero){
        String linea;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            while((linea = br.readLine()) != null){
                sb.append(linea+"\n");
            }
        }catch (IOException err){

        }
        return sb.toString();
    }

    //VA SUMANDO EL VALOR DE CADA SUBCATEGORIA
    public void ingresarCatIngresos(Enum<?> categoria, float valor) {
        for (CategoriaIngreso cat: CategoriaIngreso.values()){
            if (cat.name().equals(categoria.name())){
                resumenIngresos.put(cat, resumenIngresos.get(categoria)+valor);
                break;
            }
        }
    }

    //VA SUMANDO EL VALOR DE CADA SUBCATEGORIA
    public void gastarCatGastos(Enum<?> categoria, float valor) {
        for (CategoriaGasto cat: CategoriaGasto.values()){
            if (cat.name().equals(categoria.name())){
                resumenGastos.put(cat, resumenGastos.get(categoria)+valor);
                break;
            }
        }
    }

    public String imprimirPorCategoria(String fichero) throws IOException{
        String linea;
        StringBuilder sb = new StringBuilder();

        for (CategoriaIngreso cat : CategoriaIngreso.values()) {
            resumenIngresos.put(cat, 0.0f);
        }

        for (CategoriaGasto cat : CategoriaGasto.values()) {
            resumenGastos.put(cat, 0.0f);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            while((linea = br.readLine()) != null){
                
                String [] campos = linea.split(";");
                LocalDate fecha = convertirAfecha(campos[0]);
                float cantidad = convertirAcantidad(campos[2]);
                Enum<?> categoria = convertirAcategoria(campos[3], campos[4]);
                String concepto = campos[1];
                
                if (campos[4].equals("I")) {
                    ingresarCatIngresos(categoria, cantidad);  
                    ingresar(cantidad,(CategoriaIngreso) categoria,fecha,concepto);
                } else {
                    gastarCatGastos(categoria, cantidad);
                    gastar(cantidad, (CategoriaGasto) categoria, fecha, concepto);
                }
            }

        sb.append("INGRESOS        CANTIDAD\n");
        sb.append("------------------------\n");

        for (CategoriaIngreso cat: CategoriaIngreso.values()){
            sb.append(String.format("%-10s---->%8.2f€\n",cat,resumenIngresos.get(cat)));
        }

        sb.append("------------------------\n");
        sb.append(String.format("TOTAL:         %8.2f€\n",getTotalIngresos()));
        sb.append("  \n");
        sb.append("GASTOS          CANTIDAD\n");
        sb.append("------------------------\n");

        for (CategoriaGasto cat: CategoriaGasto.values()){
            sb.append(String.format("%-10s---->%8.2f€\n",cat,resumenGastos.get(cat)));
        }

        sb.append("------------------------\n");
        sb.append(String.format("TOTAL:         %8.2f€\n",getTotalGastos()));

        } catch (FormatError err) {
    
        } catch (IOException err) {

        }
        return sb.toString();
    }
}