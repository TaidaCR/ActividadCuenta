package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import src.entities.Movimiento;

public class TestMovimiento {
    @Test
    public void testCreateMovimiento() {
        Movimiento mov = new Movimiento("2024-02-28", "Cine", -15);

        assertEquals(LocalDate.of(2024, 2, 28), mov.getFecha() );
        assertEquals("Cine", mov.getConcepto() );
        assertEquals(-15, mov.getValor(), 0.00001);
    }

}
