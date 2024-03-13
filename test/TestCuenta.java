package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import src.entities.CategoriaGasto;
import src.entities.CategoriaIngreso;
import src.entities.Cuenta;
import src.entities.Gasto;
import src.entities.Ingreso;
import src.entities.Movimiento;

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

        cuenta.ingresar(10, CategoriaIngreso.OTROS, LocalDate.of(1990, 11, 11), "Clases particulares");
        cuenta.ingresar(20, CategoriaIngreso.NEGOCIOS, LocalDate.of(1990, 11, 12), "Otro concepto");
        
        assertEquals(cuenta.getSaldo(), 130, 0.000001);

        Ingreso[] movimientos = cuenta.getIngresos();

        assertEquals(movimientos.length, 2 );
        
        assertEquals(movimientos[0].getCategoria(), CategoriaIngreso.OTROS);
        assertEquals(movimientos[1].getCategoria(), CategoriaIngreso.NEGOCIOS);

        assertEquals(movimientos[0].getFecha(), LocalDate.of(1990, 11, 11));
        assertEquals(movimientos[1].getFecha(), LocalDate.of(1990, 11, 12));

        assertEquals(movimientos[0].getConcepto(), "Clases particulares");
        assertEquals(movimientos[1].getConcepto(), "Otro concepto");

        assertEquals(movimientos[0].getValor(), 10, 0.00001);
        assertEquals(movimientos[1].getValor(), 20, 0.00001);

        // hacer el resto de aserciones para comprobar el resto de atributos de cada ingreso
    }

  @Test
    public void testGastar(){
        Cuenta cuenta = new Cuenta(100);

        cuenta.gastar(10, CategoriaGasto.NECESIDAD, LocalDate.of(1990, 11, 11), "Comida");
        cuenta.gastar(20, CategoriaGasto.OCIO, LocalDate.of(1990, 11, 12), "Cine");

        assertEquals(cuenta.getSaldo(), 70, 0.000002);

        Gasto[] movimientosGasto = cuenta.getGastos();

        assertEquals(movimientosGasto.length, 2 );
        
        assertEquals(movimientosGasto[0].getCategoria(), CategoriaGasto.NECESIDAD);
        assertEquals(movimientosGasto[1].getCategoria(), CategoriaGasto.OCIO);

        assertEquals(movimientosGasto[0].getFecha(), LocalDate.of(1990, 11, 11));
        assertEquals(movimientosGasto[1].getFecha(), LocalDate.of(1990, 11, 12));

        assertEquals(movimientosGasto[0].getConcepto(), "Comida");
        assertEquals(movimientosGasto[1].getConcepto(), "Cine");

        assertEquals(movimientosGasto[0].getValor(), 10, 0.00001);
        assertEquals(movimientosGasto[1].getValor(), 20, 0.00001);
    }

    @Test
    public void testIngresosGastos(){
        Cuenta cuenta = new Cuenta(100);

        cuenta.ingresar(10, CategoriaIngreso.EMPLEO, LocalDate.now(), "");
        cuenta.ingresar(20, CategoriaIngreso.EMPLEO, LocalDate.now(), "");
        cuenta.gastar(15, CategoriaGasto.CULTURA, LocalDate.now(), "");

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

    @Test
    public void testListaMovimientos() {
        Cuenta cuenta = new Cuenta();

        cuenta.ingresar(100, CategoriaIngreso.EMPLEO, LocalDate.of(2023, 1, 1), "Movimiento 1");
        cuenta.gastar(50, CategoriaGasto.OCIO, LocalDate.of(2022, 1, 1), "Movimiento 2");
        cuenta.ingresar(100, CategoriaIngreso.NEGOCIOS, LocalDate.of(2021, 1, 1), "Movimiento 3");
        cuenta.gastar(50, CategoriaGasto.CULTURA, LocalDate.of(2020, 1, 1), "Movimiento 4");
        
        Movimiento[] listaDeMovimientos = cuenta.getListaMovimientos();

        assertEquals(listaDeMovimientos.length, 4);
        assertEquals(listaDeMovimientos[0].getValor(), 100, 0.00001);
        assertEquals(listaDeMovimientos[1].getValor(), 50, 0.00001);
        assertEquals(listaDeMovimientos[2].getValor(), 100, 0.00001);
        assertEquals(listaDeMovimientos[3].getValor(), 50, 0.00001);      
        
        assertEquals(listaDeMovimientos[0].getCategoria(), CategoriaIngreso.EMPLEO);
        assertEquals(listaDeMovimientos[1].getCategoria(), CategoriaGasto.OCIO);
        assertEquals(listaDeMovimientos[2].getCategoria(), CategoriaIngreso.NEGOCIOS);
        assertEquals(listaDeMovimientos[3].getCategoria(), CategoriaGasto.CULTURA);

        assertEquals(listaDeMovimientos[0].getFecha(), LocalDate.of(2023,1,1));
        assertEquals(listaDeMovimientos[0].getFecha(), LocalDate.of(2022,1,1));
        assertEquals(listaDeMovimientos[0].getFecha(), LocalDate.of(2021,1,1));
        assertEquals(listaDeMovimientos[0].getFecha(), LocalDate.of(2020,1,1));

        assertEquals(listaDeMovimientos[0].getConcepto(), "Movimiento 1");
        assertEquals(listaDeMovimientos[0].getConcepto(), "Movimiento 2");
        assertEquals(listaDeMovimientos[0].getConcepto(), "Movimiento 3");
        assertEquals(listaDeMovimientos[0].getConcepto(), "Movimiento 4");
        
    }

}
