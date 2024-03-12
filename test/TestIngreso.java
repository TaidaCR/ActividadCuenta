package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import src.entities.CategoriaIngreso;
import src.entities.Ingreso;

public class TestIngreso {
    @Test
    public void testCreateIngreso() {
        Ingreso ing = new Ingreso(LocalDate.of(2000, 1, 1), "Salario", 360000, CategoriaIngreso.EMPLEO);

        assertEquals(LocalDate.of(2000, 1, 1), ing.getFecha());
        assertEquals("Salario", ing.getConcepto());
        assertEquals(360000, ing.getValor(), 0.00001);
        assertEquals(CategoriaIngreso.EMPLEO, ing.getCategoria());

    }
}
