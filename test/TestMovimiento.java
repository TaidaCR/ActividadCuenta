package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import src.entities.Movimiento;

public class TestMovimiento {
    @Test
    public void testCreateMovimiento() {
        Movimiento mov = new Movimiento("2024-02-28", "Cine", -15);

        assertEquals(mov.getFecha(), "2024-02-28");
        assertEquals(mov.getAsunto(), "Cine");
        assertEquals(mov.getValor(), -15, 0.00001);
    }

}
