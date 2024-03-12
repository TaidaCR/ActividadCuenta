package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import src.entities.Cuenta;

public class TestCuenta {
    @Test
    public void testCreateCuentaWithSaldo() {
        Cuenta cuenta = new Cuenta(100);

        assertEquals(cuenta.getSaldo(), 100, 0.00001);
    }

    @Test
    public void testCreateCuentaWithoutSaldo() {
        Cuenta cuenta = new Cuenta();

        assertEquals(cuenta.getSaldo(), 0, 0.00001);
    }

    @Test
    public void testIngresar(){
        Cuenta cuenta = new Cuenta(100);

        cuenta.ingresar(10);
        assertEquals(cuenta.getSaldo(), 110, 0.000001);
    }

    @Test
    public void testGastar(){
        Cuenta cuenta = new Cuenta(100);

        cuenta.gastar(10);
        assertEquals(cuenta.getSaldo(), 90, 0.000002);
    }

    @Test
    public void testIngresosGastos(){
        Cuenta cuenta = new Cuenta(100);

        cuenta.ingresar(10);
        cuenta.ingresar(20);
        cuenta.gastar(15);

        assertEquals(115, cuenta.getSaldo(), 0.000001);
        assertEquals(30, cuenta.getTotalIngresos(), 0.00001);
        assertEquals(15, cuenta.getTotalGastos(), 0.00001);
    }

    @Test
    public void testInicializaFloatsACero () {
        Cuenta cuenta = new Cuenta(100);

        assertEquals(cuenta.getSaldo(), 100, 0.0001);        
        assertEquals(cuenta.getTotalIngresos(), 0, 0.0001);        
        assertEquals(cuenta.getTotalGastos(), 0, 0.0001);
        
    }

}
