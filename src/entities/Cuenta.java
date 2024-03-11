package src.entities;

public class Cuenta {
    private float saldo;

    public Cuenta(float saldo) {
        this.saldo = saldo;
    }

    public Cuenta() {
        this(0);  // equivale a llamar a Cuenta(0)
    }

    public float getSaldo() {
        return saldo;
    }

    public void ingresar(float ingreso) {
        saldo += ingreso;
    }

    public void gastar(float gasto) {
        saldo -= gasto;
    }
    
}
