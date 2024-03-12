package src.entities;

public class Cuenta {
    private float saldo;
    private float ingresos;
    private float gastos;

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

    public void ingresar(float ingreso) {
        saldo += ingreso;
        ingresos += ingreso;
    }

    public void gastar(float gasto) {
        saldo -= gasto;
        gastos += gasto;
    }

    public float getTotalIngresos() {
        return ingresos;
    }

    public float getTotalGastos() {
        return gastos;
    }

    
}
