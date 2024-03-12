package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import src.entities.CategoriaGasto;

import src.entities.Gasto;

public class TestGasto {
    @Test
    public void testCreateGasto() {
        Gasto gast = new Gasto(LocalDate.of(2000, 1, 1), "Cine", 350, CategoriaGasto.CULTURA);

        assertEquals(LocalDate.of(2000, 1, 1), gast.getFecha());
        assertEquals("Cine", gast.getConcepto());
        assertEquals(350, gast.getValor(), 0.00001);
        assertEquals(CategoriaGasto.CULTURA, gast.getCategoria());

    }
    
}
